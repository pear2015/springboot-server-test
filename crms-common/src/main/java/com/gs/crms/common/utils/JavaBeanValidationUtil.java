package com.gs.crms.common.utils;

import org.springframework.util.CollectionUtils;

import javax.validation.*;
import java.util.Set;

/**
 * Created by zhangqiang on 2017/12/10.
 * JavaBean验证工具类
 */
public class JavaBeanValidationUtil {
    private static Validator validator;
    private JavaBeanValidationUtil() {

    }
    static {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * 校验具体的JavaBean
     * 只返回校验结果
     *
     * @param t
     * @param <T>
     * @return 校验是否通过
     */
    public static <T> boolean validate(T t) {
        Set<ConstraintViolation<T>> set = validator.validate(t);
        return CollectionUtils.isEmpty(set);
    }
}
