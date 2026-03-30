package se.josecarlos.bibliotek;

import se.josecarlos.bibliotek.data.BookDAO;
import se.josecarlos.bibliotek.dto.BookDTO;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the Library Management System!");
        BookDAO bookDAO = new BookDAO();
        List<BookDTO> bookDTOS = bookDAO.getAllBooks();

        for (BookDTO bookDTO : bookDTOS) {
            System.out.println(bookDTO.getTitle() + " (" + bookDTO.getAvailableCopies() + " copies)");
        }
    }
}