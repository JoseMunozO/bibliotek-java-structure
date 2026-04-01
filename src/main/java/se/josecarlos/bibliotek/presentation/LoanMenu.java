package se.josecarlos.bibliotek.presentation;

import se.josecarlos.bibliotek.business.LoanService;
import se.josecarlos.bibliotek.dto.LoanDTO;

import java.util.List;
import java.util.Scanner;

public class LoanMenu {

    private final LoanService loanService;
    private final Scanner scanner;

    public LoanMenu() {
        this.loanService = new LoanService();
        this.scanner = new Scanner(System.in);
    }

    public void showLoanMenu() {
        int choice;

        do {
            System.out.println("\n--- LOAN MENU ---");
            System.out.println("1. Borrow book");
            System.out.println("2. Return book");
            System.out.println("3. Show active loans");
            System.out.println("4. Show loans by member");
            System.out.println("0. Back");
            System.out.print("Choose: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> borrowBook();
                case 2 -> returnBook();
                case 3 -> showActiveLoans();
                case 4 -> showLoansByMember();
                case 0 -> System.out.println("Back to main menu...");
                default -> System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }

    private void borrowBook() {
        System.out.print("Member ID: ");
        int memberId = Integer.parseInt(scanner.nextLine());

        System.out.print("Book ID: ");
        int bookId = Integer.parseInt(scanner.nextLine());

        boolean success = loanService.borrowBook(memberId, bookId);

        if (success) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Could not borrow book.");
        }
    }

    private void returnBook() {
        System.out.print("Loan ID: ");
        int loanId = Integer.parseInt(scanner.nextLine());

        boolean success = loanService.returnBook(loanId);

        if (success) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Could not return book.");
        }
    }

    private void showActiveLoans() {
        List<LoanDTO> loans = loanService.getActiveLoans();

        if (loans.isEmpty()) {
            System.out.println("No active loans found.");
            return;
        }

        for (LoanDTO loan : loans) {
            System.out.println(
                    "Loan ID: " + loan.getId()
                            + " | Book ID: " + loan.getBookId()
                            + " | Member ID: " + loan.getMemberId()
                            + " | Loan date: " + loan.getLoanDate()
                            + " | Due date: " + loan.getDueDate()
            );
        }
    }

    private void showLoansByMember() {
        System.out.print("Member ID: ");
        int memberId = Integer.parseInt(scanner.nextLine());

        List<LoanDTO> loans = loanService.getLoansByMemberId(memberId);

        if (loans.isEmpty()) {
            System.out.println("No loans found for this member.");
            return;
        }

        for (LoanDTO loan : loans) {
            System.out.println(
                    "Loan ID: " + loan.getId()
                            + " | Book ID: " + loan.getBookId()
                            + " | Loan date: " + loan.getLoanDate()
                            + " | Due date: " + loan.getDueDate()
                            + " | Return date: " + loan.getReturnDate()
            );
        }
    }
}