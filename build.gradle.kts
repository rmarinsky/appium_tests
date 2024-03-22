plugins {
    kotlin("jvm") version "1.9.22"
}

group = "org.ravlyk"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.codeborne:selenide-appium:7.2.1")

}

tasks.test {
    useJUnitPlatform()
}


kotlin {
    jvmToolchain(17)
}