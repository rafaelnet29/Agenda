drop database  if exists cad;

create database if not exists cad;

use cad;

create table if not exists usuario(
id int not null auto_increment,
nome varchar (250) not null,
endereco varchar(200)not null,
email varchar(200) not null,
telefone varchar(30),
celular varchar(30) not null,
cpf varchar(20)not null,
sobre varchar(255),
image longblob,
constraint usuario_id_pk primary key(id),
constraint usuario_cpf_uk unique (cpf));
