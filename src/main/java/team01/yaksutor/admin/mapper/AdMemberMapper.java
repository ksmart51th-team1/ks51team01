package team01.yaksutor.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.License;
import team01.yaksutor.dto.Member;
import team01.yaksutor.dto.Pharmacy;

@Mapper
public interface AdMemberMapper {

    /*개국약사 회원 추가*/
    int memberInsert(Member member);
    int licenseInsert(License license);
    int pharmacyInsert(Pharmacy pharmacy);

    /*ID중복체크*/
    boolean idCheck(String memberId);
}
