CREATE TABLE PESSOA_FISICA (
ID NUMERIC(10) PRIMARY KEY,
CPF VARCHAR(11) NOT NULL UNIQUE,
NOME VARCHAR(120) NOT NULL,
ENDERECO VARCHAR(250) NOT NULL,
DATA_NASCIMENTO DATE NOT NULL,
DATA_CRIACAO TIMESTAMP NOT NULL,
DATA_ATUALIZACAO TIMESTAMP NOT NULL
);