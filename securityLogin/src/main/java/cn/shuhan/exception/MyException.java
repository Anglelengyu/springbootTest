package cn.shuhan.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理
 */
@RestControllerAdvice
public class MyException {

    @ExceptionHandler(AccessDeniedException.class)
    public void accessDeniedExceptionHander(Exception e) throws Exception {
//        e.printStackTrace();
        throw new Exception("拒绝访问");
    }
}
