package team01.yaksutor.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.Member;

import java.util.List;

@Mapper
public interface MemberMapper {


    Member getMemberInfoById(String memberId);


}
