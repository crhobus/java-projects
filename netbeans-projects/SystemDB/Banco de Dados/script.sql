ALTER TABLE tbusuario MODIFY codfunc NUMBER;
ALTER TABLE tbsetor ADD setorAssinadoDigital BLOB;
ALTER TABLE tbdepartamento DROP COLUMN departamento;
ALTER TABLE tbdepartamento ADD departamento BLOB NOT NULL;
ALTER TABLE tbusuario DROP COLUMN senha;
ALTER TABLE tbusuario ADD senha BLOB NOT NULL;
ALTER TABLE tbfornecedor DROP COLUMN sigla;
ALTER TABLE tbfornecedor ADD sigla BLOB;