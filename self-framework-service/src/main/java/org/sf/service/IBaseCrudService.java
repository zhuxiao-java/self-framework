package org.sf.service;

import org.sf.model.dto.BaseDTO;
import org.sf.model.request.query.SearchQuery;
import org.sf.model.request.query.SortQuery;

import java.io.Serializable;
import java.util.List;

/**
 * 基础增删改查接口
 * @author zhuxiao
 */
public interface IBaseCrudService<D extends BaseDTO<? extends Serializable>> {
    /**
     * 查询详情
     * @param id 主键id
     * @return Dto
     */
    D detail(Serializable id);

    /**
     * 查询所有
     * @return List<D>
     */
    List<D> selectAll();

    /**
     * 新增
     * @param dto 入参
     * @return boolean true新增成功 false 新增失败
     */
    boolean insert(D dto);

    /**
     * 根据主键id更新
     * @param dto 参数
     * @return boolean 更新成功 boolean 更新失败
     */
    boolean update(D dto);

    /**
     * 查询条数
     * @param queryList 参数
     * @return long
     */
    long count(List<SearchQuery> queryList);

    /**
     * 根据主键id删除
     * @param id 主键id
     * @return boolean 删除成功 boolean 删除失败
     */
    boolean delete(Serializable id);
    /**
     * 分页查询列表
     * @param pageNum 当前页码
     * @param pageSize 每页显示条数
     * @param queryList 查询条件
     * @return  List<D>
     */
    PageResult<D> page(int pageNum, int pageSize, List<SearchQuery> queryList, List<SortQuery> sortList);

    record PageResult<D> (List<D> dtoList, long total) {

    }
}
