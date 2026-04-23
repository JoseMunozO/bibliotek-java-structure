package se.josecarlos.bibliotek.dto;

public class BookDTO {

    private int id;
    private String title;
    private int availableCopies;
    private String authors;

    public BookDTO(int id, String title, int availableCopies, String authors) {
        this.id = id;
        this.title = title;
        this.availableCopies = availableCopies;
        this.authors = authors;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public String getAuthors() {
        return authors;
    }
}
