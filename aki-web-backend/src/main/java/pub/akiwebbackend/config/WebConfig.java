package pub.akiwebbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author cym
 * @date 2024/3/30
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*"); // 允许跨域请求的域名
        config.addAllowedMethod("*"); // 允许的请求方式
        config.addAllowedHeader("*"); // 设置允许的header属性
        config.setAllowCredentials(true); // 是否允许kookie
        config.setMaxAge(3600L); // 跨域允许的时间，单位是s
        source.registerCorsConfiguration("/**", config); // 允许跨域的路径
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域的路径
                .allowedOriginPatterns("*") // 允许跨域请求的域名
                .allowCredentials(true) // 是否允许kookie
                .allowedMethods("*")  // 允许的请求方式
                .allowedHeaders("*") // 设置允许的header属性
                .maxAge(3600); // 跨域允许的时间，单位是s
    }
}

