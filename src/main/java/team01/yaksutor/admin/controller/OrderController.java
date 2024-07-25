package team01.yaksutor.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.admin.service.AdOrderService;
import team01.yaksutor.common.service.MemberService;
import team01.yaksutor.dto.Member;
import team01.yaksutor.dto.Order;
import team01.yaksutor.dto.SellMedicine;
import team01.yaksutor.dto.ShoppingCart;
import team01.yaksutor.pharmacy.service.PhMedicineService;
import team01.yaksutor.pharmacy.service.PhShoppingCartService;
import team01.yaksutor.supplier.service.SuOrderService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final MemberService memberService;
    private final AdOrderService adOrderService;
    private final PhMedicineService phMedicineService;
    private final PhShoppingCartService phShoppingCartService;
    private final SuOrderService suOrderService;

    @GetMapping("/addShoppingCart")
    public String addShoppingCart(Model model) {
        List<Member> memberList = memberService.getMemberList();
        List<SellMedicine> sellMedicineList = phMedicineService.getSellMedicineList();
        List<ShoppingCart> shoppingCartList = phShoppingCartService.getShoppingCartList();


        model.addAttribute("title", "장바구니에 추가하기");
        model.addAttribute("content", "장바구니에 추가하기");
        model.addAttribute("memberList", memberList);
        model.addAttribute("sellMedicineList", sellMedicineList);
        model.addAttribute("shoppingCartList", shoppingCartList);

        return "admin/order/addShoppingCart";
    }


    /**
     * 관리자화면에서 전체 주문 목록 조회
     * @param model
     * @return
     */
    @GetMapping("/orderSearchList")
    public String orderSearchList(Model model) {
        List<Order> orderList = adOrderService.getOrderList();

        model.addAttribute("orderList", orderList);
        model.addAttribute("title", "주문 목록");
        model.addAttribute("content", "주문 목록");

        return "admin/order/orderSearchList";
    }

    @PostMapping("/deleteOrder")
    @ResponseBody
    public boolean deleteOrder(@RequestBody Map<String, Object> payload) {
        boolean isDeleted;
        String oCode = (String) payload.get("oCode");
        Order order = new Order();
        order.setOCode(oCode);

        isDeleted = adOrderService.deleteOrderByoCode(order.getOCode());

        return isDeleted;
    }


    /**
     * 주문목록에서 특정주문 삭제
     * @param oCode
     * @param model
     * @return
     */
    @GetMapping("/orderDelete")
    public String orderDelete(@RequestParam(value = "oCode") String oCode, Model model) {

        model.addAttribute("oCode", oCode);
        model.addAttribute("title", "주문 삭제");
        model.addAttribute("content", "주문 삭제");

        return "admin/order/orderDelete";
    }

    @GetMapping("/orderDetailView")
    public String orderDetailView(@RequestParam(value = "oCode") String oCode,
                                  @RequestParam(value = "purchaseState") String purchaseState,
                                  Model model) {

        List<Order> orderList = adOrderService.getOrderDetailListByoCode(oCode);
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
        model.addAttribute("title", "주문 상세");
        model.addAttribute("content", "주문 상세");

        return "admin/order/orderDetailView";
    }
}
