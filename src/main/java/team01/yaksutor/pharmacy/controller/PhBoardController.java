package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import team01.yaksutor.pharmacy.dto.Board;
import team01.yaksutor.pharmacy.dto.QuestionCenter;
import team01.yaksutor.pharmacy.service.PhBoardService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PhBoardController {

    private final PhBoardService phBoardService;

    @GetMapping("/pharm/board")
    public String getBoardList(Model model) {
        List<Board> boardList = phBoardService.getBoardList();
        log.info("boardList : {}", boardList);

        model.addAttribute("boardList", boardList);


        return "user/pharmacy/board/boardList";
    }

    @GetMapping("/pharm/faq")
    public String getQuestionCenterList(Model model) {
        List<QuestionCenter> questionCenterList = phBoardService.getQuestionCenterList();

        model.addAttribute("questionCenterList", questionCenterList);
        return "user/pharmacy/board/faq";
    }
    @GetMapping("/admin/faqList")
    public String getQuestionCenterList2(Model model) {
        List<QuestionCenter> questionCenterList = phBoardService.getQuestionCenterList();

        model.addAttribute("questionCenterList", questionCenterList);
        return "admin/questionCenter/faqList";
    }
    @GetMapping("/admin/faqAdd")
    public String addQuestionCenter(Model model) {
        List<QuestionCenter> questionCenterList = phBoardService.getQuestionCenterList();

        model.addAttribute("questionCenterList", questionCenterList);
        return "admin/questionCenter/faqAdd";
    }




    @PostMapping("/admin/faqAdd")
    public String addQuestionCenter(QuestionCenter questionCenter){
        phBoardService.addQuestionCenter(questionCenter);
        return "redirect:/admin/faqList";

    }

}
