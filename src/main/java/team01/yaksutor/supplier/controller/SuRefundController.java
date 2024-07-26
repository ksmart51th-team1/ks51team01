package team01.yaksutor.supplier.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.dto.Refund;
import team01.yaksutor.dto.Shipping;
import team01.yaksutor.supplier.mapper.SuRefundMapper;
import team01.yaksutor.supplier.service.SuRefundService;

import java.util.List;

@Controller
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Slf4j
public class SuRefundController {
    private final SuRefundMapper suRefundMapper;
    private final HttpServletRequest request;

    /**
     * 내 반품 목록 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @return
     */
    @GetMapping("/myRefundSearchList")
    public String myRefundSearchList(Model model) {
        String sid = request.getSession().getAttribute("S_ID").toString();
        String suppCode = suRefundMapper.getSuppCodeById(sid);
        List<Refund> refundList = suRefundMapper.getRefundListBySuppCode(suppCode);

        model.addAttribute("refundList", refundList);
        model.addAttribute("title", "나의 반품 리스트");
        model.addAttribute("content", "나의 반품 리스트");

        return "user/supplier/refund/myRefundSearchList";
    }

    /**
     * 내 반품 상세 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @param refundCode
     * @return
     */
    @GetMapping("/myRefundDetailView")
    public String myRefundDetailView(Model model,
                                     @RequestParam(value="refundCode") String refundCode) {
        String sid = request.getSession().getAttribute("S_ID").toString();
        String suppCode = suRefundMapper.getSuppCodeById(sid);
        List<Refund> refundList = suRefundMapper.getRefundDetailByRefundCode(refundCode, suppCode);
        Refund refund = suRefundMapper.getRefundTotal(refundCode);
        String oCode = refund.getOCode();
        String paymentMethod = suRefundMapper.getPaymentMethod(oCode);
        Shipping ship = suRefundMapper.getShippingDetail(refundCode);

        model.addAttribute("ship", ship);
        model.addAttribute("refund", refund);
        model.addAttribute("paymentMethod", paymentMethod);
        model.addAttribute("refundList", refundList);
        model.addAttribute("title", "나의 반품 상세");
        model.addAttribute("content", "나의 반품 상세");

        return "user/supplier/refund/myRefundDetailView";
    }

    /**
     * 내 반품 배송 등록 ajax요청
     * 작성자: 길범진
     * @param ship
     * @return
     */
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
        suRefundMapper.insertShipping(ship);

        return "redirect:/supplier/myRefundSearchList";
    }

    /**
     * 내 반품 상태 -> '반품완료'로 변경 ajax요청
     * 작성자: 길범진
     * @param ship
     * @return
     */
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
        Shipping shipInfo = suRefundMapper.getShippingDetail(shipCode);
        String refundCode = shipInfo.getRefundCode();

        suRefundMapper.updateRefund(refundCode);
        suRefundMapper.updateShipping(ship);
        return "redirect:/supplier/myRefundSearchList";
    }

}
