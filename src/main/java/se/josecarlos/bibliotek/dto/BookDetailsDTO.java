package se.josecarlos.bibliotek.dto;

public class BookDetailsDTO {

    private final int id;
    private final String title;
    private final String isbn;
    private final int yearPublished;
    private final int totalCopies;
    private final int availableCopies;
    private final String summary;
    private final String language;
    private final Integer pageCount;
    private final String authors;
    private final String categories;

    public BookDetailsDTO(int id, String title, String isbn, int yearPublished, int totalCopies, int availableCopies,
                          String summary, String language, Integer pageCount, String authors, String categories) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.yearPublished = yearPublished;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.summary = summary;
        this.language = language;
        this.pageCount = pageCount;
        this.authors = authors;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getYearPublished() {
        return yearPublished;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public String getSummary() {
        return summary;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public String getAuthors() {
        return authors;
    }

    public String getCategories() {
        return categories;
    }
}
