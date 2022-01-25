# db2-test

Example.

We have IBM DB2 in docker container which is started by command from docker-hub db2 image overview:
	docker run -itd --name mydb2 --privileged=true -p 50000:50000 -e LICENSE=accept -e DB2INST1_PASSWORD=pass -e DBNAME=testdb -v storage:/database ibmcom/db2

Application uses JDBC for some simple operations with database.
