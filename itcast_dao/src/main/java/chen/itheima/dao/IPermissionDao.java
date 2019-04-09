package chen.itheima.dao;

import chen.itheima.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id}) ")
    public List<Permission> findPermissionByRoleId(String id);
    @Select("select * from permission")
    public List<Permission>findAll()throws Exception;
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    public void save(Permission permission);
    @Select("select * from permission where id not in(select permissionId from role_permission where roleId=#{id})")
    public List<Permission> findOtherPermission(String id) throws Exception;
}
