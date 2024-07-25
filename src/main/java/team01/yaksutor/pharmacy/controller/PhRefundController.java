package team01.yaksutor.pharmacy.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team01.yaksutor.dto.*;
import team01.yaksutor.pharmacy.mapper.PhRefundMapper;
import team01.yaksutor.pharmacy.service.PhRefundService;

import java.util.List;

@Controller
@RequestMapping("/pharm")
@RequiredArgsConstructor
@Slf4j
public class PhRefundController {
    private final HttpServletRequest request;
    private final PhRefundMapper phRefundMapper;
    private final PhRefundService phRefundService;

    /**
     * 내 주문 반품등록 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @param orderCode
     * @return
     */
    @GetMapping("/myRefundInsert")
    public String myRefundInsert(Model model,
                                 @RequestParam(value="orderCode") String orderCode) {

        List<OrderDetailForRefund> ODList = phRefundMapper.getOrderDetailList(orderCode);
        Order orderInfo = phRefundMapper.getOrderByOCode(orderCode);
        log.info("orderInfo = {}", orderInfo);
        log.info("ODList = {}", ODList);

        model.addAttribute("ODList", ODList);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("title", "내 주문 반품 신청");
        model.addAttribute("content", "내 주문 반품 신청");

        return "user/pharmacy/refund/myRefundInsert";
    }

    /**
     * 내 반품 목록 페이지 맵핑
     * 작성자: 길범진
     * @param model
     * @return
     */
    @GetMapping("/myRefundSearchList")
    public String myRefundSearchList(Model model) {
        String sid = request.getSession().getAttribute("S_ID").toString();
        List<Refund> refundList = phRefundMapper.getRefundList(sid);
        log.info("refundList: {}", refundList);

        model.addAttribute("refundList", refundList);
        model.addAttribute("title", "내 반품 신청목록");
        model.addAttribute("content", "내 반품 신청목록");

        return "user/pharmacy/refund/myRefundSearchList";
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
        log.info("refundCode: {}", refundCode);
        List<Refund> refundDetailList = phRefundMapper.getRefundDetailList(refundCode);
        log.info("refundDetailList: {}", refundDetailList);
        Refund refundInfo = phRefundMapper.getRefundInfo(refundCode);

        model.addAttribute("refundInfo", refundInfo);
        model.addAttribute("refundDetailList", refundDetailList);
        model.addAttribute("title", "내 반품 상세");
        model.addAttribute("content", "내 반품 상세");

        return "user/pharmacy/refund/myRefundDetailView";
    }

    /**
     * 내 반품 등록 ajax요청
     * 작성자: 길범진
     * @param refundInfo
     * @return
     */
    @PostMapping("/myRefundInsert")
    @ResponseBody
    public String myRefundInsert(@RequestBody RequestRefundInfo refundInfo){
        log.info("refundInfo = {}", refundInfo);
        phRefundService.insertRefund(refundInfo);

        return "redirect:/pharm/myRefundSearchList";
    }

    /**
     * 내 반품 취소 ajax요청
     * 작성자: 길범진
     * @param refundCode
     * @return
     */
    @PostMapping("/cancleRefund")
    @ResponseBody
    public String cancleRefund(@RequestBody Refund refundCode){
        log.info("refundCode: {}", refundCode);
        String value = refundCode.getRefundCode();
        phRefundService.cancleRefund(value);

        return "redirect:/pharm/myRefundSearchList";
    }
}
