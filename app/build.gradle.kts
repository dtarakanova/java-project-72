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

jacoco {
    toolVersion = "0.8.7"
}

test {
    finalizedBy jacocoTestReport
}

/*
tasks.test {
    useJUnitPlatform()
}
tasks.jacocoTestReport { reports { xml.required.set(true) } }
*/

jacocoTestReport {
    reports {
        xml.enabled true
        html.enabled true
        csv.enabled false
    }
}

check.dependsOn jacocoTestCoverageVerification