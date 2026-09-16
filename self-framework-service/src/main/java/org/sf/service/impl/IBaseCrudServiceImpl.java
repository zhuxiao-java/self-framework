package org.sf.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.sf.dao.entity.BaseEntity;
import org.sf.dao.mapper.IBaseMapper;
import org.sf.model.dto.BaseDTO;
import org.sf.model.request.query.SearchQuery;
import org.sf.model.request.query.Sort;
import org.sf.model.request.query.SortQuery;
import org.sf.service.IBaseCrudService;
import org.sf.service.mapping.IBaseMapping;
import org.sf.util.StreamUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 基础crud实现类
 *
 * @author zhuxiao
 */
@AllArgsConstructor
public class IBaseCrudServiceImpl<D extends BaseDTO<? extends Serializable>, E extends BaseEntity<? extends Serializable>, M extends IBaseMapper<E>, C extends IBaseMapping<E, D>> extends ServiceImpl<M, E> implements IBaseCrudService<D> {

    protected C mapping;

    @Override
    public D detail(Serializable id) {
        E entity = baseMapper.selectById(id);
        return mapping.toDto(entity);
    }

    @Override
    public List<D> selectAll() {
        return mapping.toDtoList(super.list());
    }

    @Override
    public boolean insert(D dto) {
        E entity = mapping.toEntity(dto);
        this.savePreCheck(entity);
        return super.save(entity);
    }

    protected void bindSortQueryWrapper(QueryWrapper<E> qw, List<SortQuery> sortList) {
        if (CollectionUtils.isEmpty(sortList)) {
            return;
        }
        Map<Sort, List<SortQuery>> sortListMap = StreamUtil.group(sortList, SortQuery::sort);
        for (Map.Entry<Sort, List<SortQuery>> entry : sortListMap.entrySet()) {
            List<String> fieldList = StreamUtil.map(entry.getValue(), SortQuery::field);
            if (Objects.equals(entry.getKey(), Sort.ASC)) {
                qw.orderByAsc(fieldList);
            } else {
                qw.orderByDesc(fieldList);
            }
        }
    }

    protected QueryWrapper<E> bindQueryWrapper(List<SearchQuery> queryList) {
        QueryWrapper<E> queryWrapper = qw();
        for (SearchQuery query : queryList) {
            switch (query.query()) {
                case EQ -> queryWrapper.eq(query.field(), query.value());
                case NOT_EQ -> queryWrapper.ne(query.field(), query.value());
                case LIKE -> queryWrapper.like(query.field(), query.value());
                case NOT_LIKE -> queryWrapper.notLike(query.field(), query.value());
                case IN -> {
                    Object inVal = query.value();
                    if (inVal instanceof Collection<?> coll) {
                        queryWrapper.in(query.field(), coll.toArray(new Object[0]));
                    } else {
                        queryWrapper.in(query.field(), inVal);
                    }
                }
                case NOT_IN -> queryWrapper.notIn(query.field(), query.value());
                case IS_NULL -> queryWrapper.isNull(query.field());
                case IS_NOT_NULL -> queryWrapper.isNotNull(query.field());
                case LEFT_LIKE -> queryWrapper.likeLeft(query.field(), query.value());
                case RIGHT_LIKE -> queryWrapper.likeRight(query.field(), query.value());
                case GE -> queryWrapper.ge(query.field(), query.value());
                case LE -> queryWrapper.le(query.field(), query.value());
                case BETWEEN -> {
                    if (query.value() instanceof List<?> coll) {
                        queryWrapper.between(query.field(), coll.get(0), coll.get(1));
                    } else if (query.value() instanceof Object[] array) {
                        queryWrapper.between(query.field(), array[0], array[1]);
                    } else {
                        throw new IllegalArgumentException("Not Support Query");
                    }
                }
            }
        }
        return queryWrapper;
    }


    @Override
    public boolean update(D dto) {
        E entity = mapping.toEntity(dto);
        return super.updateById(entity);
    }

    @Override
    public long count(List<SearchQuery> queryList) {
        QueryWrapper<E> queryWrapper = bindQueryWrapper(queryList);
        return super.count(queryWrapper);
    }

    @Override
    public boolean delete(Serializable id) {
        return super.removeById(id);
    }

    @Override
    public PageResult<D> page(int pageNum, int pageSize, List<SearchQuery> queryList, List<SortQuery> sortList) {
        QueryWrapper<E> qw = this.bindQueryWrapper(queryList);
        bindSortQueryWrapper(qw, sortList);
        Page<E> ePage = baseMapper.selectPage(PageDTO.of(pageNum, pageSize), qw);
        List<D> dtoList = mapping.toDtoList(ePage.getRecords());
        return new PageResult<>(dtoList, ePage.getTotal());
    }

    protected LambdaQueryWrapper<E> lqw() {
        return new LambdaQueryWrapper<>();
    }

    protected QueryWrapper<E> qw() {
        return new QueryWrapper<>();
    }

    protected void savePreCheck(E entity) {
    }


}
