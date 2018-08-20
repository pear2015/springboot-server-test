package com.gs.crms.crimenotice.service.datamappers;
import com.gs.crms.crimenotice.contract.model.NoticeInfo;
import com.gs.crms.crimenotice.contract.model.NoticeReturnDataModel;
import com.gs.crms.crimenotice.service.entity.NoticeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


/**
 * Created by zhengyali on 2018/3/14.
 */
@Mapper(componentModel = "spring")
public interface NoticeReturnDataModelMapper {
    /**
     * Entity to model app user info.
     *
     * @param noticeEntity the app user entity
     * @return the app user info
     */
    @Mappings({
            @Mapping(source = "noticeInputStatusEntity.name", target = "noticeInputStatusName"),
            @Mapping(source = "noticeStatusEntity.name", target = "statusName"),
            @Mapping(source = "auditResultEntity.auditResultName", target = "auditResultName")
    })
    NoticeReturnDataModel entityToModel(NoticeEntity noticeEntity);
    /**
     * Model to entity app user entity.
     *
     * @param noticeInfo the app user info
     * @return the app user entity
     */
    @Mappings({
    })
    NoticeReturnDataModel modelToModel(NoticeInfo noticeInfo);
}
