package team01.yaksutor.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    private static final String[] BASIC_LIST = {
            "*" , "/", "/index",
            "/resource/**",             //static -> css permit
            "/member/memberInsert/**", // 회원가입페이지 permit
            "/admin/login", "/supplier/login", // 로그인페이지 permit
            "supplier/supplierMain", //납품업체 메인화면 permit 로그인기능 완료시 삭제 예정
            "admin/adminMain",      // 관리자 메인화면 permit 로그인 기능 완료시 삭제 예정
            "/pharm/main",          // 약국 메인화면 permit  로그인 기능 완료시 삭제 예정
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(BASIC_LIST).permitAll()  // index 페이지에 대한 접근 허용
                                .requestMatchers("/login").hasAuthority("ROLE_PARMACY")
                                .requestMatchers("/adminLogin").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/supplierLogin").hasAuthority("ROLE_SUPPLIER")
                                   // /resources/** 하위 모든 경로에 대한 접근 허용
                                .anyRequest().authenticated()                // 그 외 모든 요청에는 인증이 필요함
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/index") // 커스텀 로그인 페이지 경로 설정
                                .successHandler(customAuthenticationSuccessHandler) // 로그인 성공 시 리디렉션할 기본 페이지
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutSuccessUrl("/index") // 로그아웃 성공 시 리디렉션할 기본 페이지
                                .permitAll()
                );

        return http.build();
    }
}