package chen.itheima.service.impl;

import chen.itheima.dao.IPermissionDao;
import chen.itheima.domain.Permission;
import chen.itheima.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class IPermissionServiceimpl implements IPermissionService{
    @Autowired
    private IPermissionDao iPermissionDao;
    @Override
    public List<Permission> findAll() throws Exception {
        return iPermissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        iPermissionDao.save(permission);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) throws Exception {
        return iPermissionDao.findOtherPermission(roleId);
    }
}
