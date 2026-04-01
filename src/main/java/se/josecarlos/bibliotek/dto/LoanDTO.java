package se.josecarlos.bibliotek.dto;

import java.time.LocalDate;

public class LoanDTO {

    private int id;
    private int bookId;
    private int memberId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public LoanDTO(int id, int bookId, int memberId, LocalDate loanDate, LocalDate dueDate, LocalDate returnDate) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}