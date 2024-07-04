package team01.yaksutor.security.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import team01.yaksutor.common.mapper.MemberMapper;
import team01.yaksutor.dto.AccountContext;
import team01.yaksutor.dto.Member;

import java.util.List;

@Service("userDetailsService")
@RequiredArgsConstructor
public class FormUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        Member member = memberMapper.getMemberInfoById(memberId);
        if (member == null) {
            throw new UsernameNotFoundException("No user found with memberId: " + memberId);
        }
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(member.getMemberLevelName()));

        return new AccountContext(member, authorities);
    }
}
