package com.pafacebank.facebank.test.annotation;

import com.pafacebank.facebank.test.datadict.BusinessTypeEnum;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@RestController
@RequestMapping
public @interface BusinessProcess {
    BusinessTypeEnum businessType();

    @AliasFor(annotation = RequestMapping.class)
    String path();
}

