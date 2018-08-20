package com.gs.crms.applycertify.service.datamappers;

import com.gs.crms.applycertify.contract.model.CertificateType;
import com.gs.crms.common.enums.CertificateTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhangqiang on 2017/9/25.
 * 证件类型Mapper
 */
@Mapper(componentModel = "spring")
public interface CertificateTypeMapper {

    /**
     * Entity to model app user info.
     *
     * @param certificateTypeEntity
     * @return
     */
    @Mappings({

    })
    CertificateType entityToModel(CertificateTypeEntity certificateTypeEntity);

    /**
     * Model to entity app user entity.
     *
     * @param certificateType
     * @return
     */
    @Mappings({
            @Mapping(target = "personIsValid",ignore = true),
            @Mapping(target = "governmentIsValid",ignore = true),
            @Mapping(target = "noticeIsValid",ignore = true)
    })
    CertificateTypeEntity modelToEntity(CertificateType certificateType);

    /**
     * Entitiesto models list.
     *
     * @param certificateTypeEntities the app user entity
     * @return the list
     */
    List<CertificateType> entitiestoModels(List<CertificateTypeEntity> certificateTypeEntities);

    /**
     * Modelsto entities list.
     *
     * @param certificateTypes the app user entity
     * @return the list
     */
    List<CertificateTypeEntity> modelstoEntities(List<CertificateType> certificateTypes);

}
