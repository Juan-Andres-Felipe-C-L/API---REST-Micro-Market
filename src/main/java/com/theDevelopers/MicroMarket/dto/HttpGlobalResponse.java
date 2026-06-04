package com.theDevelopers.MicroMarket.dto;

import lombok.Data;

@Data
public class HttpGlobalResponse<T> {

    private int statusCode;
    private String message;
    private T data;
    
}
