package team01.yaksutor.admin.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.admin.mapper.AdMemberMapper;
import team01.yaksutor.dto.Member;
import team01.yaksutor.dto.Supplier;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdSupplierService {

    private final HttpServletRequest request;
    private final PasswordEncoder passwordEncoder;
    private final AdMemberMapper adMemberMapper;

    @Transactional
    public void supplierInsert(Member member, Supplier supplier) {

        member.setMemberPw(passwordEncoder.encode(member.getMemberPw())); //비밀번호 암호화
        member.setMemberLevelNum(2); //납품업체 권한 셋팅
        String sessionId = (String) request.getSession().getAttribute("S_ID"); //현재 로그인중인 관리자의 세션 ID
        supplier.setSuppId(member.getMemberId()); // 납품업체 id에 memberId 주입
        supplier.setAdminId(sessionId); // 세션ID를 등록자ID에 주입
        supplier.setAppState("관리자승인");

        adMemberMapper.memberInsert(member);
        adMemberMapper.supplierInsert(supplier);




    }

    public List<Supplier> getSupplierInfo() {
        List<Supplier> supplierInfo = adMemberMapper.getSupplierInfo();
        return supplierInfo;
    }
}
