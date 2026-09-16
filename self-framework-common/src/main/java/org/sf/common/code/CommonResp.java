package org.sf.common.code;

import lombok.AllArgsConstructor;

/**
 * 响应码从0807往后排吧...
 */
@AllArgsConstructor
public enum CommonResp implements RespInfo {
    /**
     * 成功
     */
    SUCCESS("S0806", "成功"),
    /**
     * 失败
     */
    FAIL("S0807", "失败"),

    /**
     * 保存成功
     */
    INSERT_SUCCESS("S0808", "保存成功"),
    /**
     * 保存失败
     */
    INSERT_FAIL("S0809","保存失败"),
    /**
     * 修改成功
     */
    UPDATE_SUCCESS("S0810", "修改成功"),
    /**
     * 修改失败
     */
    UPDATE_FAIL("S0811", "修改失败"),
    /**
     * 删除成功
     */
    DELETE_SUCCESS("S0812","删除成功"),
    /**
     * 删除失败
     */
    DELETE_FAIL("S0813","删除失败"),
    /**
     * 未知异常
     */
    UNKNOWN_ERROR("S0814","未知异常"),

    ENABLE_SUCCESS("S0815", "启用成功"),

    ENABLE_FAIL("S0816", "启用失败"),

    DISABLE_SUCCESS("S0817", "禁用成功"),

    DISABLE_FAIL("S0818", "禁用失败")
    ;

    private final String code;

    private final String msg;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
