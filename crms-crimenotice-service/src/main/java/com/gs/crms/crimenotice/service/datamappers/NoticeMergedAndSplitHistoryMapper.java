package com.gs.crms.crimenotice.service.datamappers;

import com.gs.crms.crimenotice.contract.model.NoticeMergedAndSplitHistory;
import com.gs.crms.crimenotice.service.entity.NoticeMergedAndSplitHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhangqiang on 2017/11/12.
 */
@Mapper(componentModel = "spring")
public interface NoticeMergedAndSplitHistoryMapper {
    /**
     * Entity to model app user info.
     *
     * @param noticeMergedAndSplitHistoryEntity the app user entity
     * @return the app user info
     */
    @Mappings({
    })
    NoticeMergedAndSplitHistory entityToModel(NoticeMergedAndSplitHistoryEntity noticeMergedAndSplitHistoryEntity);

    /**
     * Model to entity app user entity.
     *
     * @param noticeMergedAndSplitHistory the app user info
     * @return the app user entity
     */
    @Mappings({
    })
    NoticeMergedAndSplitHistoryEntity modelToEntity(NoticeMergedAndSplitHistory noticeMergedAndSplitHistory);

    /**
     * Entitiesto models list.
     *
     * @param noticeMergedAndSplitHistoryEntityList the app user entity
     * @return the list
     */
    List<NoticeMergedAndSplitHistory> entitiestoModels(List<NoticeMergedAndSplitHistoryEntity> noticeMergedAndSplitHistoryEntityList);

    /**
     * Modelsto entities list.
     *
     * @param noticeMergedAndSplitHistoryList the app user entity
     * @return the list
     */
    List<NoticeMergedAndSplitHistoryEntity> modelstoEntities(List<NoticeMergedAndSplitHistory> noticeMergedAndSplitHistoryList);
}

