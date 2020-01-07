package com.capybara.springboot.autoconfigure

import com.capybara.core.http.HttpHandler
import com.capybara.core.model.Resource
import com.capybara.spring.http.handler.MyHttpHandler
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
@ConditionalOnBean(Resource::class)
open class CapybaraAutoConfiguration {

    @Bean
    open fun coreHttpHandler(resources: List<Resource>): HttpHandler {
        return HttpHandler(resources)
    }

    @Bean
    open fun projectRouter(resources: List<Resource>, myHttpHandler: MyHttpHandler) = router {
        resources.map { it.name }.forEach { resourceName ->
            accept(MediaType.APPLICATION_JSON).nest {
                (GET("/$resourceName") or GET("/$resourceName/{id}")
                        or POST("/$resourceName")
                        or DELETE("/$resourceName/{id}")
                        or PUT("/$resourceName/{id}")).invoke(myHttpHandler::handleRequest)
            }
        }
    }

    @Bean
    open fun myHttpHandler(httpHandler: HttpHandler) : MyHttpHandler {
        return MyHttpHandler(httpHandler)
    }
}