package pub.akiwebbackend.config;

import org.springframework.context.annotation.Configuration;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author cym
 * @date 2024/3/30
 */
@Configuration
@EnableWebSecurity // 默认注册大量过滤器，形成过滤器链
@EnableMethodSecurity // 在方法执行之前鉴权
public class SecurityConfig {
    // @Resource
    // private AuthenticationEntryPoint authenticationEntryPoint;

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }

    // @Bean
    // public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    //     return authenticationConfiguration.getAuthenticationManager();
    // }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // anonymous 匿名访问，没登录的可以访问，已经登录的不可以访问
        // permitAll 允许访问所有权限，不管登录没登录都可以访问
        // anyRequest 其他任何请求
        // authenticated 认证
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers(
                                "/doc.html", "/swagger-ui/**", "/webjars/**", "/v3/**",
                                // "/assets/**", "/favicon.ico", "/images/**",
                                // "/", "/index.html", "/error",
                                // "/user/login", "/user/register", "/user/info", "/email/code/**"
                                "/test/**"
                        ).permitAll()
                        .anyRequest().authenticated()
        );

        // http可以一直点，但是很丑
        // loginPage 登录页面
        // loginProcessingUrl 登录接口，对应前端表单的action
        // defaultSuccessUrl 登录成功后跳转

        // http.formLogin(formLogin ->
        //         formLogin
        //                 .loginPage("/login.html").permitAll()
        // );

        /*
         * 在 Security配置类中 正确配置了 AccessDeniedHandler，但是发现实际运行时 AccessDeniedHandler 没有被触发！
         * 出现这种问题的原因一般都是因为项目中还配置了 GlobalExceptionHandler
         * 由于GlobalExceptionHandler 全局异常处理器会比 AccessDeniedHandler 先捕获 AccessDeniedException 异常
         * 因此当配置了 GlobalExceptionHandler 后，会发现 AccessDeniedHandler 失效了。
         */
        // 两个过滤器，认证和授权
        // http.exceptionHandling((handler) ->
        //         handler.authenticationEntryPoint(authenticationEntryPoint)
        // );


        // 关闭跨域请求伪造防护
        // 1. http.csrf(Customizer.withDefaults());
        // 2. http.csrf(withDefaults()); 静态导入Customizer??装B
        // 3. http.csrf(csrf -> csrf.disable());
        http.csrf((csrf) -> csrf.disable());

        // 允许跨域
        http.cors((cors) -> cors.disable());
        //http.cors(Customizer.withDefaults());

        // 登出，使session失效，后面可以接deleteCookie
        // 注意logout是个post请求
        //http.logout(logout -> logout.invalidateHttpSession(true));

        // Spring Security 永远不会创建一个 HttpSession ，也永远不会使用它来获取 SecurityContext
        http.sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }
}