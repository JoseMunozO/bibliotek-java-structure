package se.josecarlos.bibliotek.business;

import se.josecarlos.bibliotek.data.BookDAO;
import se.josecarlos.bibliotek.data.LoanDAO;
import se.josecarlos.bibliotek.data.MemberDAO;
import se.josecarlos.bibliotek.data.ReviewDAO;
import se.josecarlos.bibliotek.dto.ReviewDTO;

import java.util.List;

public class ReviewService {

    private final ReviewDAO reviewDAO;
    private final BookDAO bookDAO;
    private final MemberDAO memberDAO;
    private final LoanDAO loanDAO;

    public ReviewService() {
        this.reviewDAO = new ReviewDAO();
        this.bookDAO = new BookDAO();
        this.memberDAO = new MemberDAO();
        this.loanDAO = new LoanDAO();
    }

    public List<ReviewDTO> getReviewsByBookId(int bookId) {
        if (bookId <= 0) {
            return List.of();
        }

        return reviewDAO.getReviewsByBookId(bookId);
    }

    public boolean createReview(int bookId, int memberId, int rating, String comment) {
        String normalizedComment = comment == null ? "" : comment.trim();

        if (bookId <= 0 || memberId <= 0) {
            System.out.println("Book ID and member ID must be greater than 0.");
            return false;
        }

        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return false;
        }

        if (bookDAO.getBookById(bookId) == null) {
            System.out.println("Book not found.");
            return false;
        }

        if (memberDAO.getMemberById(memberId) == null) {
            System.out.println("Member not found.");
            return false;
        }

        boolean hasCompletedLoan = loanDAO.hasReturnedLoanForBookAndMember(bookId, memberId);
        if (!hasCompletedLoan) {
            System.out.println("The member must have returned this book before leaving a review.");
            return false;
        }

        if (reviewDAO.hasMemberReviewedBook(memberId, bookId)) {
            System.out.println("This member has already reviewed this book.");
            return false;
        }

        return reviewDAO.createReview(bookId, memberId, rating, normalizedComment);
    }
}
