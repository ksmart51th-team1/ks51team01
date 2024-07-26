package team01.yaksutor.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.admin.dto.*;
import team01.yaksutor.dto.SellMedicine;
import team01.yaksutor.file.dto.FileRequest;
import team01.yaksutor.file.util.FileUtils;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdMedicineMapper {
    // 판매자 아이디 확인
    int checkLevel(String regMId);

    // 판매 의약품 목록 검색
    List<SellMediInfo> getMedicineList(int startRow, int rowPerPage);

    // 판매 의약품 수 검색
    int getMedicineListCnt();

    // 의약품 등록
    void insertMedicine(MedicalInfo medicalInfo);

    // 이미지 등록
    void insertImg(FileRequest fileRequest);

    // 의약품 성분 등록
    void insertIngredient(AdIngredient adIngredient);

    // 의약품 효능 등록
    void insertEfficacy(AdEfficacy adEfficacy);

    // 판매 의약품 등록
    void insertSellMedicine(SellMediInfo sellMediInfo);

    // 판매자 아이디로 납품업체 코드 검색
    String getSuppCode(String regMId);

    // 상품 코드로 의약품 정보 검색
    MedicalInfo getMedicineInfo(String goodsCode);

    // 상품 코드로 판매자 이름 검색
    String getSuppName(String goodsCode);

    // 상품 코드로 판매 의약품 정보 검색
    SellMediInfo getSellMediInfo(String goodsCode);

    // 의약품 코드로 의약품 성분 리스트 검색
    List<AdIngredient> getAdIngredient(String mediCode);

    // 의약품 코드로 의약품 효능 리스트 검색
    List<AdEfficacy> getAdEfficacy(String mediCode);

    // 의약품 정보 수정
    void updateMedicine(MedicalInfo medicalInfo);

    // 성분 삭제
    void deleteIngredient(String medicineCode);

    // 효능 삭제
    void deleteEfficacy(String medicineCode);

    // 판매 의약품 정보 수정
    void updateSellMedicine(SellMediInfo sellMediInfo);

    // 의약품 코드로 이미지 검색
    String getMedicineImg(String medicineCode);

    // 상품코드로 의약품 코드 검색
    String getMediCode(String goodsCode);

    // 의약품 코드로 의약품 이름 검색
    String getMedicineName(String mediCode);

    // 판매 의약품 상태 '삭제'로 수정
    void deleteSellMedicine(String mediCode);
}
