package com.fykj.scaffold.exception;

import exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;
import result.Result;
import result.ResultCode;
import utils.StringUtil;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;
import java.sql.SQLFeatureNotSupportedException;

/**
 * @author zhangzhi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result noHandlerFoundException(NoHandlerFoundException exception) {
        return new Result(ResultCode.NOT_FOUND.code(), exception.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result handleException(BusinessException exception, HttpServletResponse response) {
        String message = exception.getMessage();
        int code = exception.getHttpCode().code();
        logger.error("exception:", exception);
        if (StringUtil.isEmpty(message)) {
            return new Result(exception.getHttpCode());
        }
        return new Result(code, message);
    }

    @ExceptionHandler(BindException.class)
    public Result handleException(BindException exception) {
        return new Result(ResultCode.NOT_FOUND.code(), exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result handleException(AccessDeniedException exception) {
        return new Result(ResultCode.UNAUTHORIZED.code(), exception.getMessage());
    }

    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public Result handleMultipartException(MaxUploadSizeExceededException e) {
        String message = "文件超过最大限制:";
        return new Result(ResultCode.BAD_REQUEST.code(), message + e.getLocalizedMessage());
    }

    @ExceptionHandler(Throwable.class)
    public Result handleException(Throwable e) {
        StackTraceElement element = e.getStackTrace()[0];
        String fullClassName = element.getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
        String methodName = element.getMethodName();
        int lineNum = element.getLineNumber();
        String message = String.format("%s:%s:%d:%s", className, methodName, lineNum, e.getLocalizedMessage());
        logger.error(message, e);
        return new Result(ResultCode.ERROR.code(), "服务器异常，请联系管理员");
    }

    /**
     * 处理'@Valid'注解验证实体出错
     *
     * @param e MethodArgumentNotValidException
     * @return 错误提示
     */
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
//        BindingResult result = e.getBindingResult();
//        ValidResult validResult = new ValidResult();
//        result.getFieldErrors().forEach(it -> validResult.addResult(it.getField(), it.getDefaultMessage()));
//        return new ObjectRestResponse<>(ResultCode.NOT_VALID, validResult);
//    }
}

