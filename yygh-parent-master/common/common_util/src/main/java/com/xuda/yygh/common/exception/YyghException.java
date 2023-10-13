package com.xuda.yygh.common.exception;

import com.xuda.yygh.common.result.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：程序员徐大大
 * @description：TODO
 * @date ：2022-04-02 19:27
 */
@Data
@ApiModel(value = "自定义全局异常")
public class YyghException extends RuntimeException{
    @ApiModelProperty(value = "异常状态码")
    private Integer code;

    /**
     * 通过状态吗和错误信息创建异常对象
     * @param messgae 信息
     * @param code 状态码
     */
    public YyghException(String messgae, Integer code) {
        super(messgae);
        this.code = code;
    }
    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public YyghException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "YyghException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
