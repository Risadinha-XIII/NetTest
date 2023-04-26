CREATE DATABASE pedido
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

create table PEDIDO(
ID SERIAL PRIMARY KEY,
DT_PEDIDO TIMESTAMP default CURRENT_TIMESTAMP,
VL_TOTAL float NOT NULL);

create table PRODUTO(
ID SERIAL PRIMARY KEY,
DESCRICAO VARCHAR(200),
preco float);

create table ITEM_PEDIDO(
ID SERIAL PRIMARY KEY,
ID_PEDIDO SERIAL NOT NULL,
ID_PRODUTO SERIAL NOT NULL,
QT_PRODUTO INTEGER,
CONSTRAINT fk_pedido foreign key(id_pedido) references pedido(id),
constraint fk_produto foreign key(id_produto) references produto(id));