package xyz.schwi.interceptor;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import xyz.schwi.utils.JwtConstant;
import xyz.schwi.utils.JwtTokenUtil;
import xyz.schwi.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @ClassName TokenInterceptor
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/12/15 14:14
 * @Version 1.0
 */
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    JwtTokenUtil JwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getMethod() + " " + request.getRequestURI());
        String method = request.getMethod();
        if (HttpMethod.OPTIONS.toString().equalsIgnoreCase(method)) {
            System.out.println("OPTIONS放行");
            return true;
        }
        //1.获取token,先校验是否合法，再校验是否过期
        String token = request.getHeader(JwtConstant.ACCESS_TOKEN);
        //2.如果不合法或已过期或没有token，则让其重新登录
        Boolean valid = JwtTokenUtil.validateToken(token);
        Result result = new Result();
        if (valid) {
            //有效,则放行
            System.out.println("有效放行");
            request.getParameterMap().forEach((k, v) -> System.out.println(k + Arrays.toString(v)));

            return true;
        } else {
            if (token == null) {
                System.out.println("无token拦截");
                result.setMsg("please login!!");
            } else {
                Boolean tokenExpired = JwtTokenUtil.isTokenExpired(token);
                if (tokenExpired) {
                    System.out.println("过期token拦截");
                    result.setMsg("token expired,please login!!");
                } else {
                    System.out.println("非法token拦截");
                    result.setMsg("token is invalid,please login!!");
                }
            }
            result.setCode(1);
            response.getWriter().write(JSON.toJSONString(result));
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
