package org.sf.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.sf.dao.constant.Status;

import java.io.Serializable;

/**
 * @author zhuxiao
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseStatusEntity<S extends Status<Integer>, I extends Serializable> extends BaseEntity<I> {
    /**
     * 状态 0有效 1无效
     */
    @TableField(value = "f_status")
    private S status;
}
