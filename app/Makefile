.DEFAULT_GOAL := build-run

build:
    ./gradlew build

run:
    ./gradlew run

test:
    ./gradlew test

lint:
    ./gradlew checkstyleMain checkstyleTest

report:
    ./gradlew jacocoTestReport

build-run: build run

.PHONY: build
