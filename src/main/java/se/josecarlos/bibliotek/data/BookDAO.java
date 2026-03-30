package se.josecarlos.bibliotek.data;

import se.josecarlos.bibliotek.dto.BookDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public List<BookDTO> getAllBooks() {
        List<BookDTO> bookDTOS = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM books")) {

            while (rs.next()) {
                BookDTO bookDTO = new BookDTO(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("isbn"),
                        rs.getInt("year_published"),
                        rs.getInt("total_copies"),
                        rs.getInt("available_copies")
                );

                bookDTOS.add(bookDTO);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookDTOS;
    }
}