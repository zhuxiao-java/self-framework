package org.sf.web.controller.advice;

import lombok.extern.slf4j.Slf4j;
import org.sf.common.code.CommonResp;
import org.sf.common.exception.BaseException;
import org.sf.model.response.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 接口异常捕获器
 * @author zhuxiao
 */
@Slf4j
public class ControllerAdvice {

    /**
     * 基础异常捕获
     * @param ex 异常
     * @return BaseResponse
     */
    @ExceptionHandler(BaseException.class)
    public BaseResponse baseException(BaseException ex) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ex.getCode());
        baseResponse.setMsg(ex.getMsg());
        return baseResponse;
    }

    /**
     * 未知异常捕获
     * @param ex 未知异常
     * @return BaseResponse
     */
    @ExceptionHandler(Exception.class)
    public BaseResponse error(Exception ex) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMsg(CommonResp.UNKNOWN_ERROR.getMsg());
        baseResponse.setCode(CommonResp.UNKNOWN_ERROR.getCode());
        log.error("发现未知异常:{}", ex.getMessage(), ex);
        return baseResponse;
    }
}
