package pub.akiwebbackend.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 通用返回类
 */
@Data
@AllArgsConstructor
public class R {
    /**
     * 响应码
     */
    private String code;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private Object data;

    /**
     * 成功
     * @return 默认成功信息，无数据
     */
    public static R success() {
        return new R(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功
     * @param data 响应数据
     * @return 默认成功信息，返回响应数据
     */
    public static R success(Object data) {
        return new R(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功
     * @param msg 自定义成功信息
     * @param data 响应数据
     * @return 自定义成功信息，返回响应数据
     */
    public static R success(String msg, Object data) {
        return new R(ErrorCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 失败
     * @return 默认错误信息
     */
    public static R fail() {
        return new R(ErrorCode.ERROR.getCode(), ErrorCode.ERROR.getMessage(), null);
    }

    /**
     * 失败
     * @param msg 自定义错误信息
     * @return 默认响应码，自定义错误信息
     */
    public static R fail(String msg) {
        return new R(ErrorCode.ERROR.getCode(), msg, null);
    }


    /**
     * 失败
     * @param code 自定义错误码
     * @param msg 自定义错误信息
     * @return 自定义错误码和错误信息
     */
    public static R fail(String code, String msg) {
        return new R(code, msg, null);
    }

    /**
     * 失败
     * @param errorCode 自定义响应码
     * @return 自定义响应码
     */
    public static R fail(ErrorCode errorCode) {
        return new R(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败
     * @param errorCode 自定义响应码
     * @param msg 自定义响应信息
     * @return 自定义响应码和响应信息
     */
    public static R fail(ErrorCode errorCode, String msg) {
        return new R(errorCode.getCode(), msg, null);
    }
}

