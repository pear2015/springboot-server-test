package com.gs.crms.systemmanager.service.datamappers;

import com.gs.crms.systemmanager.contract.model.LoginLog;
import com.gs.crms.systemmanager.service.entity.LoginLogEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Created by zhangqiang on 2017/11/2.
 */
@Mapper(componentModel = "spring")
public interface LoginLogMapper {
    /**
     * Entity to model app user info.
     *
     * @param loginLogEntity the app user entity
     * @return the app user info
     */
    LoginLog entityToModel(LoginLogEntity loginLogEntity);

    /**
     * Model to entity app user entity.
     *
     * @param loginLog the app user info
     * @return the app user entity
     */
    LoginLogEntity modelToEntity(LoginLog loginLog);

    /**
     * Entitiesto models list.
     *
     * @param loginLogEntities the app user entity
     * @return the list
     */
    List<LoginLog> entitiestoModels(List<LoginLogEntity> loginLogEntities);

    /**
     * Modelsto entities list.
     *
     * @param loginLogs the app user entity
     * @return the list
     */
    List<LoginLogEntity> modelstoEntities(List<LoginLog> loginLogs);
}
