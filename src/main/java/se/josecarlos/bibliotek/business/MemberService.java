package se.josecarlos.bibliotek.business;

import se.josecarlos.bibliotek.data.MemberDAO;
import se.josecarlos.bibliotek.dto.MemberDTO;
import se.josecarlos.bibliotek.dto.MemberProfileDTO;
import se.josecarlos.bibliotek.mapper.MemberMapper;
import se.josecarlos.bibliotek.model.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class MemberService {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private final MemberDAO memberDAO;

    public MemberService() {
        this.memberDAO = new MemberDAO();
    }

    public List<MemberDTO> getAllMembers() {
        return memberDAO.getAllMembers().stream()
                .map(MemberMapper::toDTO)
                .toList();
    }

    public boolean registerMember(String firstName, String lastName, String email) {
        String normalizedFirstName = firstName == null ? "" : firstName.trim();
        String normalizedLastName = lastName == null ? "" : lastName.trim();
        String normalizedEmail = email == null ? "" : email.trim().toLowerCase();

        if (normalizedFirstName.isEmpty() || normalizedLastName.isEmpty() || normalizedEmail.isEmpty()) {
            System.out.println("First name, last name and email are required.");
            return false;
        }

        if (!EMAIL_PATTERN.matcher(normalizedEmail).matches()) {
            System.out.println("Invalid email format.");
            return false;
        }

        if (memberDAO.existsByEmail(normalizedEmail)) {
            System.out.println("A member with this email already exists.");
            return false;
        }

        Member member = new Member(
                0,
                normalizedFirstName,
                normalizedLastName,
                normalizedEmail,
                LocalDate.now(),
                "standard",
                "ACTIVE"
        );
        return memberDAO.createMember(member);
    }

    public MemberProfileDTO getMemberProfile(int memberId) {
        if (memberId <= 0) {
            return null;
        }

        return memberDAO.getMemberProfile(memberId);
    }

    public boolean updateMember(int memberId, String firstName, String lastName, String email, String membershipType) {
        String normalizedFirstName = firstName == null ? "" : firstName.trim();
        String normalizedLastName = lastName == null ? "" : lastName.trim();
        String normalizedEmail = email == null ? "" : email.trim().toLowerCase();
        String normalizedMembershipType = membershipType == null ? "" : membershipType.trim().toLowerCase();

        if (memberId <= 0) {
            System.out.println("Invalid member ID.");
            return false;
        }

        if (normalizedFirstName.isEmpty() || normalizedLastName.isEmpty() || normalizedEmail.isEmpty() || normalizedMembershipType.isEmpty()) {
            System.out.println("First name, last name, email and membership type are required.");
            return false;
        }

        if (!EMAIL_PATTERN.matcher(normalizedEmail).matches()) {
            System.out.println("Invalid email format.");
            return false;
        }

        if (memberDAO.existsByEmailExcludingMember(normalizedEmail, memberId)) {
            System.out.println("A member with this email already exists.");
            return false;
        }

        return memberDAO.updateMember(memberId, normalizedFirstName, normalizedLastName, normalizedEmail, normalizedMembershipType);
    }

    public boolean suspendMember(int memberId) {
        if (memberId <= 0) {
            System.out.println("Invalid member ID.");
            return false;
        }

        Member member = memberDAO.getMemberById(memberId);
        if (member == null) {
            System.out.println("Member not found.");
            return false;
        }

        if ("SUSPENDED".equalsIgnoreCase(member.getStatus())) {
            System.out.println("Member is already suspended.");
            return false;
        }

        return memberDAO.updateStatus(memberId, "SUSPENDED");
    }
}
