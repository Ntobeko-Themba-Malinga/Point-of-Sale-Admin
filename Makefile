.IGNORE: kill-servers, db

all:
	make kill-servers
	make run_unit_test

kill-servers:
	fuser -k 8082/tcp

db:
	sh lib/h2/bin/h2.sh

unit_test:
	mvn test
	make kill-servers

run_unit_test:
	make -j db unit_test