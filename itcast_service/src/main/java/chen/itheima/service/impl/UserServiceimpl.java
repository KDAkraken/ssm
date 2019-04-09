package chen.itheima.service.impl;

import chen.itheima.dao.IUserDao;
import chen.itheima.domain.UserInfo;
import chen.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private IUserDao iUserDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public List<UserInfo> findAll() {
        return iUserDao.findAll();

    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        iUserDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        UserInfo user = iUserDao.findById(id);
        return user;
    }

    @Override
    public void addRoleToUser(String userId, String[] roleId) {
        for (String s : roleId) {
            iUserDao.addRoleToUser(userId,s);
        }

    }
}
