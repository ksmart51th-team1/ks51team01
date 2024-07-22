package team01.yaksutor.supplier.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team01.yaksutor.dto.Order;
import team01.yaksutor.supplier.service.SuOrderService;

import java.util.List;

@Controller
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Slf4j
public class SuOrderController {

    private final SuOrderService suOrderService;

    @GetMapping("/myOrderSearchList")
    public String myOrderSearchList(Model model) {
        List<Order> orderList = suOrderService.getOrderListById();
        model.addAttribute("title", "내 주문 목록");
        model.addAttribute("content", "내 주문 목록");
        model.addAttribute("orderList", orderList);

        return "/user/supplier/order/myOrderSearchList";
    }

    @GetMapping("/myOrderDetailView")
    public String myOrderDetailView(Model model) {

        model.addAttribute("title", "내 주문 상세");
        model.addAttribute("content", "내 주문 상세");

        return "/user/supplier/order/myOrderDetailView";
    }

    @GetMapping("/myOrderDelete")
    public String myOrderDelete(@RequestParam(value = "oCode") String oCode, Model model) {

        model.addAttribute("oCode", oCode);
        model.addAttribute("title", "내 주문 삭제");
        model.addAttribute("content", "내 주문 삭제");

        return "/user/supplier/order/myOrderDelete";
    }
}
