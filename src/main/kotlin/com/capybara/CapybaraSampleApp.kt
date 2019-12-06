package com.capybara

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CapybaraSampleApp

fun main(args: Array<String>) {
	runApplication<CapybaraSampleApp>(*args)
}
