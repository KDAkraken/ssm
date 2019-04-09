package chen.itheima.service;

import chen.itheima.domain.Permission;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IPermissionService {
    public List<Permission> findAll()throws  Exception;
    public void save(Permission permission)throws Exception;
    public List<Permission>findOtherPermission(String roleId) throws Exception;


}
