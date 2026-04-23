package se.josecarlos.bibliotek.business;

import se.josecarlos.bibliotek.data.LoanDAO;
import se.josecarlos.bibliotek.data.MemberDAO;
import se.josecarlos.bibliotek.data.NotificationDAO;
import se.josecarlos.bibliotek.dto.NotificationDTO;
import se.josecarlos.bibliotek.model.Loan;
import se.josecarlos.bibliotek.model.Member;

import java.util.List;

public class NotificationService {

    private final NotificationDAO notificationDAO;
    private final MemberDAO memberDAO;
    private final LoanDAO loanDAO;

    public NotificationService() {
        this.notificationDAO = new NotificationDAO();
        this.memberDAO = new MemberDAO();
        this.loanDAO = new LoanDAO();
    }

    public boolean sendNotification(int memberId, Integer loanId, String type, String message) {
        String normalizedType = type == null ? "" : type.trim().toUpperCase();
        String normalizedMessage = message == null ? "" : message.trim();

        if (memberId <= 0) {
            System.out.println("Member ID must be greater than 0.");
            return false;
        }

        Member member = memberDAO.getMemberById(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return false;
        }

        if (normalizedType.isEmpty() || normalizedMessage.isEmpty()) {
            System.out.println("Type and message are required.");
            return false;
        }

        if (loanId != null) {
            Loan loan = loanDAO.getLoanById(loanId);
            if (loan == null) {
                System.out.println("Loan not found.");
                return false;
            }

            if (loan.getMemberId() != memberId) {
                System.out.println("That loan does not belong to the selected member.");
                return false;
            }
        }

        return notificationDAO.createNotification(memberId, loanId, normalizedType, normalizedMessage);
    }

    public List<NotificationDTO> getNotificationsByMemberId(int memberId) {
        if (memberId <= 0) {
            return List.of();
        }

        return notificationDAO.getNotificationsByMemberId(memberId);
    }
}
