--liquibase formatted sql

--changeset nander.carmo:1
create table if not exists test(
    id INTEGER PRIMARY KEY
);
--rollback drop table test