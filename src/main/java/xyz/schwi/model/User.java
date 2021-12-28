package xyz.schwi.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/12/9 16:20
 * @Version 1.0
 */
public class User implements Serializable {
    private Integer id;
    private Integer status;
    private String username;
    private String password;
    private Integer role;
    private Date createtime;
    private Integer borrowtimes;
    private Integer borrownow;
    private Integer pageSize;
    private Integer pageNow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getBorrowtimes() {
        return borrowtimes;
    }

    public void setBorrowtimes(Integer borrowtimes) {
        this.borrowtimes = borrowtimes;
    }

    public Integer getBorrownow() {
        return borrownow;
    }

    public void setBorrownow(Integer borrownow) {
        this.borrownow = borrownow;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNow() {
        return pageNow;
    }

    public void setPageNow(Integer pageNow) {
        this.pageNow = pageNow;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", createtime=" + createtime +
                ", borrowtimes=" + borrowtimes +
                ", borrownow=" + borrownow +
                ", pageSize=" + pageSize +
                ", pageNow=" + pageNow +
                '}';
    }
}
