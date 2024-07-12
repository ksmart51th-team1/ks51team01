package team01.yaksutor.supplier.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.admin.dto.AdEfficacy;
import team01.yaksutor.admin.dto.AdIngredient;
import team01.yaksutor.admin.dto.MedicalInfo;
import team01.yaksutor.admin.dto.SellMediInfo;
import team01.yaksutor.dto.*;
import team01.yaksutor.file.dto.FileRequest;

import java.util.List;

@Mapper
public interface SuMedicineMapper {
    List<SellMedicine> getListSellMedicine(String suppCode);

    String getSuppCode(String sid);

    void insertMedicine(Medicine medicine);

    void insertImg(FileRequest fileRequest);

    void insertIngredient(Ingredient ingredient);

    void insertEfficacy(Efficacy efficacy);

    void insertSellMedicine(SellMedicine sellMedicine);

    Medicine getMedicineInfo(String goodsCode);

    String getSuppName(String goodsCode);

    SellMedicine getSellMedicineInfo(String goodsCode);

    List<Ingredient> getIngrList(String mediCode);

    List<Efficacy> getEffiList(String mediCode);

    String getMediCode(String goodsCode);

    void updateMedicine(Medicine medicine);

    void deleteIngredient(String medicineCode);

    void deleteEfficacy(String medicineCode);

    void updateSellMedicine(SellMedicine sellMedicine);

    void deleteSellMedicine(String mediCode);
}
