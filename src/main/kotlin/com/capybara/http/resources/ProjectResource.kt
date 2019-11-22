package com.capybara.http.resources

import com.capybara.core.model.*
import org.springframework.stereotype.Component

@Component
class ProjectResource: Resource({
    name = "Project"
    methods = listOf(Get, Post, Delete).toMutableList()
    properties = listOf(Property(
            name = "title",
            type = PropertyType())
    ).toMutableList()

})