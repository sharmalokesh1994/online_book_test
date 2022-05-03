package com.rig.book.exceptionshandler;

import com.rig.book.exceptions.BadRequestException;
import com.rig.book.model.ErrorResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppExceptionHandler {

    private static final Integer INTERNAL_SERVER_FAILURE_CODE = 500;
    private static final String INTERNAL_SERVER_EXCEPTION_TYPE = "Internal service Error";


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseModel handle(BadRequestException exception) {
        return new ErrorResponseModel(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }


    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseModel handle(Exception exception) {
        return new ErrorResponseModel(INTERNAL_SERVER_FAILURE_CODE, INTERNAL_SERVER_EXCEPTION_TYPE);
    }

}
