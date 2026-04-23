package se.josecarlos.bibliotek.dto;

import java.time.LocalDate;

public class MemberProfileDTO {

    private final int id;
    private final String fullName;
    private final String email;
    private final LocalDate membershipDate;
    private final String membershipType;
    private final String status;
    private final int activeLoansCount;
    private final int totalLoansCount;
    private final int totalFinesCount;
    private final double unpaidFineAmount;

    public MemberProfileDTO(int id, String fullName, String email, LocalDate membershipDate, String membershipType,
                            String status, int activeLoansCount, int totalLoansCount, int totalFinesCount,
                            double unpaidFineAmount) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.membershipDate = membershipDate;
        this.membershipType = membershipType;
        this.status = status;
        this.activeLoansCount = activeLoansCount;
        this.totalLoansCount = totalLoansCount;
        this.totalFinesCount = totalFinesCount;
        this.unpaidFineAmount = unpaidFineAmount;
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

    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public String getStatus() {
        return status;
    }

    public int getActiveLoansCount() {
        return activeLoansCount;
    }

    public int getTotalLoansCount() {
        return totalLoansCount;
    }

    public int getTotalFinesCount() {
        return totalFinesCount;
    }

    public double getUnpaidFineAmount() {
        return unpaidFineAmount;
    }
}
