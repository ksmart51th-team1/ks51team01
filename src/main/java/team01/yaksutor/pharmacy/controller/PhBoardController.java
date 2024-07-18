package team01.yaksutor.pharmacy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team01.yaksutor.pharmacy.dto.*;
import team01.yaksutor.pharmacy.service.PhBoardService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PhBoardController {

    private final PhBoardService phBoardService;

    /* ------------------------------------------------------------
    *                    커뮤니티 + 댓글
    *  ------------------------------------------------------------*/

    // 커뮤니티 + 댓글 조회 (사용자)
    @GetMapping("/pharm/board")
    public String getBoardList(Model model) {
        List<Board> boardList = phBoardService.getBoardList();
        List<Repl> replList = phBoardService.getReplList();
        model.addAttribute("boardList", boardList);
        model.addAttribute("replList", replList);

        return "user/pharmacy/board/boardList";
    }
    // 커뮤니티 + 댓글 조회 (관리자)
    @GetMapping("/admin/boardList")
    public String getBoardList2(Model model) {
        List<Board> boardList = phBoardService.getBoardList();
        List<Repl> replList = phBoardService.getReplList();
        model.addAttribute("boardList", boardList);
        model.addAttribute("replList", replList);

        return "admin/board/boardList";
    }
    // 커뮤니티 + 댓글 조회 (납품자)
    @GetMapping("/supplier/boardList")
    public String getBoardList3(Model model) {
        List<Board> boardList = phBoardService.getBoardList();
        List<Repl> replList = phBoardService.getReplList();
        model.addAttribute("boardList", boardList);
        model.addAttribute("replList", replList);

        return "user/supplier/board/boardList";
    }
    // 커뮤니티 등록 (사용자)
    @PostMapping("/pharm/boardAdd")
    public String addBoard(Board board){
        phBoardService.addBoard(board);
        return "redirect:/pharm/board";
    }
    // 커뮤니티 등록 (납품자)
    @PostMapping("/supplier/boardAdd")
    public String addBoard3(Board board){
        phBoardService.addBoard(board);
        return "redirect:/supplier/boardList";

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
    // 커뮤니티 수정 (사용자)
    @PostMapping("/pharm/board/modify")
    public String modifyBoard2(Board board){
        phBoardService.modifyBoard(board);
        return "redirect:/pharm/board";
    }
    // 커뮤니티 수정 (납품자)
    @PostMapping("/supplier/board/modify")
    public String modifyBoard3(Board board){
        phBoardService.modifyBoard(board);
        return "redirect:/supplier/boardList";
    }
    // 커뮤니티 수정 (관리자)
    @PostMapping("/admin/boardList/modify")
    public String modifyBoard(Board board){
        phBoardService.modifyBoard(board);
        return "redirect:/admin/boardList";
    }
    // 커뮤니티 삭제 (사용자)
    @PostMapping("/pharm/board/delete")
    public String deleteBoard2(@RequestParam String boardCode){
        phBoardService.deleteBoard(boardCode);
        return "redirect:/pharm/board";
    }
    // 커뮤니티 삭제 (납품자)
    @PostMapping("/supplier/board/delete")
    public String deleteBoard3(@RequestParam String boardCode){
        phBoardService.deleteBoard(boardCode);
        return "redirect:/supplier/boardList";
    }
    // 커뮤니티 삭제 (관리자)
    @PostMapping("/admin/boardList/delete")
    public String deleteBoard(@RequestParam String boardCode){
        phBoardService.deleteBoard(boardCode);
        return "redirect:/admin/boardList";
    }
    // 커뮤니티 댓글 등록 (사용자)
    @PostMapping("/pharm/replAdd")
    public String addRepl(Repl repl){
        phBoardService.addRepl(repl);
        return "redirect:/pharm/board";

    }
    // 커뮤니티 댓글 등록 (납품자)
    @PostMapping("/supplier/replAdd")
    public String addRepl2(Repl repl){
        phBoardService.addRepl(repl);
        return "redirect:/supplier/boardList";
    }
    // 커뮤니티 댓글 등록 (관리자)
    @PostMapping("/admin/replAdd")
    public String addRepl3(Repl repl){
        phBoardService.addRepl(repl);
        return "redirect:/admin/boardList";

    }
    // 커뮤니티 댓글 수정 (관리자)
    @PostMapping("/admin/boardList/modifyRepl")
    public String modifyRepl(Repl repl){
        phBoardService.modifyRepl(repl);
        return "redirect:/admin/boardList";
    }
    // 커뮤니티 댓글 삭제 (사용자)
    @PostMapping("/pharm/replDelete")
    public String deleteRepl(@RequestParam String replNum){
        phBoardService.deleteRepl(replNum);
        return "redirect:/pharm/board";
    }
    // 커뮤니티 댓글 삭제 (납품자)
    @PostMapping("/supplier/replDelete")
    public String deleteRepl2(@RequestParam String replNum){
        phBoardService.deleteRepl(replNum);
        return "redirect:/supplier/boardList";
    }
    // 커뮤니티 댓글 삭제 (관리자)
    @PostMapping("/admin/boardList/deleteRepl")
    public String deleteRepl3(@RequestParam String replNum){
        phBoardService.deleteRepl(replNum);
        return "redirect:/admin/boardList";
    }

    /* ------------------------------------------------------------
     *                    자주하는 질문
     *  ------------------------------------------------------------*/

    // 자주하는 질문 조회 (사용자)
    @GetMapping("/pharm/faq")
    public String getQuestionCenterList(Model model) {
        List<QuestionCenter> questionCenterList = phBoardService.getQuestionCenterList();

        model.addAttribute("questionCenterList", questionCenterList);
        return "user/pharmacy/board/faq";
    }
    // 자주하는 질문 조회 (납품자)
    @GetMapping("/supplier/faq")
    public String getQuestionCenterList3(Model model) {
        List<QuestionCenter> questionCenterList = phBoardService.getQuestionCenterList();

        model.addAttribute("questionCenterList", questionCenterList);
        return "user/supplier/board/faq";
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
    // 자주하는 질문 삭제 (관리자)
    @PostMapping("/admin/faqList/delete")
    public String deleteQuestionCenter(@RequestParam String questionNum){
        phBoardService.deleteQuestionCenter(questionNum);
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
    // 공지사항 조회 (납품자)
    @GetMapping("/supplier/notice")
    public String getNoticeList3(Model model) {
        List<Notice> noticeList = phBoardService.getNoticeList();
        model.addAttribute("noticeList", noticeList);
        return "user/supplier/board/notice";
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
    // 공지사항 삭제 (관리자)
    @PostMapping("/admin/noticeList/delete")
    public String deleteNotice(@RequestParam String noticeNum){
        phBoardService.deleteNotice(noticeNum);
        return "redirect:/admin/noticeList";
    }


    /*문의*/
    // 조회

    @GetMapping("/admin/qnaList")
    public String qnaList(Model model) {
        List<Qna> qnaList = phBoardService.getQnaList();
        List<QnaReply> qnaReplyList = phBoardService.getQnaReplyList();
        model.addAttribute("qnaList", qnaList);
        model.addAttribute("qnaReplyList", qnaReplyList);
        model.addAttribute("title", "문의내역");

        return "admin/qna/qnaList";
    }
    // 추가
    @PostMapping("/admin/qna/add")
    public String addQna(Qna qna){
        phBoardService.addQna(qna);
        return "redirect:/admin/qnaList";
    }
    @GetMapping("/admin/qnaAdd")
    public String qnaAdd(){
        return "admin/qna/qnaAdd";
    }
    // 삭제
    @PostMapping("/admin/qna/delete")
    public String deleteQna(@RequestParam String qseq){
        phBoardService.deleteQna(qseq);
        return "redirect:/admin/qnaList";
    }





}
