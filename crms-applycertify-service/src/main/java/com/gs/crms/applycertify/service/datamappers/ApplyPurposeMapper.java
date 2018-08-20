package com.gs.crms.applycertify.service.datamappers;

import com.gs.crms.applycertify.contract.model.ApplyPurpose;
import com.gs.crms.applycertify.service.entity.dictionary.ApplyPurposeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhangqiang on 2017/8/23.
 * 申请目的
 */
@Mapper(componentModel = "spring")
public interface ApplyPurposeMapper {
    /**
     * Entity to model app user info.
     *
     * @param applyPurposeEntity
     * @return
     */
    @Mappings({

    })
    ApplyPurpose entityToModel(ApplyPurposeEntity applyPurposeEntity);

    /**
     * Model to entity app user entity.
     *
     * @param applyPurpose
     * @return
     */
    @Mappings({

    })
    ApplyPurposeEntity modelToEntity(ApplyPurpose applyPurpose);

    /**
     * Entitiesto models list.
     *
     * @param applyPurposeEntities the app user entity
     * @return the list
     */
    List<ApplyPurpose> entitiestoModels(List<ApplyPurposeEntity> applyPurposeEntities);

    /**
     * Modelsto entities list.
     *
     * @param applyPurposes the app user entity
     * @return the list
     */
    List<ApplyPurposeEntity> modelstoEntities(List<ApplyPurpose> applyPurposes);
}
