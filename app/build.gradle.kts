plugins {
    id ("java")
    id ("application")
    id ("checkstyle")
    id ("jacoco")
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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
