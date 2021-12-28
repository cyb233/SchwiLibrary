package xyz.schwi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.schwi.model.User;
import xyz.schwi.service.UserService;
import xyz.schwi.utils.JwtConstant;
import xyz.schwi.utils.JwtTokenUtil;
import xyz.schwi.utils.Result;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/12/9 16:23
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @RequestMapping("/query")
    public Result findList(HttpServletRequest request,
                           @RequestBody
                                   User user) throws Exception {
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        int userId = jwtTokenUtil.getUserId(token);
        int userRole = jwtTokenUtil.getRole(token);
        user.setPageNow(user.getPageNow() == null ? 1 : user.getPageNow());
        user.setPageSize(user.getPageSize() == null ? 5 : user.getPageSize());
        System.out.println(user);
        return userService.findBySearch(userRole, user);
    }

    @RequestMapping("/refreshuser")
    public Result refreshUser(HttpServletRequest request) throws Exception {
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        int userId = jwtTokenUtil.getUserId(token);
        int userRole = jwtTokenUtil.getRole(token);
        return userService.findById(userId);
    }

    @RequestMapping("/login")
    public Result login(@RequestBody User user) throws Exception {
        System.out.println(user);
        return userService.findByLogin(user);
    }

    @RequestMapping("/remove")
    public Result remove(HttpServletRequest request, @RequestParam Integer id) throws Exception {
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        int userId = jwtTokenUtil.getUserId(token);
        int userRole = jwtTokenUtil.getRole(token);
        return userService.remove(userRole, id);
    }

    @RequestMapping("/register")
    public Result register(@RequestBody User user) throws Exception {
        return userService.addByReg(user);
    }

    @RequestMapping("/modify")
    public Result modify(HttpServletRequest request, @RequestBody User user) throws Exception {
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        int userId = jwtTokenUtil.getUserId(token);
        int userRole = jwtTokenUtil.getRole(token);
        System.out.println(user);
        return userService.modify(userRole, user);
    }
}
