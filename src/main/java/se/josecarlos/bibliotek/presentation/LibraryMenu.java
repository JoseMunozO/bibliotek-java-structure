package se.josecarlos.bibliotek.presentation;

import java.util.Scanner;

public class LibraryMenu {

    private final Scanner scanner;
    private final BookMenu bookMenu;
    private final MemberMenu memberMenu;
    private final LoanMenu loanMenu;

    public LibraryMenu() {
        this.scanner = new Scanner(System.in);
        this.bookMenu = new BookMenu();
        this.memberMenu = new MemberMenu();
        this.loanMenu = new LoanMenu();
    }

    public void showMainMenu() {
        int choice;

        do {
            System.out.println("\n--- LIBRARY MENU ---");
            System.out.println("1. Book menu");
            System.out.println("2. Member menu");
            System.out.println("3. Loan menu");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> bookMenu.showBookMenu();
                case 2 -> memberMenu.showMemberMenu();
                case 3 -> loanMenu.showLoanMenu();
                case 0 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }
}