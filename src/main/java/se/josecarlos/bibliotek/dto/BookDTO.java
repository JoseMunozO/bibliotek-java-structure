package se.josecarlos.bibliotek.dto;

public class BookDTO {

    private int id;
    private String title;
    private int availableCopies;

    public BookDTO(int id, String title, int availableCopies) {
        this.id = id;
        this.title = title;
        this.availableCopies = availableCopies;
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
}