package team01.yaksutor.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private static final String fileRealPath = "/home/yaksutor/";

    /**
     *
     * 주소요청에 따른 외부파일 경로 설정
     * /attachment/** 리소스 요청시 -> 리소스 실제경로로 경로 설정
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String rootPath = getOSFilePath();
        registry.addResourceHandler("/attachment/**")
                .addResourceLocations(rootPath + fileRealPath + "attachment/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /**
     * 웹어플리케이션을 실행하고 있는 운영체제에 따라 루트 경로 설정
     * @return
     */
    public String getOSFilePath() {
        String rootPath = "file:///";
        String os = System.getProperty("os.name").toLowerCase();

        if(os.contains("win")) {
            rootPath = "file:///d:";
        }else if(os.contains("linux")) {
            rootPath = "file:///";
        }else if(os.contains("mac")){
            rootPath = "file:///Users/Shared";
        }
        return rootPath;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/attachment/**"));
    }
}
