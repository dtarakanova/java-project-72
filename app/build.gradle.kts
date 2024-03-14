plugins {
    id ("java")
    id ("application")
    id ("checkstyle")
    id ("jacoco")
    id("io.freefair.lombok") version "8.3"
    id ("com.github.johnrengelman.shadow") version "8.1.1"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(20))
    }
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.h2database:h2:2.2.222")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.postgresql:postgresql:42.7.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("org.webjars:bootstrap:5.2.3")
    implementation("io.javalin:javalin:5.6.2")
    implementation("io.javalin:javalin-bundle:5.6.2")
    implementation("io.javalin:javalin-rendering:5.6.2")
    implementation("gg.jte:jte:3.1.9")
    implementation("com.konghq:unirest-java:3.13.0")
    implementation("org.jsoup:jsoup:1.14.3")
}


application {
    mainClass.set("hexlet.code.App")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

jacoco {
    toolVersion = "0.8.11"
}


tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}
