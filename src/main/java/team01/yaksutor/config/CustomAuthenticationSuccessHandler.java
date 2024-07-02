package team01.yaksutor.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = null;

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                redirectUrl = "/admin/adminMain";
                break;
            } else if (authority.getAuthority().equals("ROLE_PHARMACY")) {
                redirectUrl = "/pharm/main";
                break;
            } else if (authority.getAuthority().equals("ROLE_SUPPLIER")) {
                redirectUrl = "/supplier/supplierMain";
                break;
            }
        }

        if (redirectUrl != null) {
            response.sendRedirect(redirectUrl);
        } else {
            throw new IllegalStateException();
        }
    }
}
