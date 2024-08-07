package team01.yaksutor.pharmacy.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.common.mapper.MemberMapper;
import team01.yaksutor.dto.Member;
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
    private final MemberMapper memberMapper;

    /**
     * 주문상세코드를 이용해 주문상세를 삭제하는 기능
     * @param orderDetailCode
     * @return ResponseEntity
     */
    @DeleteMapping("/deleteOrderDetail/{orderDetailCode}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable String orderDetailCode) {
        boolean isDeleted = phOrderService.deleteOrderDetail(orderDetailCode);
        if (isDeleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 장바구니로부터 주문을 생성
     * @param shoppingCart
     * @return ResponseEntity
     */
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

    /**
     * 주문상세 페이지로 이동
     * @param oCode
     * @param perchaseState
     * @param model
     * @return url주소
     */
    @GetMapping("/myOrderDetailView")
    public String myOrderDetailView(@RequestParam("oCode") String oCode,
                                    @RequestParam("perchaseState") String perchaseState,
                                    HttpServletRequest request,
                                    Model model) {

        String sId = (String) request.getSession().getAttribute("S_ID");
        Member memberInfo = memberMapper.getMemberInfoById(sId);
        List<OrderDetail> orderDetailList = phOrderService.getOrderDetailListByOCode(oCode);
        String orderCode = oCode;


        model.addAttribute("perchaseState", perchaseState);
        model.addAttribute("orderCode", orderCode);
        model.addAttribute("title", "주문 상세");
        model.addAttribute("content", "주문 상세");
        model.addAttribute("orderDetailList", orderDetailList);
        model.addAttribute("memberInfo", memberInfo);

        return "user/pharmacy/myPage/myOrderDetailView";
    }

    @GetMapping("/paymentMethod")
    public String paymentMethod(Model model) {

        model.addAttribute("title", "결제수단 등록");
        model.addAttribute("content", "결제수단 등록");

        return "user/pharmacy/order/paymentMethod";
    }
}
