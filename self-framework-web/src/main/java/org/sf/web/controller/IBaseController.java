package org.sf.web.controller;


import lombok.AllArgsConstructor;
import org.sf.common.code.CommonResp;
import org.sf.model.dto.BaseDTO;
import org.sf.model.request.PageRequest;
import org.sf.model.response.BaseResponse;
import org.sf.model.response.DataResponse;
import org.sf.model.response.PageResponse;
import org.sf.service.IBaseCrudService;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 基础Controller
 * @author zhuxiao
 */
@AllArgsConstructor
public class IBaseController<D extends BaseDTO<? extends Serializable>, S extends IBaseCrudService<D>> {

    protected final S service;

    /**
     * 分页查询
     * @param pageRequest 分页查询参数
     * @return PageResponse
     */
    @PostMapping("page")
    public PageResponse<D> page(@RequestBody PageRequest pageRequest) {
        IBaseCrudService.PageResult<D> result = service.page(pageRequest.getPageNum(), pageRequest.getPageSize(), pageRequest.getQueryList(), pageRequest.getSortList());
        return PageResponse.page(result.total(), pageRequest.getPageNum(), pageRequest.getPageSize(), result.dtoList());
    }

    /**
     * 查询所有
     * @return DataResponse<List<D>>
     */
    @GetMapping("selectAll")
    public DataResponse<List<D>> selectAll() {
        List<D> list = service.selectAll();
        return DataResponse.of(list);
    }

    /**
     * 新增
     * @param dto 参数
     * @return BaseResponse
     */
    @PostMapping("save")
    public BaseResponse save(@RequestBody D dto) {
        boolean saveSuccess = service.insert(dto);
        return BaseResponse.of(saveSuccess ? CommonResp.INSERT_SUCCESS : CommonResp.INSERT_FAIL);
    }

    /**
     * 修改
     * @param dto 参数
     * @return BaseResponse
     */
    @PostMapping("update")
    public BaseResponse update(@RequestBody D dto) {
        boolean updateSuccess = service.update(dto);
        return BaseResponse.of(updateSuccess? CommonResp.UPDATE_SUCCESS : CommonResp.UPDATE_FAIL);
    }

    /**
     * 查询详情
     * @param id 主键id
     * @return DataResponse<D>
     */
    @GetMapping("detail/{id}")
    public DataResponse<D> detail(@PathVariable("id") Serializable id) {
        D detail = service.detail(id);
        return DataResponse.of(detail);
    }

    /**
     * 根据主键id删除
     * @param id 主键id
     * @return BaseResponse
     */
    @PostMapping("delete")
    public BaseResponse delete(@RequestParam("id") Serializable id) {
        boolean isDelete = service.delete(id);
        return BaseResponse.of(isDelete ? CommonResp.DELETE_SUCCESS : CommonResp.DELETE_FAIL);
    }
}
