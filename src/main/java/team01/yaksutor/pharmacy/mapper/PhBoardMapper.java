package team01.yaksutor.pharmacy.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.pharmacy.dto.Board;
import team01.yaksutor.pharmacy.dto.QuestionCenter;

import java.util.List;

@Mapper
public interface PhBoardMapper {
    // 커뮤니티 조회
    List<Board> getBoardList();
    List<QuestionCenter> getQuestionCenterList();

   // 자주하는 질문 등록
    int addQuestionCenter(QuestionCenter questionCenter);

    // 자주하는 질문 삭제
    int removeQuestionCenterByNum(String questionNum);
}
