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
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("io.javalin:javalin:5.6.2")
    implementation("io.javalin:javalin-bundle:5.6.2")
    implementation("io.javalin:javalin-rendering:5.6.2")
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
    //dependsOn test
    reports {
        xml.required.set(true)
    }
}
