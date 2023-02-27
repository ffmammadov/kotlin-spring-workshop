.PHONY: help
help:
	@echo 'Usage: make [command]'
run-db-daemon:
	@mkdir -p .db-volume
	@docker compose up mysql-db -d
