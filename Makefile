.DEFAULT_GOAL := build-run

build:
    make -C app build

lint:
    make -C app lint
build-run: build run

test:
	./gradlew test

report:
	@./gradlew jacocoTestReport

.PHONY: build
