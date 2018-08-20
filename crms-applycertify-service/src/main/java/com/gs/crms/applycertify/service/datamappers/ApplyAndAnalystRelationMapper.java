package com.gs.crms.applycertify.service.datamappers;

import com.gs.crms.applycertify.contract.model.ApplyAndAnalystRelation;
import com.gs.crms.applycertify.service.entity.ApplyAndAnalystRelationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 分析人物和分析员用户关联Mapper
 * Created by zhangqiang on 2017/8/31.
 */
@Mapper(componentModel = "spring")
public interface ApplyAndAnalystRelationMapper {

    /**
     * Entity to model app user info.
     *
     * @param applyAndAnalystRelationEntity
     * @return
     */
    @Mappings({

    })
    ApplyAndAnalystRelation entityToModel(ApplyAndAnalystRelationEntity applyAndAnalystRelationEntity);

    /**
     * Model to entity app user entity.
     *
     * @param applyAndAnalystRelation
     * @return
     */
    @Mappings({
            @Mapping(target = "relationId",ignore = true)
    })
    ApplyAndAnalystRelationEntity modelToEntity(ApplyAndAnalystRelation applyAndAnalystRelation);

    /**
     * Entities to model list.
     *
     * @param applyAndAnalystRelationEntities
     * @return
     */

    List<ApplyAndAnalystRelation> entitiestoModels(List<ApplyAndAnalystRelationEntity> applyAndAnalystRelationEntities);

    /**
     * Modelsto entities list.
     *
     * @param applyAndAnalystRelations the app user entity
     * @return the list
     */
    List<ApplyAndAnalystRelationEntity> modelstoEntities(List<ApplyAndAnalystRelation> applyAndAnalystRelations);
}
