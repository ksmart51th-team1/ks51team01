package team01.yaksutor.security.config;




import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import team01.yaksutor.security.handler.FormAccessDeniedHandler;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {


    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;
    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;

    private static final String[] BASIC_LIST = {
            "/", "/index","/resource/**","/member/memberInsert/**", "/login"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);
        http
                .csrf(AbstractHttpConfigurer::disable) //CSRF 비활성화
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(
                                        mvc.pattern("/"),
                                        mvc.pattern("/index"),
                                        mvc.pattern("/resource/**"),
                                        mvc.pattern("/member/memberInsert/**"),
                                        mvc.pattern("/login")
                                ).permitAll()  // index 페이지에 대한 접근 허용
                                   // /resources/** 하위 모든 경로에 대한 접근 허용
                                .requestMatchers("/admin/**").hasAuthority("관리자")
                                .requestMatchers("/pharm/**").hasAuthority("개국약사")
                                .requestMatchers("/pharm/**").hasAuthority("관리약사")
                                .requestMatchers("/supplier/**").hasAuthority("납품업체")
                                .anyRequest().authenticated()                // 그 외 모든 요청에는 인증이 필요함
                )
                .formLogin(form -> form
                        .loginPage("/")
                        .usernameParameter("memberId")
                        .passwordParameter("memberPw")
                        .loginProcessingUrl("/login")
                        .authenticationDetailsSource(authenticationDetailsSource)
                        .successHandler(successHandler)
                        .failureHandler(failureHandler)
                        .permitAll())
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .authenticationProvider(authenticationProvider)
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new FormAccessDeniedHandler("/denied"))
                )
                .sessionManagement(sessionManagement ->
                sessionManagement
                        .sessionFixation(sessionFixation ->
                                sessionFixation.newSession()
                        )
                        .maximumSessions(1)
                        .expiredUrl("/login?expired=true")

        )
                .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        );


        return http.build();
    }
}