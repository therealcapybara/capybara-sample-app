package com.capybara.http.router

import com.capybara.http.handler.MyHttpHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.router

@Configuration
class Router(private val myHttpHandler: MyHttpHandler) {
    @Bean
    fun projectRouter() = router {
        accept(APPLICATION_JSON).nest {
            (GET("/project") or POST("/project")).invoke(myHttpHandler::handleRequest)
        }
    }
}