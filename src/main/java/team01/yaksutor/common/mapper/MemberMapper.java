package team01.yaksutor.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;
import team01.yaksutor.dto.MemberLevel;
import team01.yaksutor.pharmacy.dto.Level;
import team01.yaksutor.pharmacy.dto.Member;

import java.util.List;

@Mapper
public interface MemberMapper {



    //회원권한이름조회
    List<Level> findRolesByMemberId(String memberId);
    // 아이디 중복체크
    boolean idCheck(String memberId);
    // 회원등급 조회
    List<Level> getMemberLevelList();
    // 회원목록 조회
    List<Member> getMemberList();
    //특정회원조회(로그인용)
    Member getMemberInfoById(String memberId);
}
