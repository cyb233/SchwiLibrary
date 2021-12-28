package xyz.schwi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.schwi.model.Borrow;

import java.util.List;

/**
 * @author schwi
 * @date 2021/12/23 0023  09:18
 */
public interface BorrowMapper {

    @Select("<script>" +
            "select " +
            "r.*," +
            "u.username," +
            "b.name bookname " +
            "from libborrow r,libuser u,libbook b " +
            "where r.uid=u.id and r.bid=b.id and b.name like concat('%',#{bookname},'%')" +
            "<if test='userRole!=0'> and u.username like concat('%',#{username},'%')</if>" +
            "<if test='userRole==0'> and r.status=0 and r.uid=#{userId}</if>" +
            " order by r.id DESC</script>")
    List<Borrow> selectBySearch(@Param("userRole") int userRole, @Param("userId") int userId, @Param("username") String username, @Param("bookname") String bookname) throws Exception;

    @Update("update libborrow " +
            "set status=1 " +
            "where id=#{id}")
    int deleteById(@Param("userRole") int userRole, @Param("id") int id) throws Exception;

    @Insert("insert into libborrow(uid,bid,duration) " +
            "values (#{borrow.uid},#{borrow.bid},#{borrow.duration})")
    int insertBorrow(@Param("userRole") int userRole, @Param("borrow") Borrow borrow) throws Exception;

    @Update("update libborrow " +
            "set borrow=1 " +
            "where uid=#{borrow.uid} and bid=#{borrow.bid} and borrow=0")
    int returnBorrow(@Param("userRole") int userRole, @Param("borrow") Borrow borrow) throws Exception;

    @Update("<script>" +
            "update libborrow " +
            "<set>" +
            "<if test='borrow.status!=null'>status=#{borrow.status},</if>" +
            "<if test='borrow.status!=null'>borrow=#{borrow.borrow},</if>" +
            "<if test='borrow.status!=null'>duration=duration+#{borrow.duration},</if>" +
            "</set>" +
            "where id=#{borrow.id}" +
            "</script>")
    int updateBorrow(@Param("userRole") int userRole, @Param("borrow") Borrow borrow) throws Exception;
}
