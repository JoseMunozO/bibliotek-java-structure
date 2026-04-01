package se.josecarlos.bibliotek.mapper;

import se.josecarlos.bibliotek.dto.LoanDTO;
import se.josecarlos.bibliotek.model.Loan;

public class LoanMapper {

    public static LoanDTO toDTO(Loan loan) {
        return new LoanDTO(
                loan.getId(),
                loan.getBookId(),
                loan.getMemberId(),
                loan.getLoanDate(),
                loan.getDueDate(),
                loan.getReturnDate()
        );
    }
}