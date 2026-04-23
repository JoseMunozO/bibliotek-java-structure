package se.josecarlos.bibliotek.dto;

import java.time.LocalDate;

public class NotificationDTO {

    private final int id;
    private final int memberId;
    private final Integer loanId;
    private final String type;
    private final String message;
    private final LocalDate sentDate;
    private final boolean read;

    public NotificationDTO(int id, int memberId, Integer loanId, String type, String message, LocalDate sentDate, boolean read) {
        this.id = id;
        this.memberId = memberId;
        this.loanId = loanId;
        this.type = type;
        this.message = message;
        this.sentDate = sentDate;
        this.read = read;
    }

    public int getId() {
        return id;
    }

    public int getMemberId() {
        return memberId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getSentDate() {
        return sentDate;
    }

    public boolean isRead() {
        return read;
    }
}
