package team01.yaksutor.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team01.yaksutor.common.mapper.MemberMapper;
import team01.yaksutor.dto.Member;


@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.findByMemberId(username);
        if (member == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String authority = getAuthorityFromLevelName(member.getMemberLevelName());

        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getMemberId())
                .password(member.getMemberPw())
                .authorities(new SimpleGrantedAuthority(authority))
                .build();
    }

    private String getAuthorityFromLevelName(String levelName) {
        switch (levelName) {
            case "관리자":
                return "ROLE_ADMIN";
            case "납품업체":
                return "ROLE_SUPPLIER";
            case "개국약사":
            case "관리약사":
                return "ROLE_PHARMACY";
            case "탈퇴회원":
                return "ROLE_NONE";  // 탈퇴회원의 경우 별도의 권한이 필요 없으면 처리하지 않음
            default:
                throw new IllegalArgumentException("Unknown level name: " + levelName);
        }
    }
}
