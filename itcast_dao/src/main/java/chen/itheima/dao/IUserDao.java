package chen.itheima.dao;

import chen.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "chen.itheima.dao.IRoleDao.findByUserId"))}
    )
    public UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Select("select * from users where id=#{id}")
    @Results(
            {
                    @Result(id = true, property = "id", column = "id"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "email", column = "email"),
                    @Result(property = "password", column = "password"),
                    @Result(property = "phoneNum", column = "phoneNum"),
                    @Result(property = "status", column = "status"),
                    @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "chen.itheima.dao.IRoleDao.findByUserId")

                    )}
    )
    UserInfo findById(String id);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String useId, @Param("roleId") String roleId);
}
