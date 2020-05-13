package com.crossdorpoelem.site.index

import com.crossdorpoelem.site.AbstractMvcSpec
import com.crossdorpoelem.site.SiteTest

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

@SiteTest
class HomeSpec extends AbstractMvcSpec {

    def "As a visitor I want to open the index page"() {
        given: "An unauthenticated user"
        when: "when the user surfs to the root url"
        def response = mvc.perform(get("/"))
        then: "then the user can view the home page"
        response.andExpect(status().isOk()).andExpect(view().name("index"))
    }

}
