package net.canway.demo.mvc.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 相当于后端控制器的切面
 * 必须要标注@ControllerAdvice  注解
 */
@ControllerAdvice
public class CommonController {

    /**
     * @param binder
     * @InitBinder 可以指定转换规则注入到参数转换器
     */
    @InitBinder
    public void initBind(WebDataBinder binder) {
        PropertyEditor propertyEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true);
        binder.registerCustomEditor(Date.class, propertyEditor);
    }


    /**
     * 全局异常处理，如果需要返回序列化数据  务必表上@ResponseBody，
     * 否则它会去找相应的响应处理器， 找不着会抛404
     *
     * @param e
     * @return
     * @throws Exception
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public Result exceptionHandler(Exception e) throws Exception {
        if (e instanceof MissingServletRequestParameterException) {
            Result result = new Result();
            result.setCode("401");
            result.setMessage("必填参数没有传入");
            return result;
        }
        if (e instanceof MethodArgumentNotValidException) {
            String errorMessage = ((MethodArgumentNotValidException) e)
                    .getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining());
            Result result = new Result();
            result.setCode("401");
            result.setMessage(errorMessage);
            return result;
        } else if (e instanceof ConstraintViolationException) {
            String errorMessage = ((ConstraintViolationException) e)
                    .getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining());
            Result result = new Result();
            result.setCode("401");
            result.setMessage(errorMessage);
            return result;
        }

        else {
            throw e;
        }
    }
}
