package team01.yaksutor.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.admin.dto.AdEfficacy;
import team01.yaksutor.admin.dto.AdIngredient;
import team01.yaksutor.admin.dto.AdMedicine;
import team01.yaksutor.admin.dto.AdSellMedicine;
import team01.yaksutor.dto.SellMedicine;
import team01.yaksutor.file.dto.FileRequest;
import team01.yaksutor.file.util.FileUtils;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdMedicineMapper {

    int checkLevel(String regMId);

    List<Map<String, Object>> getMedicineList(int startRow, int rowPerPage);

    int getMedicineListCnt();

    void insertMedicine(AdMedicine adMedicine);

    void insertImg(FileRequest fileRequest);

    void insertIngrdient(AdIngredient ingredient);

    void insertEfficacy(AdEfficacy efficacy);

    void insertSellMedicine(AdSellMedicine adSellMedicine);

    String getSuppCode(String regMId);
}
