package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pharm")
@RequiredArgsConstructor
@Slf4j
public class PhMedicineController {

    @GetMapping("/order/orderMedicine")
    public String medicineSearchList(Model model) {
        model.addAttribute("title", "의약품 검색 주문");
        model.addAttribute("content", "의약품 목록");
        return "user/pharmacy/order/orderMedicine";
    }

    @GetMapping("/order/orderMedicineDetailView")
    public String orderMedicineDetailView(Model model) {
        model.addAttribute("title", "의약품 상세");
        model.addAttribute("content", "의약품 상세");
        return "user/pharmacy/order/orderMedicineDetailView";
    }

}
