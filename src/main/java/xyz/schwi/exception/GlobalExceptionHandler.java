package xyz.schwi.exception;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.schwi.utils.Result;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/12/20 13:47
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public Result respError(Exception e) {
        //写日志
        Result result = new Result();
        if (e.getCause() instanceof SQLIntegrityConstraintViolationException) {
            //用户名已存在
            System.out.println("用户名已存在");
            e.printStackTrace();
            result.setCode(Result.CODE_ERROR_USER_REGISTER);
            result.setMsg(Result.MSG_ERROR_USER_REGISTER_EXIST);
        } else if (e instanceof RoleNotEnoughException) {
            //权限不足
            System.out.println("权限不足");
            result.setCode(Result.CODE_ERROR_USER_ROLE);
            result.setMsg(Result.MSG_ERROR_USER_ROLE);
        } else if (e instanceof BookAlreadyBorrowedException) {
            //书籍已经借出
            System.out.println("书籍已经借出");
            result.setCode(Result.CODE_ERROR_BOOK_BORROWED);
            result.setMsg(Result.MSG_ERROR_BOOK_BORROWED);
        } else {
            //其他未知异常，写日志
            LOGGER.error("业务异常", e);
            result.setCode(Result.CODE_ERROR);
            result.setMsg(Result.MSG_ERROR);
        }
        return result;

    }
}
