package se.josecarlos.bibliotek.dto;

public class BookStatisticsDTO {

    private final int bookId;
    private final String title;
    private final long loanCount;

    public BookStatisticsDTO(int bookId, String title, long loanCount) {
        this.bookId = bookId;
        this.title = title;
        this.loanCount = loanCount;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public long getLoanCount() {
        return loanCount;
    }
}
