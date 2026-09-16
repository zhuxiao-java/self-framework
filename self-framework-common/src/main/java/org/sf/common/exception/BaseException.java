package org.sf.common.exception;


import lombok.Getter;
import org.sf.common.code.RespInfo;

/**
 * 基础异常
 * @author zhuxiao
 */
@Getter
public class BaseException extends RuntimeException {
    /**
     * 自定义异常信息
     */
    private final String msg;
    /**
     * 自定义异常码
     */
    private final String code;

    public BaseException(RespInfo respInfo, Throwable throwable) {
        super(throwable);
        this.msg = respInfo.getMsg();
        this.code = respInfo.getCode();
    }

    public BaseException(RespInfo respInfo){
        this.msg = respInfo.getMsg();
        this.code = respInfo.getCode();
    }
}
