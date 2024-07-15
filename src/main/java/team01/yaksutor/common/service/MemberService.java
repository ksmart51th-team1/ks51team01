package team01.yaksutor.common.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team01.yaksutor.common.mapper.MemberMapper;
import team01.yaksutor.dto.Member;
import team01.yaksutor.dto.SellMedicine;
import team01.yaksutor.pharmacy.mapper.PhMedicineMapper;

import java.util.List;
@Service("MemberService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;

    // 조회
    public List<Member> getMemberList(){
        List<Member> getMemberList = memberMapper.getMemberInfo();
        return getMemberList;
    }
}
