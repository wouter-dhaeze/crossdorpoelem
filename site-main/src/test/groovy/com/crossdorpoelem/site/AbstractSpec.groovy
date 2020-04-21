package com.crossdorpoelem.site

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

abstract class AbstractSpec extends Specification {

    @Autowired
    private WebApplicationContext context

    protected MockMvc mvc

    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build()
    }

}
