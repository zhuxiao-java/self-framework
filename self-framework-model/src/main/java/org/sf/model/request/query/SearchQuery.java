package org.sf.model.request.query;

import com.google.common.base.CaseFormat;

/**
 * 查询条件
 * @author zhuxiao
 */
public record SearchQuery(String key, Object value, Query query) {

    public String field() {
        return "f_" + CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, key);
    }
}
