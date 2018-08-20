package com.gs.crms.crimenotice.service.datamappers;


import com.gs.crms.crimenotice.contract.model.CrimePersonInfo;
import com.gs.crms.crimenotice.service.entity.CrimePersonInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by tanjie on 2017/7/28.
 */
@Mapper(componentModel = "spring")
public interface CrimePersonMapper {
    /**
     * Entity to model app user info.
     *
     * @param crimePersonInfoEntity the app user entity
     * @return the app user info
     */
    @Mappings({
            @Mapping(source = "marriageEntity.name",target = "marriageName"),
            @Mapping(source = "sexEntity.name",target = "sexName"),
            @Mapping(source = "certificateTypeEntity.name",target = "certificateName"),
            @Mapping(source = "careerEntity.name",target = "careerName")
    })
    CrimePersonInfo entityToModel(CrimePersonInfoEntity crimePersonInfoEntity);

    /**
     * Model to entity app user entity.
     *
     * @param crimePersonInfo the app user info
     * @return the app user entity
     */
    @Mappings({
            @Mapping(target = "modifyTime", ignore = true),
            @Mapping(target = "modifyPersonName", ignore = true),
            @Mapping(target = "modifyPersonId", ignore = true),
            @Mapping(target = "deletePersonId", ignore = true),
            @Mapping(target = "deletePersonName", ignore = true),
            @Mapping(target = "deleteTime", ignore = true),
            @Mapping(target = "changeTime", ignore = true),
            @Mapping(target = "sexEntity", ignore = true),
            @Mapping(target = "marriageEntity", ignore = true),
            @Mapping(target = "certificateTypeEntity", ignore = true),
            @Mapping(target = "careerEntity", ignore = true)
    })
    CrimePersonInfoEntity modelToEntity(CrimePersonInfo crimePersonInfo);

    /**
     * Entitiesto models list.
     *
     * @param crimePersonInfoEntities the app user entity
     * @return the list
     */
    List<CrimePersonInfo> entitiestoModels(List<CrimePersonInfoEntity> crimePersonInfoEntities);

    /**
     * Modelsto entities list.
     *
     * @param crimePersonInfos the app user entity
     * @return the list
     */
    List<CrimePersonInfoEntity> modelstoEntities(List<CrimePersonInfo> crimePersonInfos);
}
