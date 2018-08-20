package com.gs.crms.applycertify.service.datamappers;
import com.gs.crms.common.enums.SexEntity;
import com.gs.crms.common.model.SexInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhengyali on 2018/3/7.
 */
@Mapper(componentModel = "spring")
public interface SexMapper {
    /**
     * Entity to model app user info.
     *
     * @param sexEntity
     * @return
     */
    @Mappings({
    })
    SexInfo entityToModel(SexEntity sexEntity);

    /**
     * Model to entity app user entity.
     *
     * @param sexInfo
     * @return
     */
    @Mappings({
    })
    SexEntity modelToEntity(SexInfo sexInfo);

    /**
     * Entities to model list.
     *
     * @param sexEntities
     * @return
     */

    List<SexInfo> entitiestoModels(List<SexEntity> sexEntities);

    /**
     * Modelsto entities list.
     *
     * @param sexInfos
     * @return
     */
    List<SexEntity> modelstoEntities(List<SexInfo> sexInfos);
}
