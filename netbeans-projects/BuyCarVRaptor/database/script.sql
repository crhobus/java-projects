drop table item_pedido;
drop table pedido;
drop table cliente;
drop table veiculo;
drop table opcional;

drop sequence cliente_seq;
drop sequence veiculo_seq;
drop sequence opcional_seq;
drop sequence pedido_seq;
drop sequence item_pedido_seq;

create table cliente (
nr_sequencia	number(10)		primary key,
tipo_pessoa		varchar2(1)		not null,
cpf				varchar2(14),
cnpj			varchar2(18),
nome			varchar2(80)	not null,
telefone		varchar2(14)	not null,
email			varchar2(60),
endereco		varchar2(60)	not null,
bairro			varchar2(50)	not null,
numero			number(10)		not null,
cep				varchar2(9)		not null,
cidade			varchar2(50)	not null,
estado			varchar2(40)	not null
);

create table veiculo (
nr_sequencia	number(10)		primary key,
modelo			varchar2(30)	not null,
marca			varchar2(20)	not null,
ano_fabricacao	number(10)		not null,
ano_modelo		number(10)		not null,
cor				varchar2(15)	not null,
combustivel		varchar2(1)		not null,
portas			number(10)		not null,
litros			number(10,4)	not null,
potencia		number(10)		not null,
cilindros		number(10)		not null,
valvulas		number(10)		not null,
categoria		varchar2(30)	not null,
lotacao			number(10)		not null,
valor			number(10,4)	not null
);

create table opcional (
nr_sequencia	number(10)		primary key,
nome			varchar2(100)	not null,
descricao		varchar2(4000),
valor			number(10,4)	not null
);

create table pedido (
nr_sequencia	number(10)		primary key,
nr_seq_cliente	number(10)		not null,
nr_seq_veiculo	number(10)		not null,
dt_emissao		date			not null,
situacao		varchar2(1)		not null,
valor_veiculo	number(10,4)	not null,
sub_total		number(10,4)	not null,
desconto		number(10,4)	not null,
valor_total		number(10,4)	not null,
condicao_pagto	varchar2(15)	not null,
constraint fk_nr_seq_cliente foreign key (nr_seq_cliente) references cliente (nr_sequencia),
constraint fk_nr_seq_veiculo foreign key (nr_seq_veiculo) references veiculo (nr_sequencia)
);

create table item_pedido (
nr_sequencia	number(10)		primary key,
nr_seq_opcional	number(10)		not null,
nr_seq_pedido	number(10)		not null,
valor_opcional	number(10,4)	not null,
constraint fk_nr_seq_opcional foreign key (nr_seq_opcional) references opcional (nr_sequencia),
constraint fk_nr_seq_pedido foreign key (nr_seq_pedido) references pedido (nr_sequencia)
);

create sequence	cliente_seq
start with		1
increment by	1
nocache
nocycle;

create sequence	veiculo_seq
start with		1
increment by	1
nocache
nocycle;

create sequence	opcional_seq
start with		1
increment by	1
nocache
nocycle;

create sequence	pedido_seq
start with		1
increment by	1
nocache
nocycle;

create sequence	item_pedido_seq
start with		1
increment by	1
nocache
nocycle;
