package com.gs.crms.applycertify.service.datamappers;

import com.gs.crms.applycertify.contract.model.ApplyAndCriminalRelation;
import com.gs.crms.applycertify.service.entity.ApplyAndCriminalRelationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhangqiang on 2017/8/22.
 */
@Mapper(componentModel = "spring")
public interface ApplyAndNoticeRelationMapper {

    /**
     * Entity to model app user info.
     * @param applyAndCriminalRelationEntity
     * @return
     */
    @Mappings({

    })
    ApplyAndCriminalRelation entityToModel(ApplyAndCriminalRelationEntity applyAndCriminalRelationEntity);

    /**
     * Model to entity app user entity.
     * @param applyAndCriminalRelation
     * @return
     */
    @Mappings({
            @Mapping(target = "relationId",ignore = true),
            @Mapping(target = "applyInfoEntity",ignore = true),
            @Mapping(target = "createTime",ignore = true)
    })
    ApplyAndCriminalRelationEntity modelToEntity(ApplyAndCriminalRelation applyAndCriminalRelation);

    /**
     * Entities to model list.
     * @param applyAndNoticeRelationEntities
     * @return
     */

    List<ApplyAndCriminalRelation> entitiestoModels(List<ApplyAndCriminalRelationEntity> applyAndNoticeRelationEntities);

    /**
     * Modelsto entities list.
     *
     * @param applyAndCriminalRelations the app user entity
     * @return the list
     */
    List<ApplyAndCriminalRelationEntity> modelstoEntities(List<ApplyAndCriminalRelation> applyAndCriminalRelations);
}
