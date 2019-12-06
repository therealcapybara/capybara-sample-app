package com.capybara.spring.http.router

import com.capybara.core.model.Resource
import com.capybara.spring.http.handler.MyHttpHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class Router(private val myHttpHandler: MyHttpHandler) {
    @Bean
    fun projectRouter(resources: List<Resource>) = router {
        resources.map { it.name }.forEach { resourceName ->
            accept(APPLICATION_JSON).nest {
                (GET("/$resourceName") or GET("/$resourceName/{id}")
                        or POST("/$resourceName")
                        or DELETE("/$resourceName/{id}")
                        or PUT("/$resourceName/{id}")).invoke(myHttpHandler::handleRequest)
            }
        }
    }
}