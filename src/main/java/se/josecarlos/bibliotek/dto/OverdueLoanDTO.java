package se.josecarlos.bibliotek.dto;

import java.time.LocalDate;

public class OverdueLoanDTO {

    private final int loanId;
    private final int bookId;
    private final String bookTitle;
    private final int memberId;
    private final String memberName;
    private final String memberEmail;
    private final LocalDate dueDate;

    public OverdueLoanDTO(int loanId, int bookId, String bookTitle, int memberId, String memberName,
                          String memberEmail, LocalDate dueDate) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.dueDate = dueDate;
    }

    public int getLoanId() {
        return loanId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}
