package xyz.schwi.service;


import xyz.schwi.model.User;
import xyz.schwi.utils.Result;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/12/9 16:22
 * @Version 1.0
 */
public interface UserService {

    Result findBySearch(int userRole, User user) throws Exception;

    Result findByLogin(User user) throws Exception;

    Result findById(int userId) throws Exception;

    Result remove(int userRole, int id) throws Exception;

    Result addByReg(User user) throws Exception;

    Result modify(int userRole, User user) throws Exception;
}
