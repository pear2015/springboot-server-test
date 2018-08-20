package com.gs.crms.crimenotice.service.datamappers;


import com.gs.crms.crimenotice.contract.model.NoticeInfo;
import com.gs.crms.crimenotice.service.entity.NoticeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by tanjie on 2017/7/28.
 */
@Mapper(componentModel = "spring")
public interface NoticeMapper {
    /**
     * Entity to model app user info.
     *
     * @param noticeEntity the app user entity
     * @return the app user info
     */
    @Mappings({
            @Mapping(source = "crimePersonInfoEntity.firstName", target = "firstName"),
            @Mapping(source = "crimePersonInfoEntity.lastName", target = "lastName"),
            @Mapping(source = "courtEntity.name", target = "courtName"),
            @Mapping(source = "auditResultEntity.auditResultId", target = "auditResultId"),
            @Mapping(source = "noticePriorityEntity.name", target = "priorityName"),
            @Mapping(source = "noticeInputStatusEntity.name", target = "noticeInputStatusName"),
            @Mapping(source = "noticeStatusEntity.name", target = "statusName"),
            @Mapping(source = "auditResultEntity.auditResultName", target = "auditResultName"),
            @Mapping(source = "crimePersonInfoEntity.certificateNumber", target = "certificateNumber"),
            @Mapping(target = "apportionTime", ignore = true)
    })
    NoticeInfo entityToModel(NoticeEntity noticeEntity);

    /**
     * Model to entity app user entity.
     *
     * @param noticeInfo the app user info
     * @return the app user entity
     */
    @Mappings({
            @Mapping(target = "modifyTime", ignore = true),
            @Mapping(target = "deleteTime", ignore = true),
            @Mapping(target = "changeTime", ignore = true),
            @Mapping(target = "crimePersonInfoEntity", ignore = true),
            @Mapping(target = "crimeEntity", ignore = true),
            @Mapping(target = "courtEntity", ignore = true),
            @Mapping(target = "noticeStatusEntity", ignore = true),
            @Mapping(target = "noticeInputStatusEntity", ignore = true),
            @Mapping(target = "auditResultEntity", ignore = true),
            @Mapping(target = "noticePriorityEntity", ignore = true)
    })
    NoticeEntity modelToEntity(NoticeInfo noticeInfo);

    /**
     * Entitiesto models list.
     *
     * @param noticeEntities the app user entity
     * @return the list
     */
    List<NoticeInfo> entitiestoModels(List<NoticeEntity> noticeEntities);

    /**
     * Modelsto entities list.
     *
     * @param noticeInfos the app user entity
     * @return the list
     */
    List<NoticeEntity> modelstoEntities(List<NoticeInfo> noticeInfos);
}
