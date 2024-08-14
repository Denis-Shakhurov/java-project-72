import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    id("java")
    application
    checkstyle
    jacoco
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.freefair.lombok") version "8.6"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")

    implementation("io.javalin:javalin:6.1.3")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation("gg.jte:jte:3.1.9")
    implementation("io.javalin:javalin-rendering:6.1.3")
    implementation("io.javalin:javalin-bundle:6.1.3")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("com.h2database:h2:2.2.220")
    implementation(platform("com.konghq:unirest-java-bom:4.3.0"))
    implementation("com.konghq:unirest-java-core")
    implementation("com.konghq:unirest-modules-gson")
    implementation("com.konghq:unirest-modules-jackson")
    implementation ("org.jsoup:jsoup:1.15.3")
}

application {
    mainClass.set("hexlet.code.App")
}

jacoco {
    toolVersion = "0.8.11"
    reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
    }
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
    }
}