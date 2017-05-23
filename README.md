# Spring Data Rest with security and multiple integration tests approaches

### References:
- http://mrbool.com/rest-server-with-spring-data-spring-boot-and-postgresql/34023

### How to use
*Gradle is required.*
*Docker is required.*

To run: 
```
docker run --name some-postgres -e POSTGRES_PASSWORD=1 -d -p 5432:5432 postgres
gradle bootRun
```

### Postgres
To run postgres

```
docker kill some-postgres
docker rm -f some-postgres

docker run --name some-postgres -e POSTGRES_PASSWORD=1 -d -p 5432:5432 postgres
docker exec -it some-postgres bash

psql -U postgres

CREATE DATABASE mytest;
\q
```
