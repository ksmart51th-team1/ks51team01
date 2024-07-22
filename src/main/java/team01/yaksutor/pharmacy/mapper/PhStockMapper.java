package team01.yaksutor.pharmacy.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.*;

import java.util.List;

@Mapper
public interface PhStockMapper {
    String getPharCodeById(String sid);

    String getPharmNameById(String sid);

    List<PharmStock> getPharmStockList(String pharCode);

    void insertMedicine(Medicine medicine);

    void insertPharmStock(PharmStock pharmStock);

    void insertIngredient(Ingredient ingredient);

    void insertEfficacy(Efficacy efficacy);

    Medicine getMedicineByCode(String mediCode);

    PharmStock getPharmStockByCode(String mediCode, String pharCode);

    List<Ingredient> getIngredientByCode(String mediCode);

    List<Efficacy> getEfficacyByCode(String mediCode);

    String getStockQty(String stockCode);

    void stockRelease(PharmStock pharmStock);

    void insertStockHistory(StockHistory stockHistory);

    PharmStock getStockInfo(String stockCode);

    List<StockHistory> getStockHistory(String pharCode);

    List<PharmStock> getOldStockCheck(String pharCode);

    void insertStockClearance(StockClearance stockClearance);

    void updateStockClearance(PharmStock pharmStock);

    void updateStockClear(PharmStock pharmStock);
}