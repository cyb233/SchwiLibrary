package xyz.schwi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.schwi.model.Book;

import java.util.List;

/**
 * @author schwi
 * @date 2021/12/22 0022  11:24
 */
public interface BookMapper {

    @Select("<script>" +
            "select * " +
            "from libbook " +
            "where name like concat('%',#{name},'%') and author like concat('%',#{author},'%') " +
            "<if test='userRole==0'>and status=0</if>" +
            " order by id</script>")
    List<Book> selectBySearch(@Param("userRole") int userRole, @Param("name") String bookname, @Param("author") String author) throws Exception;

    @Update("update libbook " +
            "set status=1 " +
            "where id=#{id}")
    int deleteById(@Param("userRole") int userRole, @Param("id") int id) throws Exception;

    @Insert("insert into libbook(name,author,content) " +
            "values (#{book.name},#{book.author},#{book.content})")
    int insertBook(@Param("userRole") int userRole, @Param("book") Book book) throws Exception;

    @Update("<script>" +
            "update libbook " +
            "<set>" +
            "<if test='book.status!=null'>status=#{book.status},</if>" +
            "<if test='book.name!=null and book.name!=\"\"'>name=#{book.name},</if>" +
            "<if test='book.author!=null and book.author!=\"\"'>author=#{book.author},</if>" +
            "<if test='book.content!=null and book.content!=\"\"'>content=#{book.content},</if>" +
            "<if test='book.borrow!=null'>borrow=#{book.borrow},</if>" +
            "</set>" +
            "where id=#{book.id}" +
            "</script>")
    int updateBook(@Param("userRole") int userRole, @Param("book") Book book) throws Exception;

    @Update("update libbook " +
            "set borrow=1,borrowtimes=borrowtimes+1 " +
            "where id=#{id} and borrow=0")
    int updateByBorrow(@Param("userRole") int userRole, @Param("id") int id) throws Exception;

    @Update("update libbook " +
            "set borrow=0 " +
            "where id=#{id} and borrow=1")
    int updateByReturn(@Param("userRole") int userRole, @Param("id") int id) throws Exception;
}
