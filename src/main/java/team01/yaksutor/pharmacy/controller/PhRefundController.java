package team01.yaksutor.pharmacy.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team01.yaksutor.dto.Refund;
import team01.yaksutor.dto.RefundDetail;
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

    @GetMapping("/myRefundInsert")
    public String myRefundInsert(Model model) {

        model.addAttribute("title", "내 주문 반품 신청");
        model.addAttribute("content", "내 주문 반품 신청");

        return "user/pharmacy/refund/myRefundInsert";
    }

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
}
