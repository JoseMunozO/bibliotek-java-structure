package se.josecarlos.bibliotek.data;

import se.josecarlos.bibliotek.dto.NotificationDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    public boolean createNotification(int memberId, Integer loanId, String type, String message) {
        String sql = """
                INSERT INTO notifications (member_id, loan_id, type, message, sent_date, is_read)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, memberId);
            if (loanId == null) {
                stmt.setNull(2, java.sql.Types.INTEGER);
            } else {
                stmt.setInt(2, loanId);
            }
            stmt.setString(3, type);
            stmt.setString(4, message);
            stmt.setDate(5, Date.valueOf(LocalDate.now()));
            stmt.setBoolean(6, false);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Could not create notification: " + e.getMessage());
        }

        return false;
    }

    public List<NotificationDTO> getNotificationsByMemberId(int memberId) {
        List<NotificationDTO> notifications = new ArrayList<>();
        String sql = """
                SELECT id, member_id, loan_id, type, message, sent_date, is_read
                FROM notifications
                WHERE member_id = ?
                ORDER BY sent_date DESC, id DESC
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, memberId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    notifications.add(mapRow(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notifications;
    }

    private NotificationDTO mapRow(ResultSet rs) throws SQLException {
        Date sentDate = rs.getDate("sent_date");
        Integer loanId = (Integer) rs.getObject("loan_id");

        return new NotificationDTO(
                rs.getInt("id"),
                rs.getInt("member_id"),
                loanId,
                rs.getString("type"),
                rs.getString("message"),
                sentDate != null ? sentDate.toLocalDate() : null,
                rs.getBoolean("is_read")
        );
    }
}
