package team01.yaksutor.pharmacy.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.pharmacy.dto.Board;
import java.util.List;

@Mapper
public interface PhBoardMapper {
    // 커뮤니티 조회
    List<Board> getBoardList();

}
