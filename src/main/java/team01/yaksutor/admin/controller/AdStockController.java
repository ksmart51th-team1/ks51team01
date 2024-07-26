package team01.yaksutor.admin.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.admin.mapper.AdStockMapper;
import team01.yaksutor.admin.service.AdStockService;
import team01.yaksutor.dto.*;
import team01.yaksutor.pharmacy.mapper.PhStockMapper;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdStockController {
    private final PhStockMapper phStockMapper;
    private final AdStockService adStockService;
    private final AdStockMapper adStockMapper;

    /**
     * 재고 등록 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @return
     */
    @GetMapping("/stockInsert")
    public String stockInsert(Model model) {
        model.addAttribute("title", "재고 등록");
        model.addAttribute("content", "재고 등록");

        return "admin/pharmStock/stockInsert";
    }

    /**
     * 재고 목록 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @return
     */
    @GetMapping("/stockSearchList")
    public String stockSearchList(Model model) {

        List<PharmStock> stockList = adStockMapper.getPharmStockList();

        model.addAttribute("stockList", stockList);
        model.addAttribute("title", "재고 검색");
        model.addAttribute("content", "재고 검색");

        return "admin/pharmStock/stockSearchList";
    }

    /**
     * 재고 수정 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @param stockCode
     * @return
     */
    @GetMapping("/stockModify")
    public String stockModify(Model model,
                              @RequestParam(value="stockCode") String stockCode) {
        StockInfo stockInfo = new StockInfo();
        PharmStock pharmStock = adStockMapper.getPharmStockByStockCode(stockCode);
        String mediCode = pharmStock.getMediCode();
        Medicine medicine = phStockMapper.getMedicineByCode(mediCode);
        List<Ingredient> ingrList = phStockMapper.getIngredientByCode(mediCode);
        List<Efficacy> effiList = phStockMapper.getEfficacyByCode(mediCode);
        stockInfo.setMedicine(medicine);
        stockInfo.setPharmStock(pharmStock);
        stockInfo.setIngrList(ingrList);
        stockInfo.setEffiList(effiList);
        // 재고의 기존 상태
        model.addAttribute("stockInfo", stockInfo);

        model.addAttribute("title", "재고 수정");
        model.addAttribute("content", "재고 수정");

        return "admin/pharmStock/stockModify";
    }


    /*@GetMapping("/stockDelete")
    public String stockDelete(Model model) {

        model.addAttribute("title", "재고 삭제");
        model.addAttribute("content", "재고 삭제");

        return "admin/pharmStock/stockDelete";
    }*/

    /**
     * 입출고 기록 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @return
     */
    @GetMapping("/stockHistoryList")
    public String stockHistoryList(Model model) {
        List<StockHistory> stockHistoryList = adStockMapper.getStockHistory();

        model.addAttribute("stockHistoryList", stockHistoryList);
        model.addAttribute("title", "입출고 기록");
        model.addAttribute("content", "입출고 기록");

        return "admin/pharmStock/stockHistoryList";
    }

    /**
     * 재고 추가 ajax요청
     * 작성자: 길범진
     * @param stockInfo
     * @return
     */
    @PostMapping("/stockInsert")
    @ResponseBody
    public String insertStock(@RequestPart(value="stockInfo") StockInfo stockInfo){
        log.info("stockInfo: {}", stockInfo);
        adStockService.insertStock(stockInfo);

        return "admin/pharmStock/stockSearchList";
    }

    /**
     * 재고 수정 ajax요청
     * 작성자: 길범진
     * @param stockInfo
     * @return
     */
    @PostMapping("/stockModify")
    @ResponseBody
    public String modifyStock(@RequestPart(value="stockInfo") StockInfo stockInfo){
        log.info("stockInfo: {}", stockInfo);
        adStockService.modifyStock(stockInfo);

        return "admin/pharmStock/stockSearchList";
    }


}
