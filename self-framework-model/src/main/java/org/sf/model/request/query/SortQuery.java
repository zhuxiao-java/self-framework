package org.sf.model.request.query;

import com.google.common.base.CaseFormat;

/**
 * 排序查询
 */
public record SortQuery(String field, Sort sort) {

    public String field() {
        return "f_" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field);
    }
}
