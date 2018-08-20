package com.gs.crms.crimenotice.service.datamappers;


import com.gs.crms.crimenotice.contract.model.CrimeInfo;
import com.gs.crms.crimenotice.service.entity.CrimeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by tanjie on 2017/7/25.
 * 犯罪Entity和DTO相互转换
 */
@Mapper(componentModel = "spring")
public interface CrimeMapper {

    /**
     * Entity to model app user info.
     *
     * @param crimeEntity the app user entity
     * @return the app user info
     */
    @Mappings({
            @Mapping(source = "crimeTypeEntity.id",target = "crimeTypeId"),
            @Mapping(source = "crimeTypeEntity.name",target = "crimeTypeName")
    })
    CrimeInfo entityToModel(CrimeEntity crimeEntity);

    /**
     * Model to entity app user entity.
     *
     * @param crimeInfo the app user info
     * @return the app user entity
     */
    @Mappings({
            @Mapping(source = "crimeTypeId",target = "crimeType"),
            @Mapping(target = "modifyTime",ignore = true),
            @Mapping(target = "modifyPersonName",ignore = true),
            @Mapping(target = "modifyPersonId",ignore = true),
            @Mapping(target = "deletePersonId",ignore = true),
            @Mapping(target = "deleteTime",ignore = true),
            @Mapping(target = "changeTime",ignore = true),
            @Mapping(target = "crimeTypeEntity",ignore = true),
            @Mapping(target = "deletePersonName",ignore = true)
    })
    CrimeEntity modelToEntity(CrimeInfo crimeInfo);

    /**
     * Entitiesto models list.
     *
     * @param crimeEntities the app user entity
     * @return the list
     */
    List<CrimeInfo> entitiestoModels(List<CrimeEntity> crimeEntities);

    /**
     * Modelsto entities list.
     *
     * @param crimeInfos the app user entity
     * @return the list
     */
    List<CrimeEntity> modelstoEntities(List<CrimeInfo> crimeInfos);
}
