plugins {
    id ("java")
    id ("application")
    id ("checkstyle")
    id ("jacoco")
    id("io.freefair.lombok") version "8.3"
    id ("com.github.johnrengelman.shadow") version "8.1.1"
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
}

jacoco {
    toolVersion = "0.8.8"
}


tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}
