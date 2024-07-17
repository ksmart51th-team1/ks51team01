package team01.yaksutor.pharmacy.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.dto.*;
import team01.yaksutor.pharmacy.mapper.PhStockMapper;
import team01.yaksutor.pharmacy.service.PhStockService;

import java.util.List;

@Controller
@RequestMapping("/pharm")
@RequiredArgsConstructor
@Slf4j
public class PhPharmStockController {
    private final PhStockMapper phStockMapper;
    private final PhStockService phStockService;
    private final HttpServletRequest request;


    @GetMapping("/checkMedi")
    public String pharmStrock(Model model) {
        String sid = request.getSession().getAttribute("S_ID").toString();
        String pharCode = phStockMapper.getPharCodeById(sid);
        List<PharmStock> oldStockList = phStockMapper.getOldStockCheck(pharCode);
        log.info("여기보세요: {}", oldStockList);

        model.addAttribute("oldStockList", oldStockList);
        model.addAttribute("title","재고조사");
        return "user/pharmacy/pharmstock/checkMedi";
    }

    @GetMapping("/myStockInsert")
    public String myStockInsert(Model model) {

        model.addAttribute("title", "재고 추가");
        model.addAttribute("content", "재고 추가");

        return "user/pharmacy/pharmstock/myStockInsert";
    }

    @GetMapping("/myStockSearchList")
    public String myStockSearchList(Model model) {
        String sid = request.getSession().getAttribute("S_ID").toString();
        log.info("sid: {}", sid);
        String pharCode = phStockMapper.getPharCodeById(sid);
        List<PharmStock> stockList = phStockMapper.getPharmStockList(pharCode);

        model.addAttribute("stockList", stockList);
        model.addAttribute("title", "재고 목록");
        model.addAttribute("content", "재고 목록");

        return "user/pharmacy/pharmstock/myStockSearchList";
    }

    @GetMapping("/myStockRelease")
    public String myStockRelease(Model model,
                                 @RequestParam(value="stockCode") String stockCode,
                                 @RequestParam(value="mediName") String mediName) {

        String thisStockCode = stockCode;
        String stockQty = phStockMapper.getStockQty(stockCode);
        String thisMedi = mediName;

        model.addAttribute("thisStockCode", thisStockCode);
        model.addAttribute("stockQty", stockQty);
        model.addAttribute("mediName", thisMedi);
        model.addAttribute("title", "출고");
        model.addAttribute("content", "출고");

        return "user/pharmacy/pharmstock/myStockRelease";
    }

    @GetMapping("/myStockHistory")
    public String myStockHistory(Model model) {
        String sid = request.getSession().getAttribute("S_ID").toString();
        String pharCode = phStockMapper.getPharCodeById(sid);
        List<StockHistory> stockHistoryList = phStockMapper.getStockHistory(pharCode);

        model.addAttribute("historyList", stockHistoryList);
        model.addAttribute("title", "입출고 기록");
        model.addAttribute("content", "입출고 기록");

        return "user/pharmacy/pharmstock/myStockHistory";
    }

    @GetMapping("/stockDetailView")
    public String stockDetailView(Model model,
                                  @RequestParam(value="mediCode") String mediCode) {

        StockInfo stockInfo = new StockInfo();
        String sid = request.getSession().getAttribute("S_ID").toString();
        String pharCode = phStockMapper.getPharCodeById(sid);
        stockInfo.setMedicine(phStockMapper.getMedicineByCode(mediCode));
        stockInfo.setPharmStock(phStockMapper.getPharmStockByCode(mediCode, pharCode));
        List<Ingredient> ingrList = phStockMapper.getIngredientByCode(mediCode);
        stockInfo.setIngrList(ingrList);
        stockInfo.setEffiList(phStockMapper.getEfficacyByCode(mediCode));

        log.info("stockInfo: {}", stockInfo);

        model.addAttribute("stockInfo", stockInfo);
        model.addAttribute("title", "재고 상세");
        model.addAttribute("content", "재고 상세");


        return "user/pharmacy/pharmstock/stockDetailView";
    }

    @PostMapping("/myStockInsert")
    @ResponseBody
    public String myStockInsert(@RequestPart(value="stockInfo") StockInfo stockInfo){
        String sid = request.getSession().getAttribute("S_ID").toString();
        log.info("stockInfo: {}", stockInfo);
        phStockService.stockInsert(stockInfo, sid);

        return "user/pharmacy/pharmstock/myStockInsert";
    }

    @PostMapping("/myStockRelease")
    public String myStockRelease(@RequestParam(value="qty") String qty,
                                 @RequestParam(value="stockCode") String stockCode){
        phStockService.stockRelease(qty, stockCode);

        return "user/pharmacy/pharmstock/myStockSearchList";
    }

    @PostMapping("/checkMedi")
    @ResponseBody
    public String checkMedi(@RequestBody StockClearance stockClearance){
        log.info("checkMedi: {}", stockClearance);
        int oldQty = stockClearance.getPreInveQty();
        int newQty = stockClearance.getPostInveQty();
        int abnormalQty = oldQty - newQty;
        abnormalQty = Math.abs(abnormalQty);
        stockClearance.setAdnormalQty(abnormalQty);
        if(abnormalQty == 0) {
            stockClearance.setAdnormalReason("없음");
            stockClearance.setAdnormalChecked("정상");
        } else {
            stockClearance.setAdnormalChecked("비정상");
        }
        log.info("new checkMedi: {}", stockClearance);
        phStockService.stockClearance(stockClearance);

        return "redirect:user/pharmacy/pharmstock/checkMedi";
    }
}
