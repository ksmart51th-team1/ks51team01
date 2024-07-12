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

    int checkLevel(String regMId);

    List<SellMediInfo> getMedicineList(int startRow, int rowPerPage);

    int getMedicineListCnt();

    void insertMedicine(MedicalInfo medicalInfo);

    void insertImg(FileRequest fileRequest);

    void insertIngredient(AdIngredient adIngredient);

    void insertEfficacy(AdEfficacy adEfficacy);

    void insertSellMedicine(SellMediInfo sellMediInfo);

    String getSuppCode(String regMId);

    MedicalInfo getMedicineInfo(String goodsCode);

    String getSuppName(String goodsCode);

    SellMediInfo getSellMediInfo(String goodsCode);

    List<AdIngredient> getAdIngredient(String mediCode);

    List<AdEfficacy> getAdEfficacy(String mediCode);

    void updateMedicine(MedicalInfo medicalInfo);

    void deleteIngredient(String medicineCode);

    void deleteEfficacy(String medicineCode);

    void updateSellMedicine(SellMediInfo sellMediInfo);

    String getMedicineImg(String medicineCode);

    String getMediCode(String goodsCode);

    String getMedicineName(String mediCode);

    void deleteSellMedicine(String mediCode);

    
}
