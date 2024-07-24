package team01.yaksutor.admin.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.admin.mapper.AdRefundMapper;
import team01.yaksutor.dto.Refund;
import team01.yaksutor.dto.RefundDetail;
import team01.yaksutor.dto.Shipping;
import team01.yaksutor.supplier.mapper.SuShippingMapper;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdRefundController {
    private final HttpServletRequest request;
    private final AdRefundMapper adRefundMapper;

    @GetMapping("/refundRequest")
    public String refundRequest(Model model) {

        model.addAttribute("title", "반품 신청");
        model.addAttribute("content", "반품 신청");

        return "admin/refund/refundRequest";
    }

    @GetMapping("/refundSearchList")
    public String refundSearchList(Model model) {
        List<Refund> refundList = adRefundMapper.getRefundList();

        model.addAttribute("refundList", refundList);
        model.addAttribute("title", "반품 신청 리스트");
        model.addAttribute("content", "반품 신청 리스트");

        return "admin/refund/refundSearchList";
    }

    @GetMapping("/refundDetailView")
    public String refundDetailView(Model model,
                                   @RequestParam(value="refundCode") String refundCode) {
        List<Refund> refundDetailList = adRefundMapper.getRefundDetailList(refundCode);
        Refund refund = adRefundMapper.getRefundTotal(refundCode);
        String oCode = refund.getOCode();
        String paymentMethod = adRefundMapper.getPaymentMethod(oCode);
        Shipping ship = adRefundMapper.getShippingDetail(refundCode);

        model.addAttribute("ship", ship);
        model.addAttribute("refund", refund);
        model.addAttribute("paymentMethod", paymentMethod);
        model.addAttribute("refundDetailList", refundDetailList);
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

    @PostMapping("/myRefundShip")
    @ResponseBody
    public String myRefundShip(@RequestBody Shipping ship){
        log.info("ship: {}", ship);
        String re = "반품";
        ship.setReturnOrderStatus(re);
        String deliState = "배송준비중";
        ship.setDeliveryState(deliState);
        ship.setDeliveryLocation(deliState);
        log.info("new ship: {}", ship);
        //adRefundMapper.insertShipping(ship);

        return "redirect:/supplier/myRefundSearchList";
    }

    @PostMapping("/refundClear")
    @ResponseBody
    public String updateForClear(@RequestBody Shipping ship){
        log.info("ship: {}", ship);

        String deliClear = "반품완료";
        ship.setDeliveryState(deliClear);
        String locationClear = "반품완료";
        ship.setDeliveryLocation(locationClear);
        log.info("ship: {}", ship);
        String shipCode = ship.getShipCode();
        Shipping shipInfo = adRefundMapper.getShippingDetail(shipCode);
        String refundCode = shipInfo.getRefundCode();

        //adRefundMapper.updateRefund(refundCode);
        //adRefundMapper.updateShipping(ship);
        return "redirect:/supplier/myRefundSearchList";
    }
}
