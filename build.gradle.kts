import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.3.50"
	id ("maven-publish")
}

group = "com.capybara"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenLocal()
	mavenCentral()
}

dependencies {
	api("com.capybara:capybara-core:0.0.1-SNAPSHOT")
	api("org.springframework.boot:spring-boot-starter-webflux:2.2.2.RELEASE")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1")

	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.3.2")
	implementation("com.google.code.gson:gson:2.8.6")
	implementation("io.projectreactor.addons:reactor-adapter:3.3.0.RELEASE")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = "com.capybara"
			artifactId = "capybara-spring-boot-starter"
			version = version

			from(components["kotlin"])
		}
	}
}

