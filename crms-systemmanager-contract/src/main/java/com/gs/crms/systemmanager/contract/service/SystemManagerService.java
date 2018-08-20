package com.gs.crms.systemmanager.contract.service;

import com.gs.crms.systemmanager.contract.model.LoginLog;

/**
 * Created by zhangqiang on 2017/11/2.
 */
public interface SystemManagerService {
    /**
     * 记录登陆日志
     *
     * @param loginLog
     * @return
     */
    String loginInsert(LoginLog loginLog);

    /**
     * 记录登出日志
     *
     * @param loginLog
     * @return
     */
    boolean loginOutUpdate(LoginLog loginLog);

}
