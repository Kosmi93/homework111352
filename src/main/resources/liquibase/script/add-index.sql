-- liquibase formatted sql

-- changeset kosmi:1
CREATE INDEX users_name_idx ON student (name);

-- changeset kosmi:2
CREATE INDEX users_name_and_color_idx ON faculty (name,color);