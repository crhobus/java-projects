drop table produto;
drop sequence produto_seq;

create table produto (
id				number(10) primary key,
nome			varchar2(50) not null,
unidade_medida	varchar2(2) not null,
preco			number(10,4) not null
);

create sequence	produto_seq
start with		1
increment by	1
nocache
nocycle;