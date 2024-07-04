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
public class AdRefundController {

    @GetMapping("/refundRequest")
    public String refundRequest(Model model) {

        model.addAttribute("title", "반품 신청");
        model.addAttribute("content", "반품 신청");

        return "admin/refund/refundRequest";
    }

    @GetMapping("/refundSearchList")
    public String refundSearchList(Model model) {

        model.addAttribute("title", "반품 신청 리스트");
        model.addAttribute("content", "반품 신청 리스트");

        return "admin/refund/refundSearchList";
    }

    @GetMapping("/refundDetailView")
    public String refundDetailView(Model model) {

        model.addAttribute("title", "반품 상세");
        model.addAttribute("content", "반품 상세");

        return "admin/refund/refundDetailView";
    }

    @GetMapping("/refundDelete")
    public String refundDelete(Model model) {

        model.addAttribute("title", "반품 신청 취소");
        model.addAttribute("content", "반품 신청 취소");

        return "admin/refund/refundDelete";
    }
}
