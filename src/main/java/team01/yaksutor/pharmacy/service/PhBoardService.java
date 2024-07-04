package team01.yaksutor.pharmacy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.pharmacy.dto.Board;
import team01.yaksutor.pharmacy.dto.Notice;
import team01.yaksutor.pharmacy.dto.QuestionCenter;
import team01.yaksutor.pharmacy.dto.Repl;
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
    public void addBoard(Board board){
        phBoardMapper.addBoard(board);
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
}
