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
public class PhRefundController {

    @GetMapping("/myRefundInsert")
    public String myRefundInsert(Model model) {

        model.addAttribute("title", "내 주문 반품 신청");
        model.addAttribute("content", "내 주문 반품 신청");

        return "user/pharmacy/refund/myRefundInsert";
    }

    @GetMapping("/myRefundSearchList")
    public String myRefundSearchList(Model model) {

        model.addAttribute("title", "내 반품 신청목록");
        model.addAttribute("content", "내 반품 신청목록");

        return "user/pharmacy/refund/myRefundSearchList";
    }

    @GetMapping("/myRefundDetailView")
    public String myRefundDetailView(Model model) {

        model.addAttribute("title", "내 반품 상세");
        model.addAttribute("content", "내 반품 상세");

        return "user/pharmacy/refund/myRefundDetailView";
    }
}
