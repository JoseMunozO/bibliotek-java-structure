package se.josecarlos.bibliotek.business;

import se.josecarlos.bibliotek.data.FineDAO;
import se.josecarlos.bibliotek.model.Fine;

import java.util.List;

public class FineService {

    private final FineDAO fineDAO;

    public FineService() {
        this.fineDAO = new FineDAO();
    }

    public void createFine(int loanId, double amount) {
        fineDAO.createFine(loanId, amount);
    }

    public List<Fine> getFinesByMemberId(int memberId) {
        return fineDAO.getFinesByMemberId(memberId);
    }

    public boolean payFine(int memberId, int fineId) {
        if (memberId <= 0 || fineId <= 0) {
            System.out.println("Member ID and fine ID must be greater than 0.");
            return false;
        }

        Fine fine = fineDAO.getFineByIdForMember(fineId, memberId);

        if (fine == null) {
            System.out.println("Fine not found for this member.");
            return false;
        }

        if ("PAID".equalsIgnoreCase(fine.getStatus())) {
            System.out.println("Fine is already paid.");
            return false;
        }

        return fineDAO.payFine(fineId);
    }

    public boolean hasFineForLoan(int loanId) {
        return fineDAO.hasFineForLoan(loanId);
    }
}
