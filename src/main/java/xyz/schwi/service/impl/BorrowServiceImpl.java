package xyz.schwi.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.schwi.exception.BookAlreadyBorrowedException;
import xyz.schwi.exception.RoleNotEnoughException;
import xyz.schwi.mapper.BookMapper;
import xyz.schwi.mapper.BorrowMapper;
import xyz.schwi.mapper.UserMapper;
import xyz.schwi.model.Book;
import xyz.schwi.model.Borrow;
import xyz.schwi.service.BorrowService;
import xyz.schwi.utils.Result;

import java.util.List;

/**
 * @author schwi
 * @date 2021/12/23 0023  10:49
 */
@Service
@Transactional(readOnly = true)
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result findBySearch(int userRole, int userId, String username, String bookname, int pageNow, int pageSize) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<Borrow> list = borrowMapper.selectBySearch(userRole, userId, username, bookname);
        PageInfo pageInfo = new PageInfo(list);
        Result result = new Result();
        result.setCode(Result.CODE_SUCCESS);
        result.setMsg(Result.MSG_SUCCESS);
        result.setData(pageInfo);
        result.setCount(pageInfo.getTotal());
        return result;
    }

    @Transactional
    @Override
    public Result remove(int userRole, int id) throws Exception {
        int remove = 0;
        if (userRole != 0) {
            remove = borrowMapper.deleteById(userRole, id);
        } else {
            throw new RoleNotEnoughException();
        }
        Result result = new Result();
        if (remove == 1) {
            result.setCode(Result.CODE_SUCCESS);
            result.setMsg(Result.MSG_SUCCESS);
        } else {
            result.setCode(Result.CODE_ERROR_USER_DELETE);
            result.setMsg(Result.MSG_ERROR_USER_DELETE);
        }
        return result;
    }

    @Transactional
    @Override
    public Result addBorrow(int userRole, Borrow borrow) throws Exception {
        int insert = borrowMapper.insertBorrow(userRole, borrow);
        int updateBook = bookMapper.updateByBorrow(userRole, borrow.getBid());
        int updateUser = userMapper.updateByBorrow(userRole, borrow.getUid());
        Result result = new Result();
        if (insert == 1 && updateBook == 1 && updateUser == 1) {
            result.setCode(Result.CODE_SUCCESS);
            result.setMsg(Result.MSG_SUCCESS);
        } else {
            throw new BookAlreadyBorrowedException();
        }
        return result;
    }

    @Transactional
    @Override
    public Result returnBorrow(int userRole, Borrow borrow) throws Exception {
        int insert = borrowMapper.returnBorrow(userRole, borrow);
        int updateBook = bookMapper.updateByReturn(userRole, borrow.getBid());
        int updateUser = userMapper.updateByReturn(userRole, borrow.getUid());
        Result result = new Result();
        if (insert == 1 && updateBook == 1 && updateUser == 1) {
            result.setCode(Result.CODE_SUCCESS);
            result.setMsg(Result.MSG_SUCCESS);
        } else {
            throw new BookAlreadyBorrowedException();
        }
        return result;
    }

    @Transactional
    @Override
    public Result modify(int userRole, Borrow borrow) throws Exception {
        int update = 0;
        if (userRole != 0) {
            update = borrowMapper.updateBorrow(userRole, borrow);
        } else {
            throw new RoleNotEnoughException();
        }
        Result result = new Result();
        if (update == 1) {
            result.setCode(Result.CODE_SUCCESS);
            result.setMsg(Result.MSG_SUCCESS);
        } else {
            result.setCode(Result.CODE_ERROR_BORROW_UPDATE);
            result.setMsg(Result.MSG_ERROR_BORROW_UPDATE);
        }
        return result;
    }
}
