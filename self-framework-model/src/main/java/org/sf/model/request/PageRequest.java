package org.sf.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sf.model.request.query.SearchQuery;
import org.sf.model.request.query.SortQuery;

import java.util.List;

/**
 * 分页request
 * @author zhuxiao
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PageRequest extends BaseRequest {
    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 每页显示条数
     */
    private int pageSize = 10;
    /**
     * 查询条件集合
     */
    private List<SearchQuery> queryList;
    /**
     * 排序
     */
    private List<SortQuery> sortList;
}
