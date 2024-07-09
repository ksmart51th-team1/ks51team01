package team01.yaksutor.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import team01.yaksutor.security.exception.SecretException;

import java.io.IOException;
import java.net.URLEncoder;

@Component
public class FormAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {

        String errorMessage = "Invalid Username or Password";

        if(exception instanceof BadCredentialsException) {
            errorMessage = "회원정보가 일치하지 않습니다.";
        }
        else if(exception instanceof UsernameNotFoundException) {
            errorMessage = "User not exists";
        }
        else if(exception instanceof CredentialsExpiredException) {
            errorMessage = "Expired password";

        }else if(exception instanceof SecretException) {
            errorMessage = "Invalid Secret key";
        }

        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");

        setDefaultFailureUrl("/?error=true&exception=" + errorMessage);

        super.onAuthenticationFailure(request, response, exception);

    }
}
