package com.crossdorpoelem.site.operations

import com.crossdorpoelem.site.SiteTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SiteTest
class HealthSpec extends Specification {

    @Autowired
    private WebApplicationContext context

    protected MockMvc mvc

    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .build()
    }

    def "As an unauthenticated user, I want to retrieve basic health info from the actuator endpoint"() {
        given: "the /health actuator endpoint"
        when: "an unauthorized call is made"
        def result = mvc.perform(get("/actuator/health"))
        then: "the response contains status 'UP' state"
        result.andExpect(jsonPath("\$.status").exists())
        result.andExpect(jsonPath("\$.details").doesNotExist())
    }

}