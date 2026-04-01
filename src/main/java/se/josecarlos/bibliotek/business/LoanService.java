package se.josecarlos.bibliotek.business;

import se.josecarlos.bibliotek.data.BookDAO;
import se.josecarlos.bibliotek.data.LoanDAO;
import se.josecarlos.bibliotek.data.MemberDAO;
import se.josecarlos.bibliotek.dto.LoanDTO;
import se.josecarlos.bibliotek.mapper.LoanMapper;
import se.josecarlos.bibliotek.model.Book;
import se.josecarlos.bibliotek.model.Loan;
import se.josecarlos.bibliotek.model.Member;

import java.time.LocalDate;
import java.util.List;

public class LoanService {

    private final LoanDAO loanDAO;
    private final BookDAO bookDAO;
    private final MemberDAO memberDAO;

    public LoanService() {
        this.loanDAO = new LoanDAO();
        this.bookDAO = new BookDAO();
        this.memberDAO = new MemberDAO();
    }

    public boolean borrowBook(int memberId, int bookId) {
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

        LocalDate loanDate = LocalDate.now();
        LocalDate dueDate = loanDate.plusDays(14);

        loanDAO.createLoan(bookId, memberId, loanDate, dueDate);
        bookDAO.decreaseAvailableCopies(bookId);

        return true;
    }

    public boolean returnBook(int loanId) {
        Loan loan = loanDAO.getActiveLoanById(loanId);

        if (loan == null) {
            System.out.println("Active loan not found.");
            return false;
        }

        loanDAO.returnLoan(loanId, LocalDate.now());
        bookDAO.increaseAvailableCopies(loan.getBookId());

        return true;
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
}