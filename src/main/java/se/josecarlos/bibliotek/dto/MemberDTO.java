package se.josecarlos.bibliotek.dto;

public class MemberDTO {

    private int id;
    private String fullName;
    private String email;

    public MemberDTO(int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }
}