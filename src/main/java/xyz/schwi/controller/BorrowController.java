package xyz.schwi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.schwi.model.Book;
import xyz.schwi.model.Borrow;
import xyz.schwi.service.BorrowService;
import xyz.schwi.utils.JwtConstant;
import xyz.schwi.utils.JwtTokenUtil;
import xyz.schwi.utils.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @author schwi
 * @date 2021/12/23 0023  14:19
 */
@RestController
@RequestMapping("/borrow")
@CrossOrigin
public class BorrowController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private BorrowService borrowService;

    @RequestMapping("/query")
    public Result findList(HttpServletRequest request,
                           @RequestBody Borrow borrow) throws Exception {
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        int userId = jwtTokenUtil.getUserId(token);
        int userRole = jwtTokenUtil.getRole(token);
        borrow.setPageNow(borrow.getPageNow() == null ? 1 : borrow.getPageNow());
        borrow.setPageSize(borrow.getPageSize() == null ? 5 : borrow.getPageSize());
        System.out.println(borrow);
        return borrowService.findBySearch(userRole, userId, borrow.getUsername(), borrow.getBookname(), borrow.getPageNow(), borrow.getPageSize());
    }

    @RequestMapping("/remove")
    public Result remove(HttpServletRequest request, @RequestParam Integer id) throws Exception {
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        int userId = jwtTokenUtil.getUserId(token);
        int userRole = jwtTokenUtil.getRole(token);
        return borrowService.remove(userRole, id);
    }

    @RequestMapping("/borrow")
    public Result borrow(HttpServletRequest request, @RequestBody Borrow borrow) throws Exception {
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        int userId = jwtTokenUtil.getUserId(token);
        int userRole = jwtTokenUtil.getRole(token);
        return borrowService.addBorrow(userRole, borrow);
    }

    @RequestMapping("/return")
    public Result returnBorrow(HttpServletRequest request, @RequestBody Borrow borrow) throws Exception {
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        int userId = jwtTokenUtil.getUserId(token);
        int userRole = jwtTokenUtil.getRole(token);
        return borrowService.returnBorrow(userRole, borrow);
    }

    @RequestMapping("/modify")
    public Result modify(HttpServletRequest request, @RequestBody Borrow borrow) throws Exception {
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        int userId = jwtTokenUtil.getUserId(token);
        int userRole = jwtTokenUtil.getRole(token);
        return borrowService.modify(userRole, borrow);
    }
}
