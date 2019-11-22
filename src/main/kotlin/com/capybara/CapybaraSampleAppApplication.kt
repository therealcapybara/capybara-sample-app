package com.capybara

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CapybaraSampleAppApplication

fun main(args: Array<String>) {
	runApplication<CapybaraSampleAppApplication>(*args)
}
