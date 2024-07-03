package team01.yaksutor.supplier.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Slf4j
public class SuRefundController {

    @GetMapping("/myRefundSearchList")
    public String myRefundSearchList(Model model) {

        model.addAttribute("title", "나의 반품 리스트");
        model.addAttribute("content", "나의 반품 리스트");

        return "user/supplier/refund/myRefundSearchList";
    }

    @GetMapping("/myRefundDetailView")
    public String myRefundDetailView(Model model) {

        model.addAttribute("title", "나의 반품 상세");
        model.addAttribute("content", "나의 반품 상세");

        return "user/supplier/refund/myRefundDetailView";
    }
}
