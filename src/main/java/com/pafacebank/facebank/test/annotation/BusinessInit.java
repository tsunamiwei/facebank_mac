package com.pafacebank.facebank.test.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@RequestMapping(value = "/init",
        method = {RequestMethod.GET
}
)
public @interface BusinessInit {
}

