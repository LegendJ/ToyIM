package com.just.toyim.api.exception;

import com.just.toyim.pojo.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.List;
import java.util.stream.Collectors;

/**
 * handle all exception thrown by the application
 */

@ControllerAdvice
@ResponseBody
public class APIExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(APIExceptionHandler.class);

    private static final HttpResponse ERROR;

    static {
        ERROR = new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR).setMsg("系统出错,请稍候再试");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResponse resolveException(Exception exception) {
        LOG.error(exception.getMessage(), exception);
        return ERROR;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResponse handleSecurityException(Exception exception) {
        return new HttpResponse(HttpStatus.INTERNAL_SERVER_ERROR).setMsg(exception.getMessage());
    }

    /**
     * 描述：表单数据格式不正确异常提示
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse handleIllegalParamException(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getBindingResult().getFieldErrors();
        String tips = "参数不合法";
        HttpResponse result = new HttpResponse(HttpStatus.BAD_REQUEST);
        if (!errors.isEmpty()) {
            List<String> list = errors.stream()
                    .map(error -> error.getField() + error.getDefaultMessage())
                    .collect(Collectors.toList());
            result.put("details", list);
        }
        result.setMsg(tips);
        return result;
    }

    /**
     * 描述：表单数据缺失异常提示
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse handleServletRequestParameterException(MissingServletRequestParameterException exception) {
        return new HttpResponse(HttpStatus.BAD_REQUEST).setMsg(exception.getMessage());
    }

    /**
     * 描述：请求方法不支持异常提示
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public HttpResponse handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        String supportedMethods = exception.getSupportedHttpMethods().stream()
                .map(method -> method.toString())
                .collect(Collectors.joining("/"));

        String msg = "请求方法不合法,请使用方法" + supportedMethods;
        return new HttpResponse(HttpStatus.METHOD_NOT_ALLOWED).setMsg(msg);
    }

    /**
     * 描述：数据绑定失败异常提示
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResponse handleValidationBindException(BindException exception) {
        String errors = exception.getFieldErrors().stream()
                .map(error -> error.getField() + error.getDefaultMessage())
                .collect(Collectors.joining(","));
        return new HttpResponse(HttpStatus.BAD_REQUEST).setMsg(errors);
    }
}
