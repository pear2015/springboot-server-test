package com.gs.crms.applycertify.service.datamappers;


import com.gs.crms.applycertify.contract.model.CertificateInfo;
import com.gs.crms.applycertify.service.entity.CertificateInfoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhufengjie on 2017/9/28.
 */
@Mapper(componentModel = "spring")
public interface CertificateInfoMapper {

    /**
     * Entity to model app user info.
     *
     * @param certificateInfoEntity
     * @return
     */
    @Mappings({
            @Mapping(target = "valid", ignore = true)
    })
    CertificateInfo entityToModel(CertificateInfoEntity certificateInfoEntity);

    /**
     * Model to entity app user entity.
     *
     * @param certificateInfo
     * @return
     */
    @Mappings({
            @Mapping(target = "applyInfoEntity", ignore = true),
            @Mapping(target = "applyTypeEntity", ignore = true),
            @Mapping(target = "createPersonId", ignore = true),
            @Mapping(target = "createTime", ignore = true),
            @Mapping(target = "changeTime", ignore = true)
    })
    CertificateInfoEntity modelToEntity(CertificateInfo certificateInfo);

    /**
     * Entities to model list.
     *
     * @param certificateInfoEntities
     * @return
     */

    List<CertificateInfo> entitiestoModels(List<CertificateInfoEntity> certificateInfoEntities);

    /**
     * Modelsto entities list.
     *
     * @param certificateInfos
     * @return
     */
    List<CertificateInfoEntity> modelstoEntities(List<CertificateInfo> certificateInfos);

}
