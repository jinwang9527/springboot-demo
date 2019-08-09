package com.firststringboot.common.Exception;

import java.io.Serializable;

public class CustomException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -3854142442029578532L;

    public CustomException(String errorMessage){
         super(errorMessage);
    }
}
