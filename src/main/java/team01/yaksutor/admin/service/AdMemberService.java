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

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdMemberService {
    private final AdMemberMapper adMemberMapper;
    private final MemberMapper memberMapper;

    public List<Pharmacy> getPharmacyInfo(){
        List<Pharmacy> pharmacyInfo = adMemberMapper.getPharmacyList();
        return pharmacyInfo;
    }

    public List<Member> getMemberInfo() {
        List<Member> memberInfo = memberMapper.getMemberInfo();
        return memberInfo;
    }


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
    @Transactional
    public void staffToOwnerMember(Member member, Pharmacy pharmacy) {
        String memberId = member.getMemberId();
        Member staffInfo = memberMapper.getMemberInfoById(memberId);
        String staffId = staffInfo.getMemberId();
        adMemberMapper.deleteManagePharmacyById(staffId);
        adMemberMapper.updateMemberLevelNumById(staffId);
        //Pharmacy테이블에 추가
        pharmacy.setPharId(memberId);
        adMemberMapper.pharmacyInsert(pharmacy);
    }

    //회원 삭제
    public void memberDelete(String memberId) {
        Member member = memberMapper.getMemberInfoById(memberId);
        adMemberMapper.deleteMemberById(member);
    }

    //회원수정
    public int memberModify(Member member) {
        return adMemberMapper.modifyMember(member);
    }

    public int pharmacyModify(Pharmacy pharmacy) {
        return adMemberMapper.modifyPharmacy(pharmacy);
    }

    public int pharmacyDelete(Pharmacy pharmacy) {
        return adMemberMapper.deletePharmacy(pharmacy);
    }
}
