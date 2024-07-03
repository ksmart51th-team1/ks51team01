package team01.yaksutor.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdStockController {
    @GetMapping("/stockInsert")
    public String stockInsert(Model model) {

        model.addAttribute("title", "재고 등록");
        model.addAttribute("content", "재고 등록");

        return "admin/pharmStock/stockInsert";
    }

    @GetMapping("/stockSearchList")
    public String stockSearchList(Model model) {

        model.addAttribute("title", "재고 검색");
        model.addAttribute("content", "재고 검색");

        return "admin/pharmStock/stockSearchList";
    }

    @GetMapping("/stockModify")
    public String stockModify(Model model) {

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
}
