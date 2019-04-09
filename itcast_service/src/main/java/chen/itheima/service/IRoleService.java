package chen.itheima.service;

import chen.itheima.domain.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> findAll() throws Exception;

    void save(Role role) throws Exception;

    List<Role> findOtherRole(String userId) throws Exception;

    Role findById(String roleId) throws Exception;

    Role findByRoleId(String roleId) throws Exception;

    void addRoleToUser(String roleId, String[] permissionIds);
}
