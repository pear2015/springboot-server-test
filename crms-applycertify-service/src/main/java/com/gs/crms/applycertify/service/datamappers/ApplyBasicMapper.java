package com.gs.crms.applycertify.service.datamappers;

import com.gs.crms.applycertify.contract.model.ApplyBasicInfo;
import com.gs.crms.applycertify.service.entity.ApplyBasicInfoEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhangqiang on 2017/8/22.
 */
@Mapper(componentModel = "spring")
public interface ApplyBasicMapper {

    /**
     * Entity to model app user info.
      * @param applyBasicInfoEntity
     * @return
     */
    @Mappings({
            @Mapping(source = "marriageEntity.name",target = "marriageName"),
            @Mapping(source = "sexEntity.name",target = "sexName"),
            @Mapping(source = "certificateTypeEntity.name",target = "certificateName"),
            @Mapping(source = "livingCountryName",target = "livingCountryName"),
            @Mapping(source = "careerEntity.name",target = "careerName"),
            @Mapping(target = "countryName",ignore = true)

    })
    ApplyBasicInfo entityToModel(ApplyBasicInfoEntity applyBasicInfoEntity);

    /**
     * Model to entity app user entity.
     * @param applyBasicInfo
     * @return
     */
    @Mappings({
            @Mapping(source = "livingCountryName",target = "livingCountryName"),
            @Mapping(target = "sexEntity",ignore = true),
            @Mapping(target = "marriageEntity",ignore = true),
            @Mapping(target = "certificateTypeEntity",ignore = true),
            @Mapping(target = "careerEntity",ignore = true),
            @Mapping(target = "createTime",ignore = true),
            @Mapping(target = "changeTime",ignore = true)
    })
    ApplyBasicInfoEntity modelToEntity(ApplyBasicInfo applyBasicInfo);

    /**
     * Entities to model list.
     * @param applyBasicInfoEntities
     * @return
     */

    List<ApplyBasicInfo> entitiestoModels(List<ApplyBasicInfoEntity> applyBasicInfoEntities);

    /**
     * Modelsto entities list.
     *
     * @param applyBasicInfos the app user entity
     * @return the list
     */
    List<ApplyBasicInfoEntity> modelstoEntities(List<ApplyBasicInfo> applyBasicInfos);
}
