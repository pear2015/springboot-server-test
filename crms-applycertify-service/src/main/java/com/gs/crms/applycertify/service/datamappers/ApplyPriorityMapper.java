package com.gs.crms.applycertify.service.datamappers;

import com.gs.crms.applycertify.contract.model.ApplyPriority;
import com.gs.crms.applycertify.service.entity.dictionary.ApplyPriorityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhangqiang on 2017/10/12.
 */
@Mapper(componentModel = "spring")
public interface ApplyPriorityMapper {
    /**
     * Entity to model app user info.
     *
     * @param applyPriorityEntity
     * @return
     */
    @Mappings({

    })
    ApplyPriority entityToModel(ApplyPriorityEntity applyPriorityEntity);

    /**
     * Model to entity app user entity.
     *
     * @param applyPriority
     * @return
     */
    @Mappings({
            @Mapping(target = "description",ignore = true)
    })
    ApplyPriorityEntity modelToEntity(ApplyPriority applyPriority);

    /**
     * Entitiesto models list.
     *
     * @param applyPriorityEntities the app user entity
     * @return the list
     */
    List<ApplyPriority> entitiestoModels(List<ApplyPriorityEntity> applyPriorityEntities);

    /**
     * Modelsto entities list.
     *
     * @param applyPriorities the app user entity
     * @return the list
     */
    List<ApplyPriorityEntity> modelstoEntities(List<ApplyPriority> applyPriorities);
}
