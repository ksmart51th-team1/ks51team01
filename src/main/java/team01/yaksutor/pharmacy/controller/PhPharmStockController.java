package team01.yaksutor.pharmacy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/pharm")
@Controller
public class PhPharmStockController {

    @GetMapping("/checkMedi")
    public String pharmStrock(Model model) {
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

        model.addAttribute("title", "재고 목록");
        model.addAttribute("content", "재고 목록");

        return "user/pharmacy/pharmstock/myStockSearchList";
    }

    @GetMapping("/myStockModify")
    public String myStockModify(Model model) {

        model.addAttribute("title", "재고 수정");
        model.addAttribute("content", "재고 수정");

        return "user/pharmacy/pharmstock/myStockModify";
    }

    @GetMapping("/myStockDelete")
    public String myStockDelete(Model model) {

        model.addAttribute("title", "재고 삭제");
        model.addAttribute("content", "재고 삭제");

        return "user/pharmacy/pharmstock/myStockDelete";
    }
}
