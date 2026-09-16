package org.sf.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础entity
 * @author zhuxiao
 */
@Data
public class BaseEntity<S extends Serializable> {
    /**
     * 主键id
     */
    @TableId(value = "f_id", type = IdType.AUTO)
    private S id;

    /**
     * 创建时间
     */
    @TableField(value = "f_create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @TableField(value = "f_update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}
