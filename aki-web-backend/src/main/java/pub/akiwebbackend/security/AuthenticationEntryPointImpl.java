package pub.akiwebbackend.security;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import pub.akiwebbackend.common.ErrorCode;
import pub.akiwebbackend.common.R;

import java.io.IOException;

@Slf4j
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(new Gson().toJson(R.fail(ErrorCode.USER_ERROR_A0200, "未登录")));
    }
}
