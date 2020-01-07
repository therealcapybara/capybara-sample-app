package com.capybara.spring.http.handler

import com.capybara.core.backend.data.MapResourceBlob
import com.capybara.core.http.HttpHandler
import com.capybara.core.http.HttpRequest
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.adapter.rxjava.RxJava2Adapter.singleToMono
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
                    val bodyAsMap = Gson().fromJson(it, JsonObject::class.java)
                            .entrySet()
                            .fold(mutableMapOf<String, Any>()) { map, (key, value) ->
                                map.put(key, value.asString)
                                map
                            }

                    MapResourceBlob(bodyAsMap)
                }
                .switchIfEmpty(Mono.just(MapResourceBlob(emptyMap())))
                .map {
                    coreHttpHandler.handle(HttpRequest(methodName, request.path(), headers, it))
                }
                .flatMap { singleToMono(it) }
                .map { it.body }
                .flatMap(ServerResponse.ok().contentType(APPLICATION_JSON)::bodyValue)
    }
}