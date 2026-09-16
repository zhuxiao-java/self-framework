package org.sf.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sf.common.code.CommonResp;
import java.util.List;

/**
 * 带分页返回值的response
 * @author zhuxiao
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageResponse<E> extends DataResponse<List<E>> {

    private long total;

    private int pageNum;

    private int pageSize;


    public static <E> PageResponse<E> page(long total, int pageNum, int pageSize, List<E> list) {
        PageResponse<E> response = new PageResponse<>();
        response.setMsg(CommonResp.SUCCESS.getMsg());
        response.setCode(CommonResp.SUCCESS.getCode());
        response.setTotal(total);
        response.setPageNum(pageNum);
        response.setPageSize(pageSize);
        response.setData(list);
        return response;
    }
}
