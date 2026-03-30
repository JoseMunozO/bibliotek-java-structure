package se.josecarlos.bibliotek.business;

import se.josecarlos.bibliotek.data.BookDAO;
import se.josecarlos.bibliotek.dto.BookDTO;
import se.josecarlos.bibliotek.dto.BookDetailsDTO;
import se.josecarlos.bibliotek.mapper.BookMapper;
import se.josecarlos.bibliotek.model.Book;

import java.util.Comparator;
import java.util.List;

public class BookService {

    private final BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public List<BookDTO> getAllBooks() {
        return bookDAO.getAllBooks().stream()
                .map(BookMapper::toBookDTO)
                .sorted(Comparator.comparing(BookDTO::getTitle))
                .toList();
    }

    public List<BookDTO> getAvailableBooks() {
        return bookDAO.getAllBooks().stream()
                .filter(book -> book.getAvailableCopies() > 0)
                .map(BookMapper::toBookDTO)
                .sorted(Comparator.comparing(BookDTO::getTitle))
                .toList();
    }

    public List<BookDTO> searchBooks(String keyword) {
        return bookDAO.searchBooksByTitle(keyword).stream()
                .map(BookMapper::toBookDTO)
                .sorted(Comparator.comparing(BookDTO::getTitle))
                .toList();
    }

    public BookDetailsDTO getBookDetails(int id) {
        Book book = bookDAO.getBookById(id);

        if (book == null) {
            return null;
        }

        return BookMapper.toBookDetailsDTO(book);
    }
}