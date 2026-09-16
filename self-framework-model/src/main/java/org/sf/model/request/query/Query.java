package org.sf.model.request.query;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public enum Query {
    EQ("eq"),
    NOT_EQ("nt_eq"),
    LIKE("like"),
    NOT_LIKE("nt_like"),
    IN("in"),
    NOT_IN("nt_in"),
    IS_NULL("is_null"),
    IS_NOT_NULL("is_nt_null"),
    LEFT_LIKE("l_like"),
    RIGHT_LIKE("r_like"),
    BETWEEN("between"),
    GE("ge"),
    LE("le"),
    ;

    @JsonValue
    private final String val;

    @JsonCreator
    public static Query fromValue(String value) {
        for (Query query : Query.values()) {
            if (Objects.equals(query.val, value)) {
                return query;
            }
        }
        throw new IllegalArgumentException("Invalid Query value: " + value);
    }


}
