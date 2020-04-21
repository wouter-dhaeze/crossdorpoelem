package com.crossdorpoelem.site;

import java.lang.annotation.*;

import org.springframework.boot.test.context.SpringBootTest;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

@SpringBootTest(classes = SiteApplication.class)
public @interface SiteTest {
}
