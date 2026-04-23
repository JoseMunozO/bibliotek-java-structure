package se.josecarlos.bibliotek.dto;

public class MemberDTO {

    private int id;
    private String fullName;
    private String email;
    private String membershipType;
    private String status;

    public MemberDTO(int id, String fullName, String email, String membershipType, String status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.membershipType = membershipType;
        this.status = status;
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

    public String getMembershipType() {
        return membershipType;
    }

    public String getStatus() {
        return status;
    }
}
