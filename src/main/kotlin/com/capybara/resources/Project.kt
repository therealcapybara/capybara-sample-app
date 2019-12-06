package com.capybara.resources

import com.capybara.core.model.Resource
import com.capybara.core.model.TextType
import org.springframework.stereotype.Component


@Component
class Project : Resource({
    name("project")

    methods {
        get()
        post()
        delete()
        put()
    }

    properties {
        property("id", TextType)
        property("title", TextType)
        property("abstract", TextType)
    }

    backend {
        mongoDb(host = "localhost", port = 27017, database = "xpto")
    }
})