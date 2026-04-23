package se.josecarlos.bibliotek.dto;

import java.time.LocalDate;

public class ReviewDTO {

    private final int id;
    private final int bookId;
    private final int memberId;
    private final String memberName;
    private final int rating;
    private final String comment;
    private final LocalDate reviewDate;

    public ReviewDTO(int id, int bookId, int memberId, String memberName, int rating, String comment, LocalDate reviewDate) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
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

    public String getMemberName() {
        return memberName;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }
}
