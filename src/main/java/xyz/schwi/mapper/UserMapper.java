package xyz.schwi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.schwi.model.User;

import java.util.List;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author Administrator
 * @Date: 2021/12/9 16:22
 * @Version 1.0
 */
public interface UserMapper {

    @Select("<script>" +
            "select id,status,username,role,createtime,borrowtimes,borrownow " +
            "from libuser " +
            "<where>" +
            "<if test='username!=null and username!=\"\"'>username like concat('%',#{username},'%')</if>" +
            "<if test='userRole==0'> and status=0</if>" +
            "</where>" +
            " order by id</script>")
    List<User> selectBySearch(@Param("userRole") int userRole, @Param("username") String username) throws Exception;

    @Select("select id,status,username,role,createtime,borrowtimes,borrownow " +
            "from libuser " +
            "where username=#{username} and password=#{password} and status=0")
    User selectByLogin(User user) throws Exception;

    @Select("select id,status,username,role,createtime,borrowtimes,borrownow " +
            "from libuser " +
            "where id=#{userId} and status=0")
    User selectById(int userId) throws Exception;

    @Update("update libuser " +
            "set status=1 " +
            "where id=#{id}")
    int deleteById(@Param("userRole") int userRole, @Param("id") int id) throws Exception;

    @Insert("insert into libuser(username,password) " +
            "values (#{username},#{password})")
    int insertByRegister(User user) throws Exception;

    @Update("<script>" +
            "update libuser " +
            "<set>" +
            "<if test='userRole==1 and user.status!=null'>status=#{user.status},</if>" +
            "<if test='user.username!=null and user.username!=\"\"'>username=#{user.username},</if>" +
            "<if test='user.password!=null and user.password!=\"\"'>password=#{user.password},</if>" +
            "<if test='userRole==1 and user.role!=null'>role=#{user.role},</if>" +
            "</set>" +
            "where id=#{user.id}" +
            "</script>")
    int updateUser(@Param("userRole") int userRole, @Param("user") User user) throws Exception;

    @Update("update libuser " +
            "set borrownow=borrownow+1,borrowtimes=borrowtimes+1 " +
            "where id=#{id}")
    int updateByBorrow(@Param("userRole") int userRole, @Param("id") int id) throws Exception;

    @Update("update libuser " +
            "set borrownow=borrownow-1 " +
            "where id=#{id} and borrownow>0")
    int updateByReturn(@Param("userRole") int userRole, @Param("id") int id) throws Exception;
}
