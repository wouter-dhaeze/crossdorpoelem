package com.crossdorpoelem.site.operations

import com.crossdorpoelem.site.AbstractMvcSpec
import com.crossdorpoelem.site.SiteTest

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@SiteTest
class HealthSpec extends AbstractMvcSpec {

    def "As an unauthenticated user, I want to retrieve basic health info from the actuator endpoint"() {
        given: "the /health actuator endpoint"
        when: "an unauthorized call is made"
        def result = mvc.perform(get("/actuator/health"))
        then: "the response contains status 'UP' state"
        result.andExpect(jsonPath("\$.status").exists())
        result.andExpect(jsonPath("\$.details").doesNotExist())
    }

}