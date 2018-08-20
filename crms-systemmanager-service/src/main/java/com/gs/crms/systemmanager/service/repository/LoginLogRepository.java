package com.gs.crms.systemmanager.service.repository;

import com.gs.crms.systemmanager.service.entity.LoginLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhangqiang on 2017/11/2.
 * 登陆登出日志Repository
 */
public interface LoginLogRepository extends JpaRepository<LoginLogEntity, Long> {
    /**
     * 获取日志实体
     * @param id
     * @return
     */
    LoginLogEntity findById(String id);

}
