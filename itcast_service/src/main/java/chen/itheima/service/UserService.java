package chen.itheima.service;

import chen.itheima.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {
    List<UserInfo> findAll();

    void save(UserInfo userInfo);

    UserInfo findById(String id);
    void addRoleToUser(String userId,String[] roleId);
}
