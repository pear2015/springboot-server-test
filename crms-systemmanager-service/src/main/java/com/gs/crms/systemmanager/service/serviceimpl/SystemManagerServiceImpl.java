package com.gs.crms.systemmanager.service.serviceimpl;

import com.gs.crms.systemmanager.contract.model.LoginLog;
import com.gs.crms.systemmanager.contract.service.SystemManagerService;
import com.gs.crms.systemmanager.service.datamappers.LoginLogMapper;
import com.gs.crms.systemmanager.service.entity.LoginLogEntity;
import com.gs.crms.systemmanager.service.repository.LoginLogRepository;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.UUID;

/**
 * Created by zhangqiang on 2017/11/2.
 */
@Service
public class SystemManagerServiceImpl implements SystemManagerService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Autowired
    private LoginLogRepository loginLogRepository;


    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 记录登陆日志
     *
     * @param loginLog
     * @return
     */
    @Override
    public String loginInsert(LoginLog loginLog) {
        String id = "";

        try {
            id = UUID.randomUUID().toString();
            LoginLogEntity loginLogEntity = loginLogMapper.modelToEntity(loginLog);
            loginLogEntity.setId(id);
            loginLogEntity.setLoginTime(new Date());
            loginLogRepository.save(loginLogEntity);
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception: Save Login Log Call Exception" + e);
            return "";
        }

        return id;
    }

    /**
     * 记录登出日志
     *
     * @param loginLog
     * @return
     */
    @Override
    public boolean loginOutUpdate(LoginLog loginLog) {
        try {
            if (StringUtils.isBlank(loginLog.getId())) {
                return false;
            }
            LoginLogEntity loginLogEntity = loginLogRepository.findById(loginLog.getId());

            if (loginLogEntity != null) {
                loginLogEntity.setLoginOutTime(new Date());
                loginLogEntity.setTimeSpan(calculationTimeSpan(loginLogEntity.getLoginTime(), new Date()));
                loginLogRepository.save(loginLogEntity);
            }
            return true;
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("Exception:Save Login Out Log Call Exception" + e);
            return false;
        }
    }

    /**
     * 计算两个时间差(分钟)
     *
     * @param to
     * @param from
     * @return
     */
    private int calculationTimeSpan(Date from, Date to) {
        int minutes = 0;
        if (from != null && to != null) {
            long start = from.getTime();
            long end = to.getTime();
            minutes = (int) Math.ceil((double) (end - start) / (1000 * 60));
            if (minutes < 0) {
                minutes = 0;
            }
        }
        return minutes;
    }
}
