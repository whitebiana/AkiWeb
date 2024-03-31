package pub.akiwebbackend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pub.akiwebbackend.common.ErrorCode;
import pub.akiwebbackend.common.R;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public R businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return R.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public R runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return R.fail(ErrorCode.SYSTEM_ERROR_B0001);
    }
}
