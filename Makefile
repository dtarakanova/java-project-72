.DEFAULT_GOAL := build-run

build:	make -C app build

lint:	make -C app lint

build-run:	make -C app build run

test:	make -C app test

report:	make -C app report

.PHONY:	build
