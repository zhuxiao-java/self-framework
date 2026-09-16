package org.sf.service.mapping;

import org.sf.dao.entity.BaseEntity;
import org.sf.model.dto.BaseDTO;

import java.io.Serializable;
import java.util.List;

/**
 * 基础映射转换
 * @author zx
 */
public interface IBaseMapping<E extends BaseEntity<? extends Serializable>, D extends BaseDTO<? extends Serializable>> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntityList(List<D> dtoList);

    List<D> toDtoList(List<E> entityList);
}
