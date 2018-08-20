package com.gs.crms.crimenotice.service.datamappers;


import com.gs.crms.common.model.AttachmentInfo;
import com.gs.crms.common.enums.AttachmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by tanjie on 2017/7/28.
 */
@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    /**
     * Entity to model app user info.
     *
     * @param attachmentEntity the app user entity
     * @return the app user info
     */
    AttachmentInfo entityToModel(AttachmentEntity attachmentEntity);

    /**
     * Model to entity app user entity.
     *
     * @param attachmentInfo the app user info
     * @return the app user entity
     */

    @Mappings({
            @Mapping(target = "attachmentTypeEntity", ignore = true),
            @Mapping(target = "createTime", ignore = true)
    })
    AttachmentEntity modelToEntity(AttachmentInfo attachmentInfo);

    /**
     * Entitiesto models list.
     *
     * @param attachmentEntities the app user entity
     * @return the list
     */
    List<AttachmentInfo> entitiestoModels(List<AttachmentEntity> attachmentEntities);

    /**
     * Modelsto entities list.
     *
     * @param attachmentInfos the app user entity
     * @return the list
     */
    List<AttachmentEntity> modelstoEntities(List<AttachmentInfo> attachmentInfos);
}
