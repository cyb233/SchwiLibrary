package xyz.schwi.utils;

/**
 * @ClassName Result
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/12/9 16:55
 * @Version 1.0
 */
public class Result {

    public final static String MSG_SUCCESS = "success";
    public final static String MSG_ERROR = "error";
    public final static Integer CODE_SUCCESS = 0;
    public final static Integer CODE_ERROR = 1;

    public final static String MSG_ERROR_USER_ROLE = "user role not enough";
    public final static Integer CODE_ERROR_USER_ROLE = 100;

    public final static String MSG_ERROR_USER_REGISTER = "user register error";
    public final static String MSG_ERROR_USER_REGISTER_EXIST = "user register exist error";
    public final static Integer CODE_ERROR_USER_REGISTER = 101;
    public final static String MSG_ERROR_USER_LOGIN = "user login error";
    public final static Integer CODE_ERROR_USER_LOGIN = 102;
    public final static String MSG_ERROR_USER_UPDATE = "user update error";
    public final static Integer CODE_ERROR_USER_UPDATE = 103;
    public final static String MSG_ERROR_USER_DELETE = "user delete error";
    public final static Integer CODE_ERROR_USER_DELETE = 104;

    public final static String MSG_ERROR_BOOK_BORROWED = "book already borrowed";
    public final static Integer CODE_ERROR_BOOK_BORROWED = 200;
    public final static String MSG_ERROR_BOOK_INSERT = "book add error";
    public final static Integer CODE_ERROR_BOOK_INSERT = 201;
    public final static String MSG_ERROR_BOOK_SEARCH = "book search error";
    public final static Integer CODE_ERROR_BOOK_SEARCH = 202;
    public final static String MSG_ERROR_BOOK_UPDATE = "book update error";
    public final static Integer CODE_ERROR_BOOK_UPDATE = 203;
    public final static String MSG_ERROR_BOOK_DELETE = "book delete error";
    public final static Integer CODE_ERROR_BOOK_DELETE = 204;

    public final static String MSG_ERROR_BORROW_INSERT = "borrow add error";
    public final static Integer CODE_ERROR_BORROW_INSERT = 301;
    public final static String MSG_ERROR_BORROW_SEARCH = "borrow search error";
    public final static Integer CODE_ERROR_BORROW_SEARCH = 302;
    public final static String MSG_ERROR_BORROW_UPDATE = "borrow update error";
    public final static Integer CODE_ERROR_BORROW_UPDATE = 303;
    public final static String MSG_ERROR_BORROW_DELETE = "borrow delete error";
    public final static Integer CODE_ERROR_BORROW_DELETE = 304;

    private Integer code;
    private String msg;
    private Object data;
    private Long count;
    private String token;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
