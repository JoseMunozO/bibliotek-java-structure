package se.josecarlos.bibliotek.data;

import se.josecarlos.bibliotek.model.Loan;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanDAO {

    public void createLoan(int bookId, int memberId, LocalDate loanDate, LocalDate dueDate) {
        String sql = """
                INSERT INTO loans (book_id, member_id, loan_date, due_date)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, bookId);
            stmt.setInt(2, memberId);
            stmt.setDate(3, Date.valueOf(loanDate));
            stmt.setDate(4, Date.valueOf(dueDate));

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void returnLoan(int loanId, LocalDate returnDate) {
        String sql = """
                UPDATE loans
                SET return_date = ?
                WHERE id = ?
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(returnDate));
            stmt.setInt(2, loanId);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Loan> getActiveLoans() {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM loans WHERE return_date IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                loans.add(mapRow(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return loans;
    }

    public List<Loan> getLoansByMemberId(int memberId) {
        List<Loan> loans = new ArrayList<>();
        String sql = "SELECT * FROM loans WHERE member_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, memberId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    loans.add(mapRow(rs));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return loans;
    }

    public Loan getActiveLoanById(int loanId) {
        String sql = "SELECT * FROM loans WHERE id = ? AND return_date IS NULL";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, loanId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private Loan mapRow(ResultSet rs) throws Exception {
        Date returnDateSql = rs.getDate("return_date");

        return new Loan(
                rs.getInt("id"),
                rs.getInt("book_id"),
                rs.getInt("member_id"),
                rs.getDate("loan_date").toLocalDate(),
                rs.getDate("due_date").toLocalDate(),
                returnDateSql != null ? returnDateSql.toLocalDate() : null
        );
    }
}