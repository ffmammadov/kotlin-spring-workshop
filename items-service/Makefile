.PHONY: help
help:
	@echo 'Usage: make [command]'
build-docker-image:
	./gradlew bootJar
	@docker build -t items:latest .
