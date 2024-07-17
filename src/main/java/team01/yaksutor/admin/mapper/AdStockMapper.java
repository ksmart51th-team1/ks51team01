package team01.yaksutor.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.*;

import java.util.List;

@Mapper
public interface AdStockMapper {
    List<PharmStock> getPharmStockList();

    String getPharIdByPharCode(String pharCode);

    String getPharNameByPharCode(String pharCode);

    /*StockInfo getStockInfoByPharCode(String stockCode);*/

    PharmStock getPharmStockByStockCode(String stockCode);

    void updateMedicine(Medicine medicine);

    void deleteIngredient(String mediCode);

    void deleteEfficacy(String mediCode);

    void updateStock(PharmStock pharmStock);

    List<StockHistory> getStockHistory();

    void addStockHistory(StockHistory stockHistory);
}
