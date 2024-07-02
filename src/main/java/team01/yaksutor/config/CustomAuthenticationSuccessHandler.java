package team01.yaksutor.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String redirectUrl = "/index";

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ADMIN")) {
                redirectUrl = "/admin/adminMain";
                break;
            } else if (authority.getAuthority().equals("PHARMACY")) {
                redirectUrl = "/pharm/main";
                break;
            } else if (authority.getAuthority().equals("PHARMACY")) {
                redirectUrl = "/pharm/main";
                break;
            } else if (authority.getAuthority().equals("SUPPLIER")) {
                redirectUrl = "/supplier/supplierMain";
                break;
            }
        }

        response.sendRedirect(redirectUrl);
    }
}
