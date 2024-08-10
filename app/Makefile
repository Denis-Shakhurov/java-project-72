.DEFAULT_GOAL := build

setup:
	./gradlew wrapper --gradle-version 8.5

run-dist:
	./build/install/java-project-72/bin/java-project-72

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

.PHONY: build
