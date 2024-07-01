package team01.yaksutor.pharmacy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.pharmacy.dto.Board;
import team01.yaksutor.pharmacy.dto.QuestionCenter;
import team01.yaksutor.pharmacy.mapper.PhBoardMapper;

import java.util.List;

@Service("phBoardService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PhBoardService {
    private final PhBoardMapper phBoardMapper;

    public List<Board> getBoardList() {
        List<Board> boardList = phBoardMapper.getBoardList();
        return boardList;
    }

    public List<QuestionCenter> getQuestionCenterList(){
        List<QuestionCenter> questionCenterList = phBoardMapper.getQuestionCenterList();
        return questionCenterList;
    }
}
