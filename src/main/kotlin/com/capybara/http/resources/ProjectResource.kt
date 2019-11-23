package com.capybara.http.resources

import com.capybara.core.model.*
import org.springframework.stereotype.Component

@Component
class ProjectResource: Resource({
    name("project")

    methods {
        get()
        post()
        delete()
        put()
    }

    properties {
        property("title", TextType)
        property("abstract", TextType)
    }

})