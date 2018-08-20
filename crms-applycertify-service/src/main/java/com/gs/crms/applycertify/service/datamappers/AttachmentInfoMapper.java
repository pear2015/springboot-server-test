package com.gs.crms.applycertify.service.datamappers;


import com.gs.crms.common.enums.AttachmentEntity;
import com.gs.crms.common.model.AttachmentInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhangqiang on 2017/8/25.
 */
@Mapper(componentModel = "spring")
public interface AttachmentInfoMapper {
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
            @Mapping(target = "attachmentTypeEntity",ignore = true),
            @Mapping(target = "createTime",ignore = true)
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
