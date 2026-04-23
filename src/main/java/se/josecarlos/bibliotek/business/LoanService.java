package se.josecarlos.bibliotek.business;

import se.josecarlos.bibliotek.data.BookDAO;
import se.josecarlos.bibliotek.data.DatabaseConnection;
import se.josecarlos.bibliotek.data.FineDAO;
import se.josecarlos.bibliotek.data.LoanDAO;
import se.josecarlos.bibliotek.data.MemberDAO;
import se.josecarlos.bibliotek.dto.LoanDTO;
import se.josecarlos.bibliotek.dto.OverdueLoanDTO;
import se.josecarlos.bibliotek.mapper.LoanMapper;
import se.josecarlos.bibliotek.model.Book;
import se.josecarlos.bibliotek.model.Loan;
import se.josecarlos.bibliotek.model.Member;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.sql.Connection;
import java.util.List;

public class LoanService {

    private final LoanDAO loanDAO;
    private final BookDAO bookDAO;
    private final MemberDAO memberDAO;
    private final FineService fineService;
    private final FineDAO fineDAO;

    public LoanService() {
        this.loanDAO = new LoanDAO();
        this.bookDAO = new BookDAO();
        this.memberDAO = new MemberDAO();
        this.fineService = new FineService();
        this.fineDAO = new FineDAO();
    }

    public boolean borrowBook(int memberId, int bookId) {
        if (memberId <= 0 || bookId <= 0) {
            System.out.println("Member ID and book ID must be greater than 0.");
            return false;
        }

        Member member = memberDAO.getMemberById(memberId);
        Book book = bookDAO.getBookById(bookId);

        if (member == null) {
            System.out.println("Member not found.");
            return false;
        }

        if (book == null) {
            System.out.println("Book not found.");
            return false;
        }

        if (!member.getStatus().equalsIgnoreCase("ACTIVE")) {
            System.out.println("Member is not active.");
            return false;
        }

        if (book.getAvailableCopies() <= 0) {
            System.out.println("No available copies.");
            return false;
        }

        if (loanDAO.hasActiveLoanForBookAndMember(bookId, memberId)) {
            System.out.println("This member already has an active loan for this book.");
            return false;
        }

        LocalDate loanDate = LocalDate.now();
        LocalDate dueDate = loanDate.plusDays(14);

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            boolean loanCreated = loanDAO.createLoan(conn, bookId, memberId, loanDate, dueDate);
            boolean stockUpdated = loanCreated && bookDAO.decreaseAvailableCopies(conn, bookId);

            if (loanCreated && stockUpdated) {
                conn.commit();
                return true;
            }

            conn.rollback();
            System.out.println("Could not create the loan.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not create the loan.");
            return false;
        }
    }

    public boolean returnBook(int loanId) {
        if (loanId <= 0) {
            System.out.println("Loan ID must be greater than 0.");
            return false;
        }

        Loan loan = loanDAO.getActiveLoanById(loanId);

        if (loan == null) {
            System.out.println("Active loan not found.");
            return false;
        }

        return processReturn(loan);
    }

    public List<LoanDTO> getActiveLoans() {
        return loanDAO.getActiveLoans().stream()
                .map(LoanMapper::toDTO)
                .toList();
    }

    public List<LoanDTO> getLoansByMemberId(int memberId) {
        return loanDAO.getLoansByMemberId(memberId).stream()
                .map(LoanMapper::toDTO)
                .toList();
    }

    public List<LoanDTO> getOverdueLoans() {
        return loanDAO.getOverdueLoans().stream()
                .map(LoanMapper::toDTO)
                .toList();
    }

    public List<OverdueLoanDTO> getOverdueLoanRegister() {
        return loanDAO.getOverdueLoanRegister();
    }

    public boolean extendLoan(int loanId, int extraDays) {
        if (loanId <= 0 || extraDays <= 0) {
            System.out.println("Loan ID and extra days must be greater than 0.");
            return false;
        }

        Loan loan = loanDAO.getActiveLoanById(loanId);
        if (loan == null) {
            System.out.println("Active loan not found.");
            return false;
        }

        if (loan.getDueDate().isBefore(LocalDate.now())) {
            System.out.println("Cannot extend an overdue loan.");
            return false;
        }

        LocalDate newDueDate = loan.getDueDate().plusDays(extraDays);
        return loanDAO.extendLoan(loanId, newDueDate);
    }

    public boolean returnBookByMemberAndBook(int memberId, int bookId) {
        if (memberId <= 0 || bookId <= 0) {
            System.out.println("Member ID and book ID must be greater than 0.");
            return false;
        }

        Loan loan = loanDAO.getActiveLoanByBookAndMember(bookId, memberId);

        if (loan == null) {
            System.out.println("Active loan not found for this member and book.");
            return false;
        }

        return processReturn(loan);
    }

    private boolean processReturn(Loan loan) {
        LocalDate today = LocalDate.now();

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            boolean loanReturned = loanDAO.returnLoan(conn, loan.getId(), today);
            boolean stockUpdated = loanReturned && bookDAO.increaseAvailableCopies(conn, loan.getBookId());

            if (!loanReturned || !stockUpdated) {
                conn.rollback();
                System.out.println("Could not complete the return.");
                return false;
            }

            if (today.isAfter(loan.getDueDate()) && !fineDAO.hasFineForLoan(conn, loan.getId())) {
                long lateDays = ChronoUnit.DAYS.between(loan.getDueDate(), today);
                double fineAmount = lateDays * 2.0;
                boolean fineCreated = fineDAO.createFine(conn, loan.getId(), fineAmount);

                if (!fineCreated) {
                    conn.rollback();
                    System.out.println("Could not create the fine.");
                    return false;
                }
            }

            conn.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not complete the return.");
            return false;
        }
    }
}
