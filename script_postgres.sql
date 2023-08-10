CREATE DATABASE NAME_bd;

CREATE TABLE usuario(
	id SERIAL,
	nome varchar(255),
	endereco varchar(255),
	email varchar(255),
	celular_aux varchar(255),
	celular varchar(255),
	sobre varchar(255),
	CONSTRAINT usuario_pk_id PRIMARY KEY (ID)
);

ALTER TABLE usuario RENAME COLUMN sebre to sobre; 