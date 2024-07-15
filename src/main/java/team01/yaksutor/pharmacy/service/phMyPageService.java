package team01.yaksutor.pharmacy.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team01.yaksutor.common.mapper.MemberMapper;
import team01.yaksutor.dto.Member;
import team01.yaksutor.pharmacy.mapper.PhMemberMapper;

@Service
@Slf4j
@RequiredArgsConstructor
public class phMyPageService {

    private final HttpServletRequest request;
    private final MemberMapper memberMapper;
    private final PhMemberMapper phMemberMapper;

    public Member getMeberInfoById() {
        String memberId = (String) request.getSession().getAttribute("S_ID");
        return memberMapper.getMemberInfoById(memberId);
    }

    public int modifyMyAccount(Member member){
        return phMemberMapper.modifyMyAccount(member);
    }
}
