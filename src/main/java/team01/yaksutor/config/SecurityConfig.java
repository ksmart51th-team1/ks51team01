package team01.yaksutor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private static final String[] BASIC_LIST = {
            "*","/", "/index", "/pharm/main", "user3", "user4", "user5", "user6","admin/adminMain",
            "/resource/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(BASIC_LIST).permitAll()  // index 페이지에 대한 접근 허용
                                   // /resources/** 하위 모든 경로에 대한 접근 허용
                                .anyRequest().authenticated()                // 그 외 모든 요청에는 인증이 필요함
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/index") // 커스텀 로그인 페이지 경로 설정
                                .defaultSuccessUrl("/", true) // 로그인 성공 시 리디렉션할 기본 페이지
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
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password("{noop}password") // 비밀번호 인코딩 사용하지 않음
                .roles("USER")
                .build());
        return manager;
    }
}