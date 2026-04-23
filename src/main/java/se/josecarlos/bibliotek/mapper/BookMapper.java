package se.josecarlos.bibliotek.mapper;

import se.josecarlos.bibliotek.dto.BookDTO;
import se.josecarlos.bibliotek.dto.BookDetailsDTO;
import se.josecarlos.bibliotek.model.Book;

public class BookMapper {

    public static BookDTO toBookDTO(Book book) {
        return new BookDTO(
                book.getId(),
                book.getTitle(),
                book.getAvailableCopies(),
                ""
        );
    }

    public static BookDetailsDTO toBookDetailsDTO(Book book) {
        return new BookDetailsDTO(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getYearPublished(),
                book.getTotalCopies(),
                book.getAvailableCopies(),
                null,
                null,
                null,
                "",
                ""
        );
    }
}
