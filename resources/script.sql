/* modeloLogicoBanco_Jairo: */

CREATE DATABASE jajaTur;

use jajatur;

CREATE TABLE cliente (
    codCliente int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nomeCliente varchar(50),
    idadeCliente int(3),
    cpfCliente varchar(11),
    telefoneCliente varchar(11),
    emailCliente varchar(30)
);

CREATE TABLE produtoViagem (
    codProdutoViagem int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    valorPrevioProdutoViagem float,
    dataPartidaProdutoViagem varchar(11),
    localPartidaProdutoViagem varchar(20)
);

CREATE TABLE atendente (
    codAtendente int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nomeAtendente varchar(50)
);

CREATE TABLE contrata (
    fk_cliente_codCliente int,
    fk_produtoViagem_codProdutoViagem int,
    codContrato int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    dataAquisicaoProdutoViagem date
);

CREATE TABLE contacta (
    fk_atendente_codAtendente int,
    fk_cliente_codCliente int,
    codAtendimento int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    assuntoAtendimento varchar(30),
    statusAtendimento varchar(20)
);
 
ALTER TABLE contrata ADD CONSTRAINT FK_contrata_2
    FOREIGN KEY (fk_cliente_codCliente)
    REFERENCES cliente (codCliente)
    ON DELETE RESTRICT;
 
ALTER TABLE contrata ADD CONSTRAINT FK_contrata_3
    FOREIGN KEY (fk_produtoViagem_codProdutoViagem)
    REFERENCES produtoViagem (codProdutoViagem)
    ON DELETE RESTRICT;
 
ALTER TABLE contacta ADD CONSTRAINT FK_contacta_2
    FOREIGN KEY (fk_atendente_codAtendente)
    REFERENCES atendente (codAtendente)
    ON DELETE RESTRICT;
 
ALTER TABLE contacta ADD CONSTRAINT FK_contacta_3
    FOREIGN KEY (fk_cliente_codCliente)
    REFERENCES cliente (codCliente)
    ON DELETE RESTRICT;
    
INSERT INTO produtoViagem (valorPrevioProdutoViagem, 
	dataPartidaProdutoViagem, localPartidaProdutoViagem)
	VALUES (2500.99, 12-12-2022, "Sao Paulo");

INSERT INTO produtoViagem (valorPrevioProdutoViagem, 
	dataPartidaProdutoViagem, localPartidaProdutoViagem)
	VALUES (3350.99, 20-02-2023, "Salvador");
	
INSERT INTO atendente(nomeAtendente) VALUES ("Jairo");

INSERT INTO atendente(nomeAtendente) VALUES ("Jose");