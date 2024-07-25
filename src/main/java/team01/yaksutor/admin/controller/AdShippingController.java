package team01.yaksutor.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.admin.mapper.AdShippingMapper;
import team01.yaksutor.admin.service.AdShippingService;
import team01.yaksutor.dto.Refund;
import team01.yaksutor.dto.Shipping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdShippingController {
    private final AdShippingMapper adShippingMapper;
    private final AdShippingService adShippingService;

    /**
     * 주문 배송 목록 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @return
     */
    @GetMapping("/orderShip")
    public String orderShip(Model model) {
        List<Shipping> shipList = adShippingMapper.getOrderShipList();

        model.addAttribute("shipList", shipList);
        model.addAttribute("title", "주문 배송 목록");
        model.addAttribute("content", "주문 배송 목록");

        return "admin/shipping/ShipSearchList";
    }

    /**
     * 반품 배송 목록 맵핑
     * 작성자: 길범진
     * @param model
     * @return
     */
    @GetMapping("/refundShip")
    public String refundShip(Model model) {
        List<Shipping> shipList = adShippingMapper.getRefundShipList();

        model.addAttribute("shipList", shipList);
        model.addAttribute("title", "반품 배송 목록");
        model.addAttribute("content", "반품 배송 목록");

        return "admin/shipping/RefundShipList";
    }

    /**
     * 반품 취소 ajax요청
     * 작성자: 길범진
     * @param model
     * @param refund
     * @return
     */
    @PostMapping("/cancleRefund")
    @ResponseBody
    public String cancelRefund(Model model,
                               @RequestBody Refund refund) {
        log.info("refundCode: {}", refund);
        String refundCode = refund.getRefundCode();
        adShippingService.cancleRefund(refundCode);


        return "redirect:/admin/shipping/RefundShipList";
    }
}
