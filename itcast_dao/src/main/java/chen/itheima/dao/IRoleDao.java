package chen.itheima.dao;

import chen.itheima.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "chen.itheima.dao.IPermissionDao.findPermissionByRoleId")),

    })
    public List<Role> findByUserId(String userId) throws Exception;

    @Select("select * from role")
    public List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public void save(Role role);

    @Select("select * from role where id not in( select roleId from users_role where userId=#{id})")
    public List<Role> findOtherRole(String id) throws Exception;

    @Select("select * from role where id =#{roleId}")
    Role findById(String roleId);
    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class, many = @Many(select = "chen.itheima.dao.IPermissionDao.findPermissionByRoleId")),

    })
    public Role findByRoleId(String id)throws Exception;
    @Insert("insert into role_permission(roleId,permissionId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String useId, @Param("roleId") String roleId);

}
