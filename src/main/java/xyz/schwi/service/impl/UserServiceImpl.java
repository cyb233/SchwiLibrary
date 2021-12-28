package xyz.schwi.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.schwi.exception.RoleNotEnoughException;
import xyz.schwi.exception.UserNotExistException;
import xyz.schwi.mapper.UserMapper;
import xyz.schwi.model.User;
import xyz.schwi.service.UserService;
import xyz.schwi.utils.JwtTokenUtil;
import xyz.schwi.utils.Result;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/12/9 16:23
 * @Version 1.0
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private JwtTokenUtil tokenUtil;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Result findBySearch(int userRole, User user) throws Exception {
        List<User> list;
        Result result = new Result();
        PageHelper.startPage(user.getPageNow(), user.getPageSize());
        if (userRole != 0) {
            list = userMapper.selectBySearch(userRole, user.getUsername());
        } else {
            throw new RoleNotEnoughException();
        }
        PageInfo pageInfo = new PageInfo(list);
        result.setCode(Result.CODE_SUCCESS);
        result.setMsg(Result.MSG_SUCCESS);
        result.setData(pageInfo);
        result.setCount(pageInfo.getTotal());
        return result;
    }


    @Override
    public Result findByLogin(User user) throws Exception {
        User findUser = userMapper.selectByLogin(user);
        Result result = new Result();
        if (findUser != null) {
            System.out.println(findUser);
            findUser.setPassword(null);//java--json--JSONobject->map
            result.setData(findUser);
            result.setCode(Result.CODE_SUCCESS);
            result.setMsg(Result.MSG_SUCCESS);
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(findUser));
            Map<String, Object> claim = jsonObject.getInnerMap();
            String jwt = tokenUtil.getAccessToken(findUser.getId().toString(), claim);
            result.setToken(jwt);
        } else {
            result.setCode(Result.CODE_ERROR_USER_LOGIN);
            result.setMsg(Result.MSG_ERROR_USER_LOGIN);
        }
        return result;
    }

    @Override
    public Result findById(int userId) throws Exception {
        Result result = new Result();
        User user1 = userMapper.selectById(userId);
        if (user1 != null) {
            result.setCode(Result.CODE_SUCCESS);
            result.setMsg(Result.MSG_SUCCESS);
            result.setData(user1);
            return result;
        }
        throw new UserNotExistException();
    }

    @Transactional
    @Override
    public Result remove(int userRole, int id) throws Exception {
        int remove = 0;
        if (userRole != 0) {
            remove = userMapper.deleteById(userRole, id);
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
    public Result addByReg(User user) throws Exception {
        int add = userMapper.insertByRegister(user);
        Result result = new Result();
        if (add == 1) {
            result.setCode(Result.CODE_SUCCESS);
            result.setMsg(Result.MSG_SUCCESS);
        } else {
            result.setCode(Result.CODE_ERROR_USER_REGISTER);
            result.setMsg(Result.MSG_ERROR_USER_REGISTER);
        }
        return result;
    }

    @Transactional
    @Override
    public Result modify(int userRole, User user) throws Exception {
        int update = 0;
        Result result = new Result();
        update = userMapper.updateUser(userRole, user);
        if (update == 1) {
            result.setCode(Result.CODE_SUCCESS);
            result.setMsg(Result.MSG_SUCCESS);
        } else {
            result.setCode(Result.CODE_ERROR_USER_UPDATE);
            result.setMsg(Result.MSG_ERROR_USER_UPDATE);
        }
        return result;
    }
}
