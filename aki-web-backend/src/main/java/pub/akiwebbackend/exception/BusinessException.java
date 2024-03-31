package pub.akiwebbackend.exception;


import pub.akiwebbackend.common.ErrorCode;

public class BusinessException extends RuntimeException {
    /**
     * 错误码
     */
    private final String code;

    public String getCode() {
        return code;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }
}
