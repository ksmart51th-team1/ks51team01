package team01.yaksutor.security.listener;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import team01.yaksutor.admin.mapper.AdLoginHistoryMapper;

import team01.yaksutor.dto.LoginHistory;
import team01.yaksutor.dto.Member;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class AuthenticationEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final AdLoginHistoryMapper adloginHistoryMapper;
    private final HttpServletRequest request;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        Member loginMember = (Member) authentication.getPrincipal();

        String username = loginMember.getMemberId();
        String userAgent = request.getHeader("User-Agent");
        System.out.println(userAgent);
        String browser = UserAgentPaser.getBrowserName(userAgent);
        System.out.println(browser);

        String loginDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        LoginHistory loginHistory = new LoginHistory();
        loginHistory.setMemberId(username);
        loginHistory.setBrowser(browser);
        loginHistory.setLoginDate(loginDate);

        adloginHistoryMapper.insertLoginHistory(loginHistory);
    }
}
