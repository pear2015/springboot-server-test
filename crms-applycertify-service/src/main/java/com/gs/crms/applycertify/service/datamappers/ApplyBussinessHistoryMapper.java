package com.gs.crms.applycertify.service.datamappers;

import com.gs.crms.applycertify.contract.model.ApplyBussinessHistory;
import com.gs.crms.applycertify.service.entity.history.ApplyBussinessHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhangqiang on 2017/9/25.
 */
@Mapper(componentModel = "spring")
public interface ApplyBussinessHistoryMapper {
    /**
     * Entity to model app user info.
     *
     * @param applyBussinessHistoryEntity
     * @return
     */
    @Mappings({
            @Mapping(source = "applyBussinessHistoryEntity.userOperatorTypeEntity.name", target = "operatorTypeName"),
    })
    ApplyBussinessHistory entityToModel(ApplyBussinessHistoryEntity applyBussinessHistoryEntity);

    /**
     * Model to entity app user entity.
     *
     * @param applyBussinessHistory
     * @return
     */
    @Mappings({
            @Mapping(target = "userOperatorTypeEntity",ignore = true)
    })
    ApplyBussinessHistoryEntity modelToEntity(ApplyBussinessHistory applyBussinessHistory);

    /**
     * Entitiesto models list.
     *
     * @param applyBussinessHistoryEntities the app user entity
     * @return the list
     */
    List<ApplyBussinessHistory> entitiestoModels(List<ApplyBussinessHistoryEntity> applyBussinessHistoryEntities);

    /**
     * Modelsto entities list.
     *
     * @param applyBussinessHistories the app user entity
     * @return the list
     */
    List<ApplyBussinessHistoryEntity> modelstoEntities(List<ApplyBussinessHistory> applyBussinessHistories);
}
