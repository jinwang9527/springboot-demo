package com.firststringboot.service;

import com.firststringboot.common.Exception.CustomException;
import com.firststringboot.common.model.PageResultModel;
import com.firststringboot.common.model.ResultModel;
import com.firststringboot.common.utils.PageBoundsUtil;
import com.firststringboot.pojo.BaseBoundVo;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface BaseService {


    Logger logger = LoggerFactory.getLogger(BaseService.class);


    default PageBounds of() {
        return PageBoundsUtil.of();
    }

    default PageBounds of(BaseBoundVo baseBoundVo) {
        return PageBoundsUtil.of(baseBoundVo);
    }

    default ResultModel responseSuccess() {
        return ResultModel.getSuccessResultModel();
    }

    default <T> ResultModel responseSuccess(T data) {
        return ResultModel.getSuccessResultModel(data);
    }

    default <T> PageResultModel responseSuccess(T data, Paginator paginator) {
        return PageResultModel.getSuccessPageResultModel(data, paginator);
    }

    default void responseError(String message) throws Exception {
        throw new CustomException(message);
    }


}
