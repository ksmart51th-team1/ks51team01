package team01.yaksutor.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.*;

import java.util.List;

@Mapper
public interface AdMemberMapper {

    /*납품업체 회원 추가*/
    void supplierInsert(Supplier supplier);
    /*개국약사,관리약사 회원 추가*/
    int memberInsert(Member member);
    int licenseInsert(License license);
    int pharmacyInsert(Pharmacy pharmacy);
    int managePharmacyInsert(ManagePharmacy managePharmacy);
    /*ID중복체크*/
    boolean idCheck(String memberId);
    /* 개국약사 아이디로 약국코드 조회*/
    String getPharCode(String pharId);

    /*관리약사에서 개국약사로 회원전환 */
    void deleteManagePharmacyById(String staffId);
    void updateMemberLevelNumById(String staffId);

    /*약국전체정보조회*/
    List<Pharmacy> getPharmacyList();
    /*납품업체전체정보조회*/
    List<Supplier> getSupplierInfo();
}
