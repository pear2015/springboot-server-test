package com.gs.crms.applycertify.service.datamappers;
import com.gs.crms.common.enums.MarriageEntity;
import com.gs.crms.common.model.MarriageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhengyali on 2018/3/7.
 */
@Mapper(componentModel = "spring")
public interface MarriageMapper {
    /**
     * Entity to model app user info.
     *
     * @param marriageEntity
     * @return
     */
    @Mappings({
    })
    MarriageInfo entityToModel(MarriageEntity marriageEntity);

    /**
     * Model to entity app user entity.
     *
     * @param marriageInfo
     * @return
     */
    @Mappings({
    })
    MarriageEntity modelToEntity(MarriageInfo marriageInfo);

    /**
     * Entities to model list.
     *
     * @param marriageEntities
     * @return
     */

    List<MarriageInfo> entitiestoModels(List<MarriageEntity> marriageEntities);

    /**
     * Modelsto entities list.
     *
     * @param marriageInfos
     * @return
     */
    List<MarriageEntity> modelstoEntities(List<MarriageInfo> marriageInfos);
}
