package com.gs.crms.applycertify.service.datamappers;

import com.gs.crms.applycertify.contract.model.WaitApportion;
import com.gs.crms.applycertify.service.entity.WaitApportionEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhangqiang on 2017/8/22.
 */
@Mapper(componentModel = "spring")
public interface WaitApportionMapper {
    /**
     * Entity to model app user info.
     *
     * @param waitApportionEntity
     * @return
     */
    @Mappings({

    })
    WaitApportion entityToModel(WaitApportionEntity waitApportionEntity);

    /**
     * Model to entity app user entity.
     *
     * @param waitApportion
     * @return
     */
    @Mappings({

    })
    WaitApportionEntity modelToEntity(WaitApportion waitApportion);

    /**
     * Entitiesto models list.
     *
     * @param waitApportionEntityList the app user entity
     * @return the list
     */
    List<WaitApportion> entitiestoModels(List<WaitApportionEntity> waitApportionEntityList);

    /**
     * Modelsto entities list.
     *
     * @param waitApportionList the app user entity
     * @return the list
     */
    List<WaitApportionEntity> modelstoEntities(List<WaitApportion> waitApportionList);
}
