package com.capybara.http.resources.configuration

import com.capybara.core.http.HttpHandler
import com.capybara.core.model.Resource
import com.capybara.http.resources.ProjectResource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ResourceConfig {

    @Bean
    fun resources(projectResource: ProjectResource): List<Resource>{
        return listOf(projectResource)
    }

    @Bean
    fun coreHttpHandler(resources:List<Resource>): HttpHandler{
        return HttpHandler(resources)
    }


}