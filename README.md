# Xplore Service

A REST API to support the requirements of the Xplore application.

## Project Prerequisites

- JDK 17
- Maven
- Docker

## Getting Started

### Running Locally

> Ensure you are in the root directory when running the following commands.

```text
# 1. Generate the .war file for xplore-service
mvn clean package

# 2. Start the application in a docker container
./start.sh
```

Once, the application has start successfully it can be accessed locally via `http:localhost:8080`.

### Modifying PostgreSQL Database

The PostgreSQL database can be modified via the scripts which are run against the database on initialisation which are
located at `docker/postgresql/script/`.

There is currently two scripts, a script for creating databases (`create-tables.sql`) and another script for populating
records within the tables (`insert-records.sql`).

> Each script should only be modified to allow for thorough testing. Test records should also try match real world data
> as close as possible.

```text
...
├── docker
│   └── postgresql
│       └── script
│           ├── create-tables.sql
│           └── insert-records.sql
...
```

## Testing

### Modifying PostgreSQL Data