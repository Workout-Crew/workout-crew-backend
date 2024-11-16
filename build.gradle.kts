plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.4"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.diffplug.spotless").version("6.19.0")
    kotlin("kapt") version "1.9.21"
}

group = "com.comtongsu"

version = "0.0.1-SNAPSHOT"

java { toolchain { languageVersion = JavaLanguageVersion.of(17) } }

repositories { mavenCentral() }

val queryDslVersion: String by extra

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

    // Spring-Cloud-Starter-Aws
    implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")

    // QueryDsl
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")

    // Bedrock
    implementation("software.amazon.awssdk:bedrockruntime:2.29.9")

    // Json
    implementation("org.json:json:20231013")

    // Spring Batch
    implementation("org.springframework.batch:spring-batch-core:5.1.1")

    // Scheduler
    implementation("org.springframework.boot:spring-boot-starter-quartz")

    // Actuator
    implementation("org.springframework.boot:spring-boot-starter-actuator")
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
        indentWithTabs(2)
        indentWithSpaces(4)
        trimTrailingWhitespace()
        endWithNewline()
    }
}

kotlin { compilerOptions { freeCompilerArgs.addAll("-Xjsr305=strict") } }

val generated = file("src/main/generated")

tasks.withType<JavaCompile> { options.generatedSourceOutputDirectory.set(generated) }

sourceSets { main { kotlin.srcDirs += generated } }

tasks.named("clean") { doLast { generated.deleteRecursively() } }

kapt { generateStubs = true }

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> { useJUnitPlatform() }
