package se.josecarlos.bibliotek.business;

import se.josecarlos.bibliotek.data.MemberDAO;
import se.josecarlos.bibliotek.dto.MemberDTO;
import se.josecarlos.bibliotek.mapper.MemberMapper;
import se.josecarlos.bibliotek.model.Member;

import java.util.List;

public class MemberService {

    private final MemberDAO memberDAO;

    public MemberService() {
        this.memberDAO = new MemberDAO();
    }

    public List<MemberDTO> getAllMembers() {
        return memberDAO.getAllMembers().stream()
                .map(MemberMapper::toDTO)
                .toList();
    }

    public void registerMember(String firstName, String lastName, String email) {
        Member member = new Member(0, firstName, lastName, email, "ACTIVE");
        memberDAO.createMember(member);
    }
}