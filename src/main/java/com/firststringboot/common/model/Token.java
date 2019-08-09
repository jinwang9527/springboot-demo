package com.firststringboot.common.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Token implements Serializable {


    private static final long serialVersionUID = -7303928294377607692L;


    private Long userId;
}
