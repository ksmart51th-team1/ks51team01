package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import team01.yaksutor.dto.OrderDetail;
import team01.yaksutor.dto.ShoppingCart;
import team01.yaksutor.pharmacy.service.PhOrderService;

import java.util.List;

@Controller
@RequestMapping("/pharm")
@RequiredArgsConstructor
@Slf4j
public class PhOrderController {

    private final PhOrderService phOrderService;

    @PostMapping("/OrderPay")
    public ResponseEntity<String> OrderPay(@RequestBody List<ShoppingCart> shoppingCart) {
        log.info(shoppingCart.toString());

        try {
            phOrderService.submitOrder(shoppingCart);
            return ResponseEntity.ok("주문이 성공적으로 등록되었습니다.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("정상적인 주문경로가 아닙니다.");
        }

    }

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
