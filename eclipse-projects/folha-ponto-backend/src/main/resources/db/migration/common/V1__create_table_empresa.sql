CREATE TABLE EMPRESA (
ID NUMERIC(10) PRIMARY KEY,
NOME VARCHAR(120) NOT NULL,
CNPJ VARCHAR(14) NOT NULL UNIQUE,
DATA_CRIACAO TIMESTAMP NOT NULL,
DATA_ATUALIZACAO TIMESTAMP NOT NULL
);