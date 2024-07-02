package team01.yaksutor.common.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import team01.yaksutor.common.mapper.MemberMapper;
import team01.yaksutor.pharmacy.dto.Level;
import team01.yaksutor.pharmacy.dto.Member;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberMapper.findByMemberId(username);
        if (member == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<SimpleGrantedAuthority> authorities = member.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + translateToEnglish(role)))
                .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getMId())
                .password(member.getPw())
                .authorities(authorities)
                .build();
    }

    private String translateToEnglish(String role) {
        // 예를 들어, 한글 권한 이름을 영문으로 변환하는 메서드 구현
        switch (role) {
            case "관리자":
                return "ADMIN";
            case "납품업체":
                return "SUPPLIER";
            case "개국약사":
            case "관리약사":
                return "PHARMACY";
            default:
                // 기타 필요한 경우 추가 처리
                return role.toUpperCase(); // 혹은 다른 로직에 따라 변환
        }
    }
}