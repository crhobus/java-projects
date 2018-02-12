drop table contato;
drop sequence contato_seq;

create table contato (
nr_sequencia	number(10) primary key,
nome			varchar2(80) not null,
telefone		varchar2(9) not null,
email			varchar2(50) not null
);

create sequence	contato_seq
start with		1
increment by	1
nocache
nocycle;