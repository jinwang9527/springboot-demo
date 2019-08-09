package com.firststringboot.common.model;

import com.firststringboot.common.responseCode.CodeStratus;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import lombok.Data;

@Data
public class PageResultModel<T> extends ResultModel {


    private Paginator paginator;


    public PageResultModel(T data, Paginator paginator, int code, String message) {
        super(code, message, data);
        this.paginator = paginator;
    }


    /**
     * 获取成功带分页数据
     * @param data
     * @param paginator
     * @param <T>
     * @return
     */
    public static <T> PageResultModel<T> getSuccessPageResultModel(T data, Paginator paginator) {
        return new PageResultModel<T>(data, paginator, CodeStratus.OK.getCode(), CodeStratus.OK.getMessage());
    }

}
