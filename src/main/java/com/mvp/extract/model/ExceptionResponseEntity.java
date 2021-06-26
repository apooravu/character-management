package com.mvp.extract.model;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
public class ExceptionResponseEntity {

    private int statusCode;
    private Date timestamp;
    private String message;

}
