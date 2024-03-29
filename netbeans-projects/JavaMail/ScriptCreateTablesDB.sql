/*Contatos*/
CREATE TABLE TBCONTATOS(
CODCONTATO NUMBER PRIMARY KEY,
NOME VARCHAR(60) NOT NULL,
EMAIL VARCHAR(70) NOT NULL);

/*E-Mail*/
CREATE TABLE TBEMAIL(
CODEMAIL NUMBER PRIMARY KEY,
EMAIL VARCHAR(90) NOT NULL,
ASSUNTO VARCHAR(60) NOT NULL,
ANEXO VARCHAR(40),
DATAREC TIMESTAMP NOT NULL);

/*Conex�o*/
CREATE TABLE TBCONEXAO(
CODCONEXAO NUMBER PRIMARY KEY,
TIPO VARCHAR(4) NOT NULL,
NOME VARCHAR(60) NOT NULL,
SERVPOP3IMAP VARCHAR(50) NOT NULL,
USUARIO VARCHAR(30) NOT NULL,
SENHA VARCHAR(30) NOT NULL,
SERVSMTP VARCHAR(50) NOT NULL,
EMAIL VARCHAR(70) NOT NULL,
PORTAPOP3IMAP NUMBER NOT NULL,
PORTASMTP NUMBER NOT NULL,
AUTENTICACAO VARCHAR(1) NOT NULL,
CONSEGURANCA VARCHAR(1) NOT NULL);