package xyz.schwi.model;

import java.util.Date;

/**
 * @author schwi
 * @date 2021/12/22 0022  09:03
 */
public class Book {
    private Integer id;
    private Integer status;
    private String name;
    private String author;
    private Integer borrow;
    private String content;
    private Date createtime;
    private Integer borrowtimes;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getBorrow() {
        return borrow;
    }

    public void setBorrow(Integer borrow) {
        this.borrow = borrow;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Book{" +
                "id=" + id +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", borrow=" + borrow +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                ", borrowtimes=" + borrowtimes +
                ", pageSize=" + pageSize +
                ", pageNow=" + pageNow +
                '}';
    }
}
