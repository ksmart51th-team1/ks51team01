package team01.yaksutor.pharmacy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.pharmacy.dto.Board;
import team01.yaksutor.pharmacy.dto.Notice;
import team01.yaksutor.pharmacy.dto.QuestionCenter;
import team01.yaksutor.pharmacy.dto.Repl;

import java.util.List;

@Mapper
public interface PhBoardMapper {
    //  조회
    List<Board> getBoardList();
    List<Repl> getReplList();
    List<QuestionCenter> getQuestionCenterList();
    List<Notice> getNoticeList();

    // 커뮤니티 등록
    int addBoard(Board board);
    // 커뮤니티 수정
    int modifyBoard(Board board);
    // 커뮤니티 삭제
    int deleteBoard(String boardCode);
    // 커뮤니티 댓글 등록
    int addRepl(Repl repl);
    // 커뮤니티 댓글 수정
    int modifyRepl(Repl repl);
    // 커뮤니티 댓글 삭제
    int deleteRepl(String replNum);



   // 자주하는 질문 등록
    int addQuestionCenter(QuestionCenter questionCenter);
    // 자주하는 질문 수정
    int modifyQuestionCenter(QuestionCenter questionCenter);
    // 자주하는 질문 삭제
    int deleteQuestionCenter(String questionNum);

    // 공지사항 등록
    int addNotice(Notice notice);
    // 공지사항 수정
    int modifyNotice(Notice notice);
    // 공지사항 삭제
    int deleteNotice(String noticeNum);
}
