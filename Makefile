.PHONY: help
help:
	@echo 'Usage: make [command]'
run-db-daemon:
	@mkdir -p .db-volume
	@docker compose up mysql-db -d
run-auth-daemon:
	@mkdir -p .db-volume
	@docker rmi auth:latest || true
	$(MAKE) -C auth build-docker-image
	@docker compose up auth-service -d
run-items-daemon:
	@mkdir -p .db-volume
	@docker rmi auth:latest || true
	$(MAKE) -C auth build-docker-image
	@docker rmi items:latest || true
	$(MAKE) -C items-service build-docker-image
	@docker compose up items-service -d
