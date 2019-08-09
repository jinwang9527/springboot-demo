package com.firststringboot.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class BaseBoundVo implements BaseVo {


    private int pageIndex;


    private int pageSize;


    private String order;


    private Date startCreateTime;


    private Date endCreateTime;


}
