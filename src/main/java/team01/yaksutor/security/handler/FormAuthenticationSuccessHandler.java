package team01.yaksutor.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import team01.yaksutor.dto.AccountContext;
import team01.yaksutor.dto.Member;

import java.io.IOException;

@Component
@Slf4j
public class FormAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final RequestCache requestCache = new HttpSessionRequestCache();
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {

        String memberInfo = authentication.getName();
        int start = memberInfo.indexOf("memberLevelName=") + "memberLevelName=".length();
        String memberLevel = memberInfo.substring(start);
        memberLevel = memberLevel.substring(0, memberLevel.indexOf(","));

        String defaultPath = "/";

        switch (memberLevel){
            case "관리자" -> defaultPath = "/admin/adminMain";
            case "개국약사" -> defaultPath = "/pharm/main";
            case "관리약사" -> defaultPath = "/pharm/main";
            case "납품업체" -> defaultPath = "/supplier/supplierMain";
        }
        setDefaultTargetUrl(defaultPath);

        SavedRequest savedRequest = requestCache.getRequest(request, response);

        if(savedRequest!=null) {
            redirectStrategy.sendRedirect(request, response, defaultPath);
        }
        else {
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }
    }
}
