package chen.itheima.service;

import chen.itheima.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    public void save(SysLog log) throws Exception;
    public List<SysLog> findAll() throws Exception;
}
