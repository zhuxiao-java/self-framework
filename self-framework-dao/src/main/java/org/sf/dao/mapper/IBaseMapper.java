package org.sf.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.sf.dao.entity.BaseEntity;

/**
 * 基础mapper接口
 * @author zx
 */
public interface IBaseMapper<E extends BaseEntity<?>> extends BaseMapper<E> {

}
