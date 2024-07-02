package team01.yaksutor.config;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import team01.yaksutor.common.service.CustomUserDetailService;

@Configuration
public class SecurityConfig {

    private static final String[] BASIC_LIST = {
            "/", "/index","/resource/**","/member/memberInsert/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) //CSRF 비활성화
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(BASIC_LIST).permitAll()  // index 페이지에 대한 접근 허용
                                   // /resources/** 하위 모든 경로에 대한 접근 허용
                                .requestMatchers("/pharm/main").hasAuthority("ROLE_PHARMACY")
                                .anyRequest().authenticated()                // 그 외 모든 요청에는 인증이 필요함
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/index") // 커스텀 로그인 페이지 경로 설정
                                .successHandler(customAuthenticationSuccessHandler()) // 로그인 성공 시 리디렉션할 기본 페이지
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/index") // 로그아웃 성공 시 리디렉션할 기본 페이지
                                .permitAll()
                );

        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public CustomUserDetailService customUserDetailService() {
        return new CustomUserDetailService();
    }




}