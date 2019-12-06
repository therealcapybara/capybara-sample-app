package com.capybara.spring.http.resources.configuration

import com.capybara.core.http.HttpHandler
import com.capybara.core.model.Resource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ResourceConfig {

    @Bean
    fun coreHttpHandler(resources:List<Resource>): HttpHandler{
        return HttpHandler(resources)
    }


}