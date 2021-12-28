package xyz.schwi.service;

import xyz.schwi.model.Borrow;
import xyz.schwi.utils.Result;

/**
 * @author schwi
 * @date 2021/12/23 0023  10:48
 */
public interface BorrowService {

    Result findBySearch(int userRole, int userId, String username, String bookname, int pageNow, int pageSize) throws Exception;

    Result remove(int userRole, int id) throws Exception;

    Result addBorrow(int userRole, Borrow borrow) throws Exception;

    Result returnBorrow(int userRole, Borrow borrow) throws Exception;

    Result modify(int userRole, Borrow borrow) throws Exception;
}
