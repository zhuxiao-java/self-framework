package org.sf.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sf.common.code.CommonResp;

/**
 * 带返回值的response
 * @author zhuxiao
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataResponse<T> extends BaseResponse {

    private T data;


    public static <T> DataResponse<T> of(T data) {
        DataResponse<T> response = new DataResponse<>();
        response.setData(data);
        response.setCode(CommonResp.SUCCESS.getCode());
        response.setMsg(CommonResp.SUCCESS.getMsg());
        return response;
    }
}
