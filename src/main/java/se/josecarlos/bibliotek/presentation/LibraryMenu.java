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
            System.out.println("\n--- LIBRARY ROLE MENU ---");
            System.out.println("1. User");
            System.out.println("2. Librarian");
            System.out.println("3. Admin");
            System.out.println("0. Exit");
            choice = MenuInput.readInt(scanner, "Choose a role: ");

            switch (choice) {
                case 1 -> showUserMenu();
                case 2 -> showLibrarianMenu();
                case 3 -> showAdminMenu();
                case 0 -> System.out.println("Exiting system...");
                default -> System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }

    private void showUserMenu() {
        int choice;

        do {
            System.out.println("\n--- USER MENU ---");
            System.out.println("1. Browse books");
            System.out.println("2. My loans and fines");
            System.out.println("3. My profile");
            System.out.println("0. Back");
            choice = MenuInput.readInt(scanner, "Choose an option: ");

            switch (choice) {
                case 1 -> bookMenu.showBookMenu();
                case 2 -> loanMenu.showUserLoanMenu();
                case 3 -> memberMenu.showUserProfileMenu();
                case 0 -> System.out.println("Back to role menu...");
                default -> System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }

    private void showLibrarianMenu() {
        int choice;

        do {
            System.out.println("\n--- LIBRARIAN MENU ---");
            System.out.println("1. Browse books");
            System.out.println("2. Manage loans");
            System.out.println("0. Back");
            choice = MenuInput.readInt(scanner, "Choose an option: ");

            switch (choice) {
                case 1 -> bookMenu.showBookMenu();
                case 2 -> loanMenu.showLoanMenu();
                case 0 -> System.out.println("Back to role menu...");
                default -> System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }

    private void showAdminMenu() {
        int choice;

        do {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Browse books");
            System.out.println("2. Manage members");
            System.out.println("3. Manage loans");
            System.out.println("0. Back");
            choice = MenuInput.readInt(scanner, "Choose an option: ");

            switch (choice) {
                case 1 -> bookMenu.showBookMenu();
                case 2 -> memberMenu.showMemberMenu();
                case 3 -> loanMenu.showLoanMenu();
                case 0 -> System.out.println("Back to role menu...");
                default -> System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }
}
