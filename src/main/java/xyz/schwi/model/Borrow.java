package xyz.schwi.model;

import java.util.Date;

/**
 * @author schwi
 * @date 2021/12/22 0022  09:11
 */
public class Borrow {
    private Integer id;
    private Integer status;
    private Integer borrow;
    private Integer uid;
    private Integer bid;
    private Date borrowtime;
    private Integer duration;
    private String username;
    private String bookname;
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

    public Integer getBorrow() {
        return borrow;
    }

    public void setBorrow(Integer borrow) {
        this.borrow = borrow;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Date getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(Date borrowtime) {
        this.borrowtime = borrowtime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
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
        return "Borrow{" +
                "id=" + id +
                ", status=" + status +
                ", borrow=" + borrow +
                ", uid=" + uid +
                ", bid=" + bid +
                ", borrowtime=" + borrowtime +
                ", duration=" + duration +
                ", username='" + username + '\'' +
                ", bookname='" + bookname + '\'' +
                ", pageSize=" + pageSize +
                ", pageNow=" + pageNow +
                '}';
    }
}
