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
public class SuOrderController {

    @GetMapping("/myOrderSearchList")
    public String myOrderSearchList(Model model) {

        model.addAttribute("title", "내 주문 목록");
        model.addAttribute("content", "내 주문 목록");

        return "/user/supplier/order/myOrderSearchList";
    }

    @GetMapping("/myOrderDetailView")
    public String myOrderDetailView(Model model) {

        model.addAttribute("title", "내 주문 상세");
        model.addAttribute("content", "내 주문 상세");

        return "/user/supplier/order/myOrderDetailView";
    }

    @GetMapping("/myOrderDelete")
    public String myOrderDelete(Model model) {

        model.addAttribute("title", "내 주문 삭제");
        model.addAttribute("content", "내 주문 삭제");

        return "/user/supplier/order/myOrderDelete";
    }
}
