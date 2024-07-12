package team01.yaksutor.security.listener;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import team01.yaksutor.admin.mapper.AdLoginHistoryMapper;
import team01.yaksutor.dto.LoginHistory;
import team01.yaksutor.dto.Member;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class LogoutEventListener implements ApplicationListener<LogoutSuccessEvent> {

    private final AdLoginHistoryMapper adloginHistoryMapper;
    private final HttpServletRequest request;

    public LogoutEventListener(AdLoginHistoryMapper loginHistoryMapper, HttpServletRequest request) {
        this.adloginHistoryMapper = loginHistoryMapper;
        this.request = request;
    }

    @Override
    public void onApplicationEvent(LogoutSuccessEvent event) {
        Authentication authentication = event.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof Member) {
                Member loginMember = (Member) principal;
                String username = loginMember.getMemberId();
                String logoutDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                LoginHistory loginHistory = new LoginHistory();
                loginHistory.setMemberId(username);
                loginHistory.setLogoutDate(logoutDate);

                adloginHistoryMapper.updateLogoutDate(loginHistory);
            } else {
                System.out.println("Principal is not an instance of Member.");
            }
        } else {
            // Authentication이 null인 경우를 처리하는 로직 추가
            System.out.println("Authentication is null during logout.");
        }
    }
}
