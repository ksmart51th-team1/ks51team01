package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import team01.yaksutor.pharmacy.dto.Board;
import team01.yaksutor.pharmacy.dto.Notice;
import team01.yaksutor.pharmacy.dto.QuestionCenter;
import team01.yaksutor.pharmacy.service.PhBoardService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PhBoardController {

    private final PhBoardService phBoardService;

    /* ------------------커뮤니티------------------------------*/

    // 커뮤니티 조회 (사용자)
    @GetMapping("/pharm/board")
    public String getBoardList(Model model) {
        List<Board> boardList = phBoardService.getBoardList();
        model.addAttribute("boardList", boardList);

        return "user/pharmacy/board/boardList";
    }
    // 커뮤니티 조회 (관리자)
    @GetMapping("/admin/boardList")
    public String getBoardList2(Model model) {
        List<Board> boardList = phBoardService.getBoardList();
        model.addAttribute("boardList", boardList);

        return "admin/board/boardList";
    }
    // 커뮤니티 등록 (사용자)
    @PostMapping("/pharm/boardAdd")
    public String addBoard(Board board){
        phBoardService.addBoard(board);
        return "redirect:/pharm/board";

    }
    // 커뮤니티 등록 (관리자)
    @GetMapping("/admin/boardAdd")
    public String addBoard(Model model) {
        List<Board> boardList = phBoardService.getBoardList();

        model.addAttribute("boardList", boardList);
        return "admin/board/boardAdd";
    }
    @PostMapping("/admin/boardAdd")
    public String addBoard2(Board board){
        phBoardService.addBoard(board);
        return "redirect:/admin/boardList";

    }
    // 커뮤니티 수정 (관리자)
    @PostMapping("/admin/boardList/modify")
    public String modifyBoard(Board board){
        phBoardService.modifyBoard(board);
        return "redirect:/admin/boardList";
    }


    /* ------------------자주하는 질문------------------------------*/

    // 자주하는 질문 조회 (사용자)
    @GetMapping("/pharm/faq")
    public String getQuestionCenterList(Model model) {
        List<QuestionCenter> questionCenterList = phBoardService.getQuestionCenterList();

        model.addAttribute("questionCenterList", questionCenterList);
        return "user/pharmacy/board/faq";
    }
    // 자주하는 질문 조회 (관리자)
    @GetMapping("/admin/faqList")
    public String getQuestionCenterList2(Model model) {
        List<QuestionCenter> questionCenterList = phBoardService.getQuestionCenterList();

        model.addAttribute("questionCenterList", questionCenterList);
        return "admin/questionCenter/faqList";
    }
    // 자주하는 질문 등록 (관리자)
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
    // 자주하는 질문 수정 (관리자)
    @PostMapping("/admin/faqList/modify")
    public String modifyQuestionCenter(QuestionCenter questionCenter){
        phBoardService.modifyQuestionCenter(questionCenter);
        return "redirect:/admin/faqList";
    }


    /* ------------------공지사항------------------------------*/

    // 공지사항 조회 (사용자)
    @GetMapping("/pharm/notice")
    public String getNoticeList(Model model) {
    List<Notice> noticeList = phBoardService.getNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "user/pharmacy/board/notice";
    }
    // 공지사항 조회 (관리자)
    @GetMapping("/admin/noticeList")
    public String getNoticeList2(Model model) {
        List<Notice> noticeList = phBoardService.getNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "admin/notice/noticeList";
    }

    // 공지사항 추가 (관리자)
    @GetMapping("/admin/noticeAdd")
    public String addNotice(Model model){
        List<Notice> noticeList = phBoardService.getNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "admin/notice/noticeAdd";
    }
    @PostMapping("/admin/noticeAdd")
    public String addNotice(Notice notice){
        phBoardService.addNotice(notice);
        return "redirect:/admin/noticeList";
    }
    // 공지사항 수정 (관리자)
    @PostMapping("/admin/noticeList/modify")
    public String modifyNotice(Notice notice){
        phBoardService.modifyNotice(notice);
        return "redirect:/admin/noticeList";
    }



}
