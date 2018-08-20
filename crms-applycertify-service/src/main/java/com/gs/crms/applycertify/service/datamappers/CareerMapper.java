package com.gs.crms.applycertify.service.datamappers;
import com.gs.crms.common.enums.CareerEntity;
import com.gs.crms.common.model.CareerInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Created by zhengyali on 2018/3/6.
 */
@Mapper(componentModel = "spring")
public interface CareerMapper {
    /**
     * Entity to model app user info.
     *
     * @param careerEntity
     * @return
     */
    @Mappings({
    })
    CareerInfo entityToModel(CareerEntity careerEntity);

    /**
     * Model to entity app user entity.
     *
     * @param careerInfo
     * @return
     */
    @Mappings({
    })
    CareerEntity modelToEntity(CareerInfo careerInfo);

    /**
     * Entities to model list.
     *
     * @param careerEntities
     * @return
     */

    List<CareerInfo> entitiestoModels(List<CareerEntity> careerEntities);

    /**
     * Modelsto entities list.
     *
     * @param careerInfos
     * @return
     */
    List<CareerEntity> modelstoEntities(List<CareerInfo> careerInfos);
}
