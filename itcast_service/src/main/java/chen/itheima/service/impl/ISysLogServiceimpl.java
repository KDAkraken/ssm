package chen.itheima.service.impl;

import chen.itheima.dao.ISysLogDao;
import chen.itheima.domain.SysLog;
import chen.itheima.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Service
public class ISysLogServiceimpl implements ISysLogService {
    @Autowired
    private ISysLogDao iSysLogDao;

    @Override
    public void save(SysLog log) throws Exception {
        iSysLogDao.save(log);
    }

    @Override
    public List<SysLog> findAll() throws Exception {
        return iSysLogDao.findAll();
    }
}
