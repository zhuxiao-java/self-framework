package org.sf.model.response;

import lombok.Data;
import org.sf.common.code.CommonResp;
import org.sf.common.code.RespInfo;

import java.io.Serializable;

/**
 * 基础response
 * @author zhuxiao
 */
@Data
public class BaseResponse implements Serializable {

    private String code;

    private String msg;

    public static BaseResponse of(RespInfo resp) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMsg(resp.getMsg());
        baseResponse.setCode(resp.getCode());
        return baseResponse;
    }

    public static BaseResponse fail() {
        return of(CommonResp.FAIL);
    }


    public static BaseResponse success() {
        return of(CommonResp.SUCCESS);
    }
}
