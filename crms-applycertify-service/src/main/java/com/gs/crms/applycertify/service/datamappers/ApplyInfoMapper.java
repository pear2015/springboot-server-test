package com.gs.crms.applycertify.service.datamappers;

import com.gs.crms.applycertify.contract.model.ApplyInfo;
import com.gs.crms.applycertify.service.entity.ApplyInfoEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhangqiang on 2017/8/22.
 */
@Mapper(componentModel = "spring")
public interface ApplyInfoMapper {

    /**
     * Entity to model app user info.
     *
     * @param applyInfoEntity
     * @return
     */
    @Mappings({
            @Mapping(source = "applyInfoEntity.applyPurposeEntity.name", target = "applyPurposeName"),
            @Mapping(source = "applyInfoEntity.applyStatusEntity.name", target = "applyStatusName"),
            @Mapping(source = "applyInfoEntity.applyTypeEntity.name", target = "applyTypeName"),
            @Mapping(source = "applyInfoEntity.analysisResultEntity.analysisResultName", target = "analysisResultName"),
            @Mapping(source = "applyInfoEntity.auditResultEntity.auditResultName", target = "auditResultName"),
            @Mapping(source = "applyInfoEntity.applyResultEntity.name", target = "applyResultName"),
            @Mapping(source = "applyInfoEntity.applyPriorityEntity.name", target = "priorityName"),
            @Mapping(source = "applyInfoEntity.certificateTypeEntity.name", target = "agentIdTypeName"),
            @Mapping(source = "applyInfoEntity.applyBasicInfoEntity.certificateNumber", target = "certificateNumber"),
            @Mapping(source = "applyInfoEntity.applyBasicInfoEntity.lastName", target = "lastName"),
            @Mapping(source = "applyInfoEntity.applyBasicInfoEntity.certificateType", target = "certificateType"),
            @Mapping(source = "applyInfoEntity.applyBasicInfoEntity.firstName", target = "firstName"),
            @Mapping(source = "applyInfoEntity.applyBasicInfoEntity.certificateTypeEntity.name", target = "certificateName"),
            @Mapping(target = "credentialsPrintTime",ignore = true),
            @Mapping(target = "apportionTime", ignore = true)
    })
    ApplyInfo entityToModel(ApplyInfoEntity applyInfoEntity);

    /**
     * Model to entity app user entity.
     *
     * @param applyInfo
     * @return
     */
    @Mappings({
            @Mapping( target = "applyTypeEntity",ignore = true),
            @Mapping( target = "applyStatusEntity",ignore = true),
            @Mapping( target = "applyPurposeEntity",ignore = true),
            @Mapping( target = "applyBasicInfoEntity",ignore = true),
            @Mapping( target = "analysisResultEntity",ignore = true),
            @Mapping( target = "auditResultEntity",ignore = true),
            @Mapping( target = "certificateTypeEntity",ignore = true),
            @Mapping( target = "applyPriorityEntity",ignore = true),
            @Mapping( target = "applyResultEntity",ignore = true)
    })
    ApplyInfoEntity modelToEntity(ApplyInfo applyInfo);

    /**
     * Entities to model list.
     *
     * @param applyInfoEntities
     * @return
     */

    List<ApplyInfo> entitiestoModels(List<ApplyInfoEntity> applyInfoEntities);

    /**
     * Modelsto entities list.
     *
     * @param applyInfos the app user entity
     * @return the list
     */
    List<ApplyInfoEntity> modelstoEntities(List<ApplyInfo> applyInfos);

}
