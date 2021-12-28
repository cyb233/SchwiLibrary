package xyz.schwi.service;

import xyz.schwi.model.Book;
import xyz.schwi.utils.Result;

/**
 * @author schwi
 * @date 2021/12/22 0022  14:07
 */
public interface BookService {

    Result findBySearch(int userRole, String bookname, String author, int pageNow, int pageSize) throws Exception;

    Result remove(int userRole, int id) throws Exception;

    Result addBook(int userRole, Book book) throws Exception;

    Result modify(int userRole, Book book) throws Exception;
}
