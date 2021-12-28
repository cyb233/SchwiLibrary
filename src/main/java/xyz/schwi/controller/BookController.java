package xyz.schwi.controller;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.schwi.model.Book;
import xyz.schwi.service.BookService;
import xyz.schwi.utils.JwtConstant;
import xyz.schwi.utils.JwtTokenUtil;
import xyz.schwi.utils.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author schwi
 * @date 2021/12/22 0022  16:27
 */
@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private BookService bookService;

    @RequestMapping("/query")
    public Result findList(HttpServletRequest request,
                           @RequestBody Book book) throws Exception {
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        int userRole = jwtTokenUtil.getRole(token);
        book.setPageNow(book.getPageNow() == null ? 1 : book.getPageNow());
        book.setPageSize(book.getPageSize() == null ? 5 : book.getPageSize());
        System.out.println(book);
        return bookService.findBySearch(userRole, book.getName(), book.getAuthor(), book.getPageNow(), book.getPageSize());
    }

    @RequestMapping("/remove")
    public Result remove(HttpServletRequest request, @RequestParam Integer id) throws Exception {
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        int userId = jwtTokenUtil.getUserId(token);
        int userRole = jwtTokenUtil.getRole(token);
        return bookService.remove(userRole, id);
    }

    @RequestMapping("/add")
    public Result add(HttpServletRequest request, @RequestBody Book book) throws Exception {
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        int userId = jwtTokenUtil.getUserId(token);
        int userRole = jwtTokenUtil.getRole(token);
        return bookService.addBook(userRole, book);
    }

    @RequestMapping("/modify")
    public Result modify(HttpServletRequest request, @RequestBody Book book) throws Exception {
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        int userId = jwtTokenUtil.getUserId(token);
        int userRole = jwtTokenUtil.getRole(token);
        return bookService.modify(userRole, book);
    }

}
