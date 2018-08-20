package com.gs.crms.crimenotice.service.datamappers;

import com.gs.crms.crimenotice.contract.model.CrimeTypeInfo;
import com.gs.crms.crimenotice.service.entity.dictionary.CrimeTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhengyali on 2017/10/30.
 */
@Mapper(componentModel = "spring")
public interface CrimeTypeMapper {
    /**
     * Entity to model app user info.
     *
     * @param crimeTypeEntity the app user entity
     * @return the app user info
     */
    @Mappings({
            @Mapping(source = "id",target = "crimeTypeId"),
            @Mapping(source = "name",target = "crimeTypeName")
    })
    CrimeTypeInfo entityToModel(CrimeTypeEntity crimeTypeEntity);

    /**
     * Model to entity app user entity.
     *
     * @param crimeTypeInfo the app user info
     * @return the app user entity
     */
    @Mappings({
            @Mapping(source = "crimeTypeId",target = "id"),
            @Mapping(source = "crimeTypeName",target = "name")
    })
    CrimeTypeEntity modelToEntity(CrimeTypeInfo crimeTypeInfo);

    /**
     * Entitiesto models list.
     *
     * @param crimeEntities the app user entity
     * @return the list
     */
    List<CrimeTypeInfo> entitiestoModels(List<CrimeTypeEntity> crimeEntities);

    /**
     * Modelsto entities list.
     *
     * @param crimeInfos the app user entity
     * @return the list
     */
    List<CrimeTypeEntity> modelstoEntities(List<CrimeTypeInfo> crimeInfos);

}
