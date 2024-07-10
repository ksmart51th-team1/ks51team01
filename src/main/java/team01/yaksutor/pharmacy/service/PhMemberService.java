package team01.yaksutor.pharmacy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.admin.mapper.AdMemberMapper;
import team01.yaksutor.dto.License;
import team01.yaksutor.dto.ManagePharmacy;
import team01.yaksutor.dto.Member;
import team01.yaksutor.dto.Pharmacy;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhMemberService {

    private final AdMemberMapper adMemberMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void insertStaffMember(Member member,License license, Pharmacy pharmacy){
        //패스워드 암호화 인코딩
        member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));

        member.setMemberLevelNum(4);
        license.setPharmacistId(member.getMemberId());

        ManagePharmacy managePharmacy = new ManagePharmacy();
        String pharCode = adMemberMapper.getPharCode(pharmacy.getPharId());
        managePharmacy.setPharCode(pharCode);
        managePharmacy.setPharId(member.getMemberId());

        adMemberMapper.memberInsert(member);
        adMemberMapper.licenseInsert(license);
        adMemberMapper.managePharmacyInsert(managePharmacy);
    }

    @Transactional
    public void insertOwnerMember(Member member, License license, Pharmacy pharmacy){
        member.setMemberLevelNum(3);
        license.setPharmacistId(member.getMemberId());
        pharmacy.setPharId(member.getMemberId());
        member.setMemberPw(passwordEncoder.encode(member.getMemberPw()));

        adMemberMapper.memberInsert(member);
        adMemberMapper.licenseInsert(license);
        adMemberMapper.pharmacyInsert(pharmacy);
    }
}
