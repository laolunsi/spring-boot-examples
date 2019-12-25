package com.example.exceptiondemo.config;

import com.example.exceptiondemo.exception.UserNotExistException;
import com.example.exceptiondemo.model.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author zfh
 * @version 1.0
 * @since 2019/12/5 15:43
 */
@ControllerAdvice
public class ControllerHandler {

    /**
     * 处理全部异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult handleException(Exception ex) {
        System.out.println("程序异常：" + ex.toString());
        return new JsonResult(false, "请求失败");
    }

    /**
     * 处理UserNotExistException异常
     * @param ex
     * @return
     */
    @ExceptionHandler({UserNotExistException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult handleUserNotExistException(UserNotExistException ex) {
        System.out.println("请求用户数据异常：" + ex.toString());
        return new JsonResult(false, "请求用户数据失败");
    }

}
