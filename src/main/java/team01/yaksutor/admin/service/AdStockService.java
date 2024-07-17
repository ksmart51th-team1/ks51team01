package team01.yaksutor.admin.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.admin.mapper.AdStockMapper;
import team01.yaksutor.dto.*;
import team01.yaksutor.pharmacy.mapper.PhStockMapper;

import java.util.List;

import static java.lang.Math.abs;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AdStockService {
    private final HttpServletRequest request;
    private final PhStockMapper phStockMapper;
    private final AdStockMapper adStockMapper;

    @Transactional
    public void insertStock(StockInfo stockInfo){
        Medicine medicine = stockInfo.getMedicine();
        PharmStock pharmStock = stockInfo.getPharmStock();
        List<Ingredient> ingrList = stockInfo.getIngrList();
        List<Efficacy> effiList = stockInfo.getEffiList();
        String pharCode = pharmStock.getPharCode();
        String regMId = adStockMapper.getPharIdByPharCode(pharCode);
        String pharmName = adStockMapper.getPharNameByPharCode(pharCode);

        // medicine set
        medicine.setRegMId(regMId);
        // medicine insert
        phStockMapper.insertMedicine(medicine);

        //pharmStock set
        pharmStock.setMediCode(medicine.getMediCode());
        pharmStock.setPharmName(pharmName);
        pharmStock.setExpiraDate(medicine.getMediUsebydate());
        phStockMapper.insertPharmStock(pharmStock);

        // ingrList set
        ingrList.forEach(ingr -> {
            ingr.setRegMId(regMId);
            ingr.setMediCode(medicine.getMediCode());
            phStockMapper.insertIngredient(ingr);
        });

        // effiList set
        effiList.forEach(effi -> {
            effi.setRegMId(regMId);
            effi.setMediCode(medicine.getMediCode());
            phStockMapper.insertEfficacy(effi);
        });
    }

    @Transactional
    public void modifyStock(StockInfo stockInfo){
        Medicine medicine = stockInfo.getMedicine();
        PharmStock pharmStock = stockInfo.getPharmStock();
        List<Ingredient> ingrList = stockInfo.getIngrList();
        List<Efficacy> effiList = stockInfo.getEffiList();
        String mediCode = medicine.getMediCode();
        String regMId = medicine.getRegMId();
        List<Ingredient> oldIngrList = phStockMapper.getIngredientByCode(mediCode);
        List<Efficacy> oldEffiList = phStockMapper.getEfficacyByCode(mediCode);

        String stockCode = pharmStock.getStockCode();
        PharmStock oldStock = adStockMapper.getPharmStockByStockCode(stockCode);
        int oldQty = oldStock.getPharQty();
        int newQty = pharmStock.getPharQty();
        String inoutType = "입고";
        if(oldQty > newQty){
            inoutType = "출고";
        }

        adStockMapper.updateMedicine(medicine);

        adStockMapper.deleteIngredient(mediCode);
        adStockMapper.deleteEfficacy(mediCode);

        ingrList.forEach(ingr -> {
            ingr.setRegMId(regMId);
            ingr.setMediCode(medicine.getMediCode());
            phStockMapper.insertIngredient(ingr);
        });

        // effiList set
        effiList.forEach(effi -> {
            effi.setRegMId(regMId);
            effi.setMediCode(medicine.getMediCode());
            phStockMapper.insertEfficacy(effi);
        });

        pharmStock.setMediCode(mediCode);
        adStockMapper.updateStock(pharmStock);

        if(oldQty != newQty){
            StockHistory stockHistory = new StockHistory();
            stockHistory.setMediCode(medicine.getMediCode());
            stockHistory.setPharCode(pharmStock.getPharCode());
            stockHistory.setInoutQty(oldQty);
            stockHistory.setRealInoutQty(abs(oldQty - newQty));
            stockHistory.setOrderInoutQty(abs(oldQty - newQty));
            stockHistory.setInoutType(inoutType);
            stockHistory.setAfterQty(pharmStock.getPharQty());
            log.info("stockHistory: {}", stockHistory);
            adStockMapper.addStockHistory(stockHistory);
        }
    }
}
