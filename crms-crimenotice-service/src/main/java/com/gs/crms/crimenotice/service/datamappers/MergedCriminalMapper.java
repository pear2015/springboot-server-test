package com.gs.crms.crimenotice.service.datamappers;

import com.gs.crms.crimenotice.contract.model.MergedCriminal;
import com.gs.crms.crimenotice.service.entity.MergedCriminalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhangqiang on 2017/11/12.
 */
@Mapper(componentModel = "spring")
public interface MergedCriminalMapper {
    /**
     * Entity to model app user info.
     *
     * @param mergedCriminalEntity the app user entity
     * @return the app user info
     */
    MergedCriminal entityToModel(MergedCriminalEntity mergedCriminalEntity);

    /**
     * Model to entity app user entity.
     *
     * @param mergedCriminal the app user info
     * @return the app user entity
     */
    @Mappings({
            @Mapping(target = "createTime",ignore = true)
    })
    MergedCriminalEntity modelToEntity(MergedCriminal mergedCriminal);

    /**
     * Entitiesto models list.
     *
     * @param mergedCriminalEntities the app user entity
     * @return the list
     */
    List<MergedCriminal> entitiestoModels(List<MergedCriminalEntity> mergedCriminalEntities);

    /**
     * Modelsto entities list.
     *
     * @param mergedCriminals the app user entity
     * @return the list
     */
    List<MergedCriminalEntity> modelstoEntities(List<MergedCriminal> mergedCriminals);
}
