package com.capybara.http.handler

import com.capybara.core.http.HttpHandler
import com.capybara.core.http.HttpRequest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono

@Component
class MyHttpHandler(private val coreHttpHandler: HttpHandler) {

    fun handleRequest(request: ServerRequest): Mono<ServerResponse> {

        val headers = request
                .headers()
                .asHttpHeaders()
                .toMap()

        val methodName = request.methodName()

        return request
                .bodyToMono<String>()
                .map {
                    coreHttpHandler.handle(HttpRequest(methodName, request.path(), headers, it))
                }.flatMap {
                    ServerResponse.ok().contentType(APPLICATION_JSON).bodyValue(it)
                }
    }
}