package team01.yaksutor.pharmacy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.pharmacy.dto.*;
import team01.yaksutor.pharmacy.mapper.PhBoardMapper;

import java.util.List;

@Service("phBoardService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PhBoardService {
    private final PhBoardMapper phBoardMapper;

    /* -----커뮤니티---------*/
    // 조회
    public List<Board> getBoardList() {
        List<Board> boardList = phBoardMapper.getBoardList();
        return boardList;
    }
    // 추가
    public void addBoard(Board board){phBoardMapper.addBoard(board);
    }
    // 수정
    public int modifyBoard(Board board){
        return phBoardMapper.modifyBoard(board);
    }
    // 삭제
    public void deleteBoard(String boardCode){
        phBoardMapper.deleteBoard(boardCode);
    }

    /* -----커뮤니티 댓글---------*/
    // 조회
    public List<Repl> getReplList() {
        List<Repl> replList = phBoardMapper.getReplList();
        return replList;
    }
    // 추가
    public void addRepl(Repl repl) {
        phBoardMapper.addRepl(repl);
    }
    // 수정
    public int modifyRepl(Repl repl) {
        return  phBoardMapper.modifyRepl(repl);
    }
    // 삭제
    public void deleteRepl(String replNum) {
        phBoardMapper.deleteRepl(replNum);
    }





    /* -----자주하는 질문---------*/
    // 조회
    public List<QuestionCenter> getQuestionCenterList(){
        List<QuestionCenter> questionCenterList = phBoardMapper.getQuestionCenterList();
        return questionCenterList;
    }
    // 추가
    public void addQuestionCenter(QuestionCenter questionCenter) {
        log.info("questionCenter :{}", questionCenter);
        phBoardMapper.addQuestionCenter(questionCenter);
    }
    // 수정
    public int modifyQuestionCenter(QuestionCenter questionCenter){
        return phBoardMapper.modifyQuestionCenter(questionCenter);
    }
    // 삭제
    public void deleteQuestionCenter(String questionNum){
        phBoardMapper.deleteQuestionCenter(questionNum);
    }


    /* -----공지사항---------*/
    // 조회
    public List<Notice> getNoticeList(){
        List<Notice> noticeList = phBoardMapper.getNoticeList();
        return noticeList;
    }
    // 추가
    public void addNotice(Notice notice){
        phBoardMapper.addNotice(notice);
    }
    // 수정
    public int modifyNotice(Notice notice){
        return phBoardMapper.modifyNotice(notice);
    }
    // 삭제
    public void deleteNotice(String noticeNum){
        phBoardMapper.deleteNotice(noticeNum);
    }

    /* -----1:1문의---------*/
    // 조회
    public List<Qna> getQnaList(){
        List<Qna> qnaList = phBoardMapper.getQnaList();
        return qnaList;
    }
    public List<QnaReply> getQnaReplyList(){
        List<QnaReply> qnaReplyList = phBoardMapper.getQnaReplyList();
        return qnaReplyList;
    }
    // 문의 추가
    public void addQna(Qna qna) {
        phBoardMapper.addQna(qna);
    }
    // 답변 추가
    @Transactional
    public void addQnaReply(QnaReply qnaReply) {
        phBoardMapper.addQnaReply(qnaReply);
        String qseq = qnaReply.getQseq();
        phBoardMapper.updateQna(qseq);
    }


    // 삭제
    public void deleteQna(String qseq) {
        phBoardMapper.deleteQna(qseq);
    }
    public void deleteQnaReply(String qrSeq){
        phBoardMapper.deleteQnaReply(qrSeq);
    }


}
