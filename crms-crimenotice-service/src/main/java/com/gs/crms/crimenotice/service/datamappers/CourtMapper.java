package com.gs.crms.crimenotice.service.datamappers;

import com.gs.crms.crimenotice.contract.model.CourtInfo;
import com.gs.crms.crimenotice.service.entity.dictionary.CourtEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by tanjie on 2017/8/10.
 */
@Mapper(componentModel = "spring")
public interface CourtMapper {
    /**
     * Entity to model app user info.
     *
     * @param courtEntity the app user entity
     * @return the app user info
     */
    CourtInfo entityToModel(CourtEntity courtEntity);

    /**
     * Model to entity app user entity.
     *
     * @param courtInfo the app user info
     * @return the app user entity
     */
    @Mappings({
            @Mapping(target = "courtAddress",ignore = true),
            @Mapping(target = "createTime",ignore = true),
            @Mapping(target = "createPersonName",ignore = true),
            @Mapping(target = "modifyTime",ignore = true),
            @Mapping(target = "modifyPersonName",ignore = true),
            @Mapping(target = "description",ignore = true),
            @Mapping(target = "provinceId",ignore = true),
            @Mapping(target = "cityId",ignore = true),
            @Mapping(target = "communityId",ignore = true)
    })
    CourtEntity modelToEntity(CourtInfo courtInfo);

    /**
     * Entitiesto models list.
     *
     * @param courtEntities the app user entity
     * @return the list
     */
    List<CourtInfo> entitiestoModels(List<CourtEntity> courtEntities);

    /**
     * Modelsto entities list.
     *
     * @param courtInfos the app user entity
     * @return the list
     */
    List<CourtEntity> modelstoEntities(List<CourtInfo> courtInfos);
}
