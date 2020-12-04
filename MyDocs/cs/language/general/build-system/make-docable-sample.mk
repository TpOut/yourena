.DEFAULT_GOAL:=help
SHELL:=/bin/bash

.PHONY: help deps clean build watch

help:  ## Display this help
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m\033[0m\n\nTargets:\n"} /^[a-zA-Z_-]+:.*?##/ { printf "  \033[36m%-10s\033[0m %s\n", $$1, $$2 }' $(MAKEFILE_LIST)

deps:  ## Check dependencies
	$(info Checking and getting dependencies)

clean: ## Cleanup the project folders
	$(info Cleaning up things)

build: clean deps ## Build the project
	$(info Building the project)

watch: clean deps ## Watch file changes and build
	$(info Watching and building the project)