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
public class PhOrderController {

    @GetMapping("/myOrderDetailView")
    public String myOrderDetailView(Model model) {

        model.addAttribute("title", "주문 상세");
        model.addAttribute("content", "주문 상세");

        return "user/pharmacy/myPage/myOrderDetailView";
    }

    @GetMapping("/paymentMethod")
    public String paymentMethod(Model model) {

        model.addAttribute("title", "결제수단 등록");
        model.addAttribute("content", "결제수단 등록");

        return "user/pharmacy/order/paymentMethod";
    }
}
