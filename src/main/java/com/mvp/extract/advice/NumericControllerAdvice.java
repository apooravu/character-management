package com.mvp.extract.advice;

import com.mvp.extract.exception.ExecutionInterruptedException;
import com.mvp.extract.exception.InputStreamOrReadException;
import com.mvp.extract.exception.NoFileOrContentFoundException;
import com.mvp.extract.model.ExceptionResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


@ControllerAdvice
@ResponseBody
public class NumericControllerAdvice {


    @ExceptionHandler(NoFileOrContentFoundException.class)
    public ExceptionResponseEntity handleProcessException(NoFileOrContentFoundException e) {
        return new ExceptionResponseEntity(HttpStatus.NO_CONTENT.value(),
                new Date(),
                e.getMessage());
    }

    @ExceptionHandler(InputStreamOrReadException.class)
    public ExceptionResponseEntity handleInputStreamOrReadException(InputStreamOrReadException e) {
        return new ExceptionResponseEntity(HttpStatus.EXPECTATION_FAILED.value(),
                new Date(),
                e.getMessage());
    }


    @ExceptionHandler(ExecutionInterruptedException.class)
    public ExceptionResponseEntity handleExecutionInterruptedException(ExecutionInterruptedException e) {
        return new ExceptionResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                new Date(),
                e.getMessage());
    }

}

