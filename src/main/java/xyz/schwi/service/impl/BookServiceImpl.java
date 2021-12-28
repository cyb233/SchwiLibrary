package xyz.schwi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.schwi.exception.RoleNotEnoughException;
import xyz.schwi.mapper.BookMapper;
import xyz.schwi.model.Book;
import xyz.schwi.service.BookService;
import xyz.schwi.utils.Result;

import java.util.List;

/**
 * @author schwi
 * @date 2021/12/22 0022  14:09
 */
@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Result findBySearch(int userRole, String bookname, String author, int pageNow, int pageSize) throws Exception {
        PageHelper.startPage(pageNow, pageSize);
        List<Book> list = bookMapper.selectBySearch(userRole, bookname, author);
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
            remove = bookMapper.deleteById(userRole, id);
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
    public Result addBook(int userRole, Book book) throws Exception {
        int insert = 0;
        if (userRole != 0) {
            insert = bookMapper.insertBook(userRole, book);
        } else {
            throw new RoleNotEnoughException();
        }
        Result result = new Result();
        if (insert == 1) {
            result.setCode(Result.CODE_SUCCESS);
            result.setMsg(Result.MSG_SUCCESS);
        } else {
            result.setCode(Result.CODE_ERROR_BOOK_INSERT);
            result.setMsg(Result.MSG_ERROR_BOOK_INSERT);
        }
        return result;
    }

    @Transactional
    @Override
    public Result modify(int userRole, Book book) throws Exception {
        int update = 0;
        if (userRole != 0) {
            update = bookMapper.updateBook(userRole, book);
        } else {
            throw new RoleNotEnoughException();
        }
        Result result = new Result();
        if (update == 1) {
            result.setCode(Result.CODE_SUCCESS);
            result.setMsg(Result.MSG_SUCCESS);
        } else {
            result.setCode(Result.CODE_ERROR_BOOK_UPDATE);
            result.setMsg(Result.MSG_ERROR_BOOK_UPDATE);
        }
        return result;
    }
}
