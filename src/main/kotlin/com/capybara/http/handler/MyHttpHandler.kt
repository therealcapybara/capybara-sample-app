package com.capybara.http.handler

import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono


@Component
class Dummy()

@Component
class MyHttpHandler(private val dummy: Dummy ) {

    fun handleRequest(request: ServerRequest): Mono<ServerResponse> {
        return request
                .bodyToMono<String>()
                .flatMap { ServerResponse.ok().contentType(APPLICATION_JSON).bodyValue(mapOf("path" to request.path(), "body" to it)) }
    }

}