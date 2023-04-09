-- liquibase formatted sql

-- changeset denis:1
CREATE TABLE socks
(
    id              BIGSERIAL,
    color           VARCHAR,
    cotton_part     INT,
    quantity        INT
);


