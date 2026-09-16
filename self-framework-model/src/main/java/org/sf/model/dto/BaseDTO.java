package org.sf.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础DTO
 * @author zhuxiao
 */
@Data
public class BaseDTO<S extends Serializable> implements Serializable {
    /**
     * 主键id
     */
    private S id;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
