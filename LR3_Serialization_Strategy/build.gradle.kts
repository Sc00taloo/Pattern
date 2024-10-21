plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.8.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0") // Замените на актуальную версию
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}