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
    private final HttpServletRequest request;
    private final AdStockService adStockService;
    private final AdStockMapper adStockMapper;

    @GetMapping("/stockInsert")
    public String stockInsert(Model model) {
        model.addAttribute("title", "재고 등록");
        model.addAttribute("content", "재고 등록");

        return "admin/pharmStock/stockInsert";
    }

    @GetMapping("/stockSearchList")
    public String stockSearchList(Model model) {
        /*String sid = request.getSession().getAttribute("S_ID").toString();
        log.info("sid: {}", sid);
        String pharCode = phStockMapper.getPharCodeById(sid);
        List<PharmStock> stockList = phStockMapper.getPharmStockList(pharCode);*/

        List<PharmStock> stockList = adStockMapper.getPharmStockList();

        model.addAttribute("stockList", stockList);
        model.addAttribute("title", "재고 검색");
        model.addAttribute("content", "재고 검색");

        return "admin/pharmStock/stockSearchList";
    }

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

        model.addAttribute("stockInfo", stockInfo);

        model.addAttribute("title", "재고 수정");
        model.addAttribute("content", "재고 수정");

        return "admin/pharmStock/stockModify";
    }

    @GetMapping("/stockDelete")
    public String stockDelete(Model model) {

        model.addAttribute("title", "재고 삭제");
        model.addAttribute("content", "재고 삭제");

        return "admin/pharmStock/stockDelete";
    }

    @GetMapping("/stockHistoryList")
    public String stockHistoryList(Model model) {
        List<StockHistory> stockHistoryList = adStockMapper.getStockHistory();

        model.addAttribute("stockHistoryList", stockHistoryList);
        model.addAttribute("title", "입출고 기록");
        model.addAttribute("content", "입출고 기록");

        return "admin/pharmStock/stockHistoryList";
    }
    
    @GetMapping("/stockHistoryInsert")
    public String stockHistoryInsert(Model model) {

        model.addAttribute("title", "입출고 기록 추가");
        model.addAttribute("content", "입출고 기록 추가");
        
        return "admin/pharmStock/stockHistoryInsert";
    }

    @GetMapping("/stockHistoryModify")
    public String stockHistoryModify(Model model) {

        model.addAttribute("title", "입출고 기록 수정");
        model.addAttribute("content", "입출고 기록 수정");

        return "admin/pharmStock/stockHistoryModify";
    }

    @GetMapping("/stockHistoryDelete")
    public String stockHistoryDelete(Model model) {

        model.addAttribute("title", "입출고 기록 삭제");
        model.addAttribute("content", "입출고 기록 삭제");

        return "admin/pharmStock/stockHistoryDelete";
    }

    @PostMapping("/stockInsert")
    @ResponseBody
    public String insertStock(@RequestPart(value="stockInfo") StockInfo stockInfo){
        log.info("stockInfo: {}", stockInfo);
        adStockService.insertStock(stockInfo);

        return "admin/pharmStock/stockSearchList";
    }

    @PostMapping("/stockModify")
    @ResponseBody
    public String modifyStock(@RequestPart(value="stockInfo") StockInfo stockInfo){
        log.info("stockInfo: {}", stockInfo);
        adStockService.modifyStock(stockInfo);

        return "admin/pharmStock/stockSearchList";
    }


}
