package org.sf.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 时间区间request
 * @author zhuxiao
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RangeRequest<T> extends BaseRequest {
    /**
     * 开始时间
     */
    private T start;
    /**
     * 结束时间
     */
    private T end;
}
