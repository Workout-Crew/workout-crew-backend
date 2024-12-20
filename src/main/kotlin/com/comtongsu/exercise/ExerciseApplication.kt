package com.comtongsu.exercise

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication @EnableJpaAuditing class ExerciseApplication

fun main(args: Array<String>) {
    runApplication<ExerciseApplication>(*args)
}
