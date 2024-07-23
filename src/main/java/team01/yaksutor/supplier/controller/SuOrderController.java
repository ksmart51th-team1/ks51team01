package team01.yaksutor.supplier.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.dto.Order;
import team01.yaksutor.supplier.service.SuOrderService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Slf4j
public class SuOrderController {

    private final SuOrderService suOrderService;

    @PostMapping("/deleteOrder")
    @ResponseBody
    public boolean deleteOrder(@RequestBody Map<String, Object> payload) {
        boolean isDeleted;
        String oCode = (String) payload.get("oCode");
        Order order = new Order();
        order.setOCode(oCode);

        isDeleted = suOrderService.deleteOrderByoCode(order.getOCode());

        return isDeleted;
    }

    @GetMapping("/myOrderSearchList")
    public String myOrderSearchList(Model model) {
        List<Order> orderList = suOrderService.getOrderListById();
        model.addAttribute("title", "내 주문 목록");
        model.addAttribute("content", "내 주문 목록");
        model.addAttribute("orderList", orderList);

        return "/user/supplier/order/myOrderSearchList";
    }

    @GetMapping("/myOrderDetailView")
    public String myOrderDetailView(@RequestParam(value = "oCode") String oCode,
                                    @RequestParam(value = "purchaseState") String purchaseState,
                                    Model model) {

        List<Order> orderList = suOrderService.getOrderDetailByOderCode(oCode);
        Order order = new Order();
        AtomicInteger price = new AtomicInteger();
        orderList.forEach(item -> {
            order.setPurchaseState(item.getPurchaseState());
            order.setPaymentMethod(item.getPaymentMethod());
            price.addAndGet(item.getOrderDetail().getOrderPrice());
        });
        order.setOrderTotalPrice(price.get());
        model.addAttribute("purchaseState", purchaseState);
        model.addAttribute("order", order);
        model.addAttribute("orderList", orderList);
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
