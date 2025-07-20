-- Habilitar extensión para UUIDs
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "project" (
    "id" bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "oid" uuid NOT NULL UNIQUE,
    "active" boolean NOT NULL,
    "creation_user" bigint NOT NULL,
    "last_modification_user" bigint,
    "creation_date" date NOT NULL,
    "last_modification_date" date
);

CREATE TABLE "janus_user" (
    "id" bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "username" varchar(255) NOT NULL,
    "password_hash" varchar(255) NOT NULL,
    "active" boolean NOT NULL,
    "creation_user" varchar(255) NOT NULL,
    "last_modification_user" varchar(255),
    "creation_date" date NOT NULL,
    "last_modification_date" date
);

CREATE TABLE "role" (
    "id" bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "code" varchar(25) NOT NULL UNIQUE,
    "active" boolean NOT NULL,
    "creation_user" varchar(255) NOT NULL,
    "last_modification_user" varchar(255),
    "creation_date" date NOT NULL,
    "last_modification_date" date NOT NULL
);

CREATE TABLE "authority" (
    "id" bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "code" varchar(100) NOT NULL UNIQUE,
    "role_id" bigint NOT NULL,
    "active" boolean NOT NULL,
    "creation_user" varchar(255) NOT NULL,
    "last_modification_user" varchar(255),
    "creation_date" date NOT NULL,
    "last_modification_date" date,
    FOREIGN KEY ("role_id") REFERENCES "role"("id")
);



CREATE TABLE "user_role" (
    "id" bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "user_id" bigint NOT NULL,
    "role_id" bigint NOT NULL,
    "active" boolean NOT NULL,
    FOREIGN KEY ("user_id") REFERENCES "janus_user"("id"),
    FOREIGN KEY ("role_id") REFERENCES "role"("id")
);

CREATE TABLE "user_project" (
    "id" bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "user_id" bigint NOT NULL,
    "project_id" bigint NOT NULL,
    "active" boolean,
    FOREIGN KEY ("user_id") REFERENCES "janus_user"("id"),
    FOREIGN KEY ("project_id") REFERENCES "project"("id")
);

CREATE TABLE "project_role" (
    "id" bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "project_id" bigint NOT NULL,
    "role_id" bigint NOT NULL,
    "active" boolean,
    FOREIGN KEY ("project_id") REFERENCES "project"("id"),
    FOREIGN KEY ("role_id") REFERENCES "role"("id")
);


-- Crear el rol admin 
INSERT INTO Role(code , active , creation_user , last_modification_user , creation_date , last_modification_date) 
VALUES('JANUS_ADMIN',true , 'system' , 'system' , current_date , current_date);

-- Crear las authorities del ROLE JANUS_ADMIN
INSERT INTO Authority(code , role_id , active , creation_user , last_modification_user , creation_date , last_modification_date) 
VALUES('J_INSERT' , (SELECT id FROM Role WHERE code = 'JANUS_ADMIN') ,true , 'system' , 'system' , current_date , current_date);

INSERT INTO Authority(code , role_id , active , creation_user , last_modification_user , creation_date , last_modification_date) 
VALUES('J_UPDATE' , (SELECT id FROM Role WHERE code = 'JANUS_ADMIN') ,true , 'system' , 'system' , current_date , current_date);

INSERT INTO Authority(code , role_id , active , creation_user , last_modification_user , creation_date , last_modification_date) 
VALUES('J_DELETE' , (SELECT id FROM Role WHERE code = 'JANUS_ADMIN'),true , 'system' , 'system' , current_date , current_date);


INSERT INTO Authority(code , role_id , active , creation_user , last_modification_user , creation_date , last_modification_date) 
VALUES('J_Test' , (SELECT id FROM Role WHERE code = 'JANUS_ADMIN'), true , 'system' , 'system' , current_date , current_date);
