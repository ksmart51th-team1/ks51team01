package team01.yaksutor.pharmacy.mapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import team01.yaksutor.dto.Member;

@Mapper
public interface PhMemberMapper {

    int modifyMyAccount(Member member);
}
