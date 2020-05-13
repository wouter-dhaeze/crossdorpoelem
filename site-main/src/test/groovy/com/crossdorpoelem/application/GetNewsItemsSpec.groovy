package com.crossdorpoelem.application

//import com.crossdorpoelem.query.GetNewsItems
import org.springframework.beans.factory.annotation.Autowired

class GetNewsItemsSpec extends AbstractAppSpec {

//    @Autowired
//    private GetNewsItems getNewsItems;

    def "As an unauthorized user, I want to view the news messages"() {
        given: "the fixed set of news items"

        when: "requesting all news items"
        def response = getNewsItems.get();

        then: "all news items are returned"
        !response.newsItems.isEmpty

    }

}
