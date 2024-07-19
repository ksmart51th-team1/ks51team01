package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team01.yaksutor.dto.Member;
import team01.yaksutor.dto.Order;
import team01.yaksutor.pharmacy.dto.Qna;
import team01.yaksutor.pharmacy.dto.QnaReply;
import team01.yaksutor.pharmacy.service.PhBoardService;
import team01.yaksutor.pharmacy.service.PhOrderService;
import team01.yaksutor.pharmacy.service.phMyPageService;

import java.util.List;

@RequestMapping("/pharm")
@RequiredArgsConstructor
@Controller
public class PharmacyController {

    private static final Logger log = LoggerFactory.getLogger(PharmacyController.class);
    private final phMyPageService phMyPageService;
    private final PhBoardService phBoardService;
    private final PhOrderService phOrderService;

    @GetMapping("/main")
    public String pharmMain(Model model) {
        model.addAttribute("content", "유저 메인.");
        return "user/pharmacy/pharmMain";
    }

    @GetMapping("/contact")
    public String contact() {

        return "user/pharmacy/board/contact";
    }

    //  ====================
    //         1:1문의
    // ======================

    // 조회
    @GetMapping("/qna")
    public String qna(Model model) {
        List<Qna> qnaList = phBoardService.getQnaList();
        model.addAttribute("qnaList", qnaList);
        model.addAttribute("title", "문의하기");
        return "user/pharmacy/board/qna";
    }
    @GetMapping("/qnaList")
    public String qnaList(Model model) {
        List<Qna> qnaList = phBoardService.getQnaList();
        List<QnaReply> qnaReplyList = phBoardService.getQnaReplyList();
        model.addAttribute("qnaList", qnaList);
        model.addAttribute("qnaReplyList", qnaReplyList);
        model.addAttribute("title", "문의내역");

        return "user/pharmacy/board/qnaList";
    }

    // 추가
    @PostMapping("/qna/add")
    public String addQna(Qna qna){
        phBoardService.addQna(qna);
        return "redirect:/pharm/qna";
    }
    // 삭제
    @PostMapping("/qna/delete")
    public String deleteQna(@RequestParam String qseq){
        phBoardService.deleteQna(qseq);
        return "redirect:/pharm/qnaList";
    }





    @GetMapping("/myPage")
    public String myPage(Model model) {
        Member member = phMyPageService.getMeberInfoById();
        model.addAttribute("member",member);
        return "user/pharmacy/myPage/myPage";
    }
/*    @GetMapping("/cart")
    public String cart() {

        return "user/pharmacy/order/cart";
    }*/

    @GetMapping("/myOrderList")
    public String myOderList(Model model) {
        List<Order> orderList = phOrderService.getOrderListById();
        model.addAttribute("orderList",orderList);
        return "user/pharmacy/myPage/myOrderList";
    }
    @GetMapping("/myDelivery")
    public String myDelivery() {

        return "user/pharmacy/myPage/myDelivery";
    }
    @GetMapping("/liveOrder")
    public String liveOrder() {

        return "user/pharmacy/order/liveOrder";
    }
    @GetMapping("/liveOrderList")
    public String liveOrderList() {

        return "user/pharmacy/order/liveOrderList";
    }
    @GetMapping("/hotDeal")
    public String hotDeal() {

        return "user/pharmacy/order/hotDeal";
    }


}
