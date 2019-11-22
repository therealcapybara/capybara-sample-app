package com.capybara

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CapybaraApplication

fun main(args: Array<String>) {
	runApplication<CapybaraApplication>(*args)
}
