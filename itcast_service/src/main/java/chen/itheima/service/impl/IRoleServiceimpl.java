package chen.itheima.service.impl;

import chen.itheima.dao.IRoleDao;
import chen.itheima.domain.Role;
import chen.itheima.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IRoleServiceimpl implements IRoleService {
    @Autowired
    private IRoleDao iRoleDao;


    public void save(Role role){
        iRoleDao.save(role);
    }

    @Override
    public List<Role> findOtherRole(String userId) throws Exception {
        return iRoleDao.findOtherRole(userId);
    }

    @Override
    public Role findById(String roleId) throws Exception {
        return iRoleDao.findById(roleId);
    }

    @Override
    public Role findByRoleId(String roleId) throws Exception {
        return iRoleDao.findByRoleId(roleId);
    }

    @Override
    public void addRoleToUser(String roleId, String[] permissionIds) {
        for (String s : permissionIds) {
            iRoleDao.addRoleToUser(roleId,s);
        }
    }


    @Override
    public List<Role> findAll()throws Exception {
        return iRoleDao.findAll();
    }
}
