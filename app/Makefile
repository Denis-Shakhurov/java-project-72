.DEFAULT_GOAL := build

setup:
	./gradlew wrapper --gradle-version 8.5

run-dist:
	./build/install/java-project-78/bin/java-project-78

run:
	./gradlew run

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

.PHONY: build
