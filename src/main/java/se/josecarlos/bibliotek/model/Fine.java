package se.josecarlos.bibliotek.model;

import java.time.LocalDate;

public class Fine {

    private int id;
    private int loanId;
    private double amount;
    private LocalDate issuedDate;
    private String status;

    public Fine(int id, int loanId, double amount, LocalDate issuedDate, String status) {
        this.id = id;
        this.loanId = loanId;
        this.amount = amount;
        this.issuedDate = issuedDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getLoanId() {
        return loanId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Fine{" +
                "id=" + id +
                ", loanId=" + loanId +
                ", amount=" + amount +
                ", issuedDate=" + issuedDate +
                ", status='" + status + '\'' +
                '}';
    }
}