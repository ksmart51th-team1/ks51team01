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
public class OrderController {

    @GetMapping("/addShoppingCart")
    public String addShoppingCart(Model model) {

        model.addAttribute("title", "장바구니에 추가하기");
        model.addAttribute("content", "장바구니에 추가하기");

        return "admin/order/addShoppingCart";
    }

    @GetMapping("/orderInsert")
    public String orderInsert(Model model) {

        model.addAttribute("title", "주문 등록");
        model.addAttribute("content", "주문 등록");

        return "admin/order/orderInsert";
    }

    @GetMapping("/orderSearchList")
    public String orderSearchList(Model model) {

        model.addAttribute("title", "주문 목록");
        model.addAttribute("content", "주문 목록");

        return "admin/order/orderSearchList";
    }

    @GetMapping("/orderDelete")
    public String orderDelete(Model model) {

        model.addAttribute("title", "주문 삭제");
        model.addAttribute("content", "주문 삭제");

        return "admin/order/orderDelete";
    }

    @GetMapping("/orderDetailView")
    public String orderDetailView(Model model) {

        model.addAttribute("title", "주문 상세");
        model.addAttribute("content", "주문 상세");

        return "admin/order/orderDetailView";
    }
}
