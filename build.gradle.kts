plugins {
    kotlin("jvm") version "2.4.0"
}

group = "com.back"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.26.3")

}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}