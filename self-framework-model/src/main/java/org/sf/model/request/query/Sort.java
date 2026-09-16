package org.sf.model.request.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 排序
 * @author zhuxiao
 */
@AllArgsConstructor
@Getter
public enum Sort {

    ASC("asc"),

    DESC("desc");

    @JsonValue
    private final String val;

    @JsonCreator
    public static Sort fromValue(String value) {
        for (Sort sort : Sort.values()) {
            if (Objects.equals(sort.getVal(), value)) {
                return sort;
            }
        }
        throw new IllegalArgumentException("invalid sort value: " + value);
    }
}
