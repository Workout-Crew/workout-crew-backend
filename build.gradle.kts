plugins {
  kotlin("jvm") version "1.9.25"
  kotlin("plugin.spring") version "1.9.25"
  id("org.springframework.boot") version "3.3.4"
  id("io.spring.dependency-management") version "1.1.6"
  id("com.diffplug.spotless").version("6.19.0")
}

group = "com.comtongsu"

version = "0.0.1-SNAPSHOT"

java { toolchain { languageVersion = JavaLanguageVersion.of(17) } }

repositories { mavenCentral() }

dependencies {
  implementation("org.springframework.boot:spring-boot-starter")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
  developmentOnly("org.springframework.boot:spring-boot-devtools")

  // starter web
  implementation("org.springframework.boot:spring-boot-starter-web:3.3.4")

  // database - mysql
  implementation("com.mysql:mysql-connector-j:8.0.33")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")

  // Swagger
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")

  // Webflux
  implementation("org.springframework.boot:spring-boot-starter-webflux:3.3.3")

  // Dotenv
  implementation("me.paulschwarz:spring-dotenv:2.3.0")
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
  kotlin {
    target("**/*.kt")
    ktfmt()
    indentWithTabs(2)
    indentWithSpaces(4)
    trimTrailingWhitespace()
    endWithNewline()
  }
  kotlinGradle {
    target("**/*.gradle.kts")
    ktfmt()
  }
}

kotlin { compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") } }

allOpen {
  annotation("jakarta.persistence.Entity")
  annotation("jakarta.persistence.MappedSuperclass")
  annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> { useJUnitPlatform() }
