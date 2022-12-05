FROM postgres:14-alpine

ARG PROJECT_NAME

ENV POSTGRES_USER dev
ENV POSTGRES_PASSWORD password
ENV POSTGRES_DB ${PROJECT_NAME}

# Copy all scripts to Docker PostgreSQL init directory to be executed on container init.
COPY script /docker-entrypoint-initdb.d/
