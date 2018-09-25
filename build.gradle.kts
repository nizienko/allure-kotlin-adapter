import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.71"
}

group = "com.jetbrain.test"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
//    compile("ru.yandex.qatools.allure:allure-commons:1.5.4")
    compile("io.qameta.allure:allure2-model-api:1.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.1.0")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.1.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}