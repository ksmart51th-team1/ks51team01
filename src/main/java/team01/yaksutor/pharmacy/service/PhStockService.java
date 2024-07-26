package team01.yaksutor.pharmacy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.dto.*;
import team01.yaksutor.pharmacy.mapper.PhStockMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class PhStockService {
    private final PhStockMapper phStockMapper;

    /**
     * medicine, PharmStock, Ingredient, Efficacy에 값을 담아서 쿼리문을 실행하는 트랜젝션
     * 작성자: 길범진
     * @param stockInfo
     * @param sid
     */
    @Transactional
    public void stockInsert(StockInfo stockInfo,
                            String sid){
        String pharCode = phStockMapper.getPharCodeById(sid);
        String pharmName = phStockMapper.getPharmNameById(sid);

        Medicine medicine = stockInfo.getMedicine();
        medicine.setRegMId(sid);
        phStockMapper.insertMedicine(medicine);

        PharmStock pharmStock = stockInfo.getPharmStock();
        pharmStock.setMediCode(medicine.getMediCode());
        pharmStock.setPharCode(pharCode);
        pharmStock.setPharmName(pharmName);
        pharmStock.setExpiraDate(medicine.getMediUsebydate());
        phStockMapper.insertPharmStock(pharmStock);

        List<Ingredient> ingrList = stockInfo.getIngrList();
        for (Ingredient ingr : ingrList) {
            ingr.setMediCode(pharmStock.getMediCode());
            ingr.setRegMId(sid);
            phStockMapper.insertIngredient(ingr);
        }

        List<Efficacy> effiList = stockInfo.getEffiList();
        for (Efficacy eff : effiList) {
            eff.setMediCode(pharmStock.getMediCode());
            eff.setRegMId(sid);
            phStockMapper.insertEfficacy(eff);
        }
    }

    /**
     * 재고 출고 트랜젝션
     * 작성자: 길범진
     * @param qty
     * @param stockCode
     */
    @Transactional
    public void stockRelease(String qty,
                             String stockCode){
        log.info("qty: {}", qty);
        log.info("stockCode: {}", stockCode);

        int newQty = Integer.parseInt(qty);

        PharmStock beforeStock = phStockMapper.getStockInfo(stockCode);

        StockHistory sh = new StockHistory();
        int inout = beforeStock.getPharQty() - Integer.parseInt(qty);
        sh.setInoutQty(beforeStock.getPharQty());
        sh.setRealInoutQty(inout);
        sh.setOrderInoutQty(inout);
        sh.setInoutType("출고");
        sh.setAfterQty(newQty);
        sh.setPharCode(beforeStock.getPharCode());
        sh.setMediCode(beforeStock.getMediCode());

        PharmStock pharmStock = new PharmStock();
        pharmStock.setStockCode(stockCode);
        pharmStock.setPharQty(newQty);
        phStockMapper.stockRelease(pharmStock);
        phStockMapper.insertStockHistory(sh);

    }

    /**
     * 재고조사 후 등록 트랜젝션
     * 작성자: 길범진
     * @param stockClearance
     */
    @Transactional
    public void stockClearance(StockClearance stockClearance){
        phStockMapper.insertStockClearance(stockClearance);
        String stockCode = stockClearance.getStockCode();
        int isUpdate = stockClearance.getAdnormalQty();
        int pharQty = stockClearance.getPostInveQty();
        PharmStock pharmStock = new PharmStock();
        pharmStock.setStockCode(stockCode);
        pharmStock.setPharQty(pharQty);
        if(isUpdate != 0){
            phStockMapper.updateStockClearance(pharmStock);
        } else{
            phStockMapper.updateStockClear(pharmStock);
        }
    }

}
