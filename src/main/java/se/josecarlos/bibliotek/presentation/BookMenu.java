package se.josecarlos.bibliotek.presentation;

import se.josecarlos.bibliotek.business.BookService;
import se.josecarlos.bibliotek.dto.BookDTO;
import se.josecarlos.bibliotek.dto.BookDetailsDTO;

import java.util.List;
import java.util.Scanner;

public class BookMenu {

    private final BookService bookService;
    private final Scanner scanner;

    public BookMenu() {
        this.bookService = new BookService();
        this.scanner = new Scanner(System.in);
    }

    public void showBookMenu() {
        int choice;

        do {
            System.out.println("\n--- BOOK MENU ---");
            System.out.println("1. Show all books");
            System.out.println("2. Show available books");
            System.out.println("3. Search books by title");
            System.out.println("4. Show book details");
            System.out.println("0. Back / Exit");
            System.out.print("Choose an option: ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> showAllBooks();
                case 2 -> showAvailableBooks();
                case 3 -> searchBooks();
                case 4 -> showBookDetails();
                case 0 -> System.out.println("Leaving Book Menu...");
                default -> System.out.println("Invalid option.");
            }

        } while (choice != 0);
    }

    private void showAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();

        System.out.println("\n--- ALL BOOKS ---");
        for (BookDTO book : books) {
            System.out.println("ID: " + book.getId()
                    + " | Title: " + book.getTitle()
                    + " | Available copies: " + book.getAvailableCopies());
        }
    }

    private void showAvailableBooks() {
        List<BookDTO> books = bookService.getAvailableBooks();

        System.out.println("\n--- AVAILABLE BOOKS ---");
        for (BookDTO book : books) {
            System.out.println("ID: " + book.getId()
                    + " | Title: " + book.getTitle()
                    + " | Available copies: " + book.getAvailableCopies());
        }
    }

    private void searchBooks() {
        System.out.print("Enter title keyword: ");
        String keyword = scanner.nextLine();

        List<BookDTO> books = bookService.searchBooks(keyword);

        System.out.println("\n--- SEARCH RESULTS ---");
        if (books.isEmpty()) {
            System.out.println("No books found.");
            return;
        }

        for (BookDTO book : books) {
            System.out.println("ID: " + book.getId()
                    + " | Title: " + book.getTitle()
                    + " | Available copies: " + book.getAvailableCopies());
        }
    }

    private void showBookDetails() {
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());

        BookDetailsDTO book = bookService.getBookDetails(id);

        if (book == null) {
            System.out.println("Book not found.");
            return;
        }

        System.out.println("\n--- BOOK DETAILS ---");
        System.out.println("ID: " + book.getId());
        System.out.println("Title: " + book.getTitle());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Year published: " + book.getYearPublished());
        System.out.println("Total copies: " + book.getTotalCopies());
        System.out.println("Available copies: " + book.getAvailableCopies());
    }
}