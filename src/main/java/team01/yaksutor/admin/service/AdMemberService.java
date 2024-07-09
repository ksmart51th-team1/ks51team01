package team01.yaksutor.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.admin.mapper.AdMemberMapper;
import team01.yaksutor.common.mapper.MemberMapper;
import team01.yaksutor.dto.License;
import team01.yaksutor.dto.ManagePharmacy;
import team01.yaksutor.dto.Member;
import team01.yaksutor.dto.Pharmacy;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdMemberService {
    private final AdMemberMapper adMemberMapper;


    @Transactional
    public void insertOwnerMember(Member member, License license, Pharmacy pharmacy){
        adMemberMapper.memberInsert(member);
        adMemberMapper.licenseInsert(license);
        adMemberMapper.pharmacyInsert(pharmacy);
    }
    @Transactional
    public void insertStaffMember(Member member, License license, ManagePharmacy managePharmacy){
        adMemberMapper.memberInsert(member);
        adMemberMapper.licenseInsert(license);
        adMemberMapper.managePharmacyInsert(managePharmacy);
    }
}
