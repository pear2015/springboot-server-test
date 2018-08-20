package com.gs.crms.applycertify.service.datamappers;
import com.gs.crms.applycertify.contract.model.ApplyReturnDataModel;
import com.gs.crms.applycertify.service.entity.ApplyInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * Created by zhengyali on 2018/3/14.
 */
@Mapper(componentModel = "spring")
@FunctionalInterface
public interface ApplyReturnDataModelMapper {
    /**
     * Entity to model app user info.
     *
     * @param applyInfoEntity
     * @return
     */
    @Mappings({

            @Mapping(source = "applyInfoEntity.applyStatusEntity.name", target = "applyStatusName"),
            @Mapping(source = "applyInfoEntity.analysisResultEntity.analysisResultName", target = "analysisResultName"),
            @Mapping(source = "applyInfoEntity.auditResultEntity.auditResultName", target = "auditResultName"),
            @Mapping(source = "applyInfoEntity.applyResultEntity.name", target = "applyResultName")
    })
    ApplyReturnDataModel entityToModel(ApplyInfoEntity applyInfoEntity);
}
