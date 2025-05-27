-- Criação do banco
DROP DATABASE IF EXISTS db_locadora;
CREATE DATABASE db_locadora;
USE db_locadora;

-- TABELAS AUXILIARES

CREATE TABLE categorias_veiculos (
	id_categoria INT PRIMARY KEY AUTO_INCREMENT NOT NULL, 
    descricao VARCHAR(30) NOT NULL
);

CREATE TABLE tipos_seguro (
	id_seguro INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    nome VARCHAR(30) UNIQUE NOT NULL,
    descricao TEXT NOT NULL,
    taxa DECIMAL(10,2) NOT NULL
);

CREATE TABLE meios_de_pagamento (
	id_pagamento INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
   	descricao VARCHAR(30) NOT NULL
);

CREATE TABLE usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT NOT NULL, 
    nome VARCHAR(80) NOT NULL, 
    email VARCHAR(80) UNIQUE NOT NULL,
    senha VARCHAR(80) NOT NULL,
    tipo_usuario INT NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE clientes (
	id_cliente INT PRIMARY KEY AUTO_INCREMENT NOT NULL, 
    cpf CHAR(11) UNIQUE NOT NULL,
    nome  VARCHAR(80) NOT NULL, 
    email VARCHAR(80) UNIQUE NOT NULL,
    cep VARCHAR(8) NOT NULL,
    rua VARCHAR(80) NOT NULL, 
    numero VARCHAR(10) NOT NULL,
    complemento VARCHAR(30),
    bairro VARCHAR(80) NOT NULL,
    cidade VARCHAR(80) NOT NULL, 
    estado VARCHAR(30) NOT NULL,
    dataNascimento DATE NOT NULL, 
    telefone VARCHAR(20) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE
);

CREATE TABLE veiculos (
	placa CHAR(7) PRIMARY KEY NOT NULL, 
    marca VARCHAR(30) NOT NULL, 
    cor VARCHAR(30) NOT NULL, 
    ano INT NOT NULL, 
    chassi VARCHAR(17) UNIQUE NOT NULL, 
    modelo VARCHAR(30) NOT NULL, 
    quilometragem INT NOT NULL, 
    id_categoria INT, 
    preco_base DECIMAL(10,2) NOT NULL, 
    CONSTRAINT fk_veiculo_categoria FOREIGN KEY(id_categoria) REFERENCES categorias_veiculos(id_categoria)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- TABELAS DE MANUTENÇÃO

CREATE TABLE manutencoes (
	id_manutencao INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	placa CHAR(7), 
	descricao TEXT NOT NULL, 
	finalizado BOOLEAN DEFAULT FALSE, 
	instante_chegada DATETIME, 
	instante_saida DATETIME,
	total DECIMAL(10,2),
	CONSTRAINT fk_manutencao_placa FOREIGN KEY(placa) REFERENCES veiculos(placa)
    ON DELETE SET NULL
	ON UPDATE CASCADE
);

CREATE TABLE servicos(
	id_servico INT PRIMARY KEY AUTO_INCREMENT,
    descricao TEXT NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    isComum BOOLEAN DEFAULT FALSE
);

CREATE TABLE manutencoes_servicos(
	idMS INT PRIMARY KEY AUTO_INCREMENT, 
    id_manutencao INT, 
    id_servico INT,
    valor_item DECIMAL(10,2),
    CONSTRAINT fk_ms_manutencao FOREIGN KEY(id_manutencao) REFERENCES manutencoes(id_manutencao)
    ON DELETE SET NULL ON UPDATE CASCADE, 
    CONSTRAINT fk_ms_servico FOREIGN KEY(id_servico) REFERENCES servicos(id_servico)
    ON DELETE SET NULL ON UPDATE CASCADE
);

-- TABELAS DE LOCAÇÃO

CREATE TABLE pedidos_locacao (
	id_pedido INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_atendente INT NOT NULL,
    id_cliente INT NOT NULL,
    id_seguro INT,
    placa CHAR(7), 
    id_saida INT,
    id_devolucao INT, 
    devolucao_esperada DATE NOT NULL, 
    forma_de_pagamento INT NOT NULL, 
    finalizado BOOLEAN DEFAULT FALSE, 
    valor_total DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_pedido_atendente FOREIGN KEY(id_atendente) REFERENCES usuarios(id_usuario)
    ON UPDATE CASCADE,
	CONSTRAINT fk_pedido_cliente FOREIGN KEY(id_cliente) REFERENCES clientes(id_cliente)
    ON UPDATE CASCADE, 
    CONSTRAINT fk_pedido_placa FOREIGN KEY(placa) REFERENCES veiculos(placa)
    ON DELETE SET NULL
    ON UPDATE CASCADE, 
    CONSTRAINT fk_pedido_pagamento FOREIGN KEY(forma_de_pagamento) REFERENCES meios_de_pagamento(id_pagamento)
    ON UPDATE CASCADE,
    CONSTRAINT fk_pedido_seguro FOREIGN KEY(id_seguro) REFERENCES tipos_seguro(id_seguro)
    ON DELETE SET NULL
    ON UPDATE CASCADE
);

-- TABELAS QUE DEPENDEM DO PEDIDO

CREATE TABLE devolucoes_veiculos (
	id_devolucao INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	id_pedido INT,
	id_assistente INT,
	instante_devolucao DATETIME, 
    placa CHAR(7),
	km_chegada INT,
	id_manutencao INT, 
    CONSTRAINT fk_devolucao_placa FOREIGN KEY(placa) REFERENCES veiculos(placa)
	ON DELETE SET NULL 
    ON UPDATE CASCADE,
	CONSTRAINT fk_devolucao_pedido FOREIGN KEY(id_pedido) REFERENCES pedidos_locacao(id_pedido)
    ON DELETE SET NULL
	ON UPDATE CASCADE,
	CONSTRAINT fk_devolucao_assistente FOREIGN KEY(id_assistente) REFERENCES usuarios(id_usuario)
	ON UPDATE CASCADE, 
	CONSTRAINT fk_devolucao_manutencao FOREIGN KEY(id_manutencao) REFERENCES manutencoes(id_manutencao)
	ON UPDATE CASCADE
);

CREATE TABLE saidas_veiculos (
	id_saida INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
	id_pedido INT,
	id_assistente INT, 
    placa CHAR(7),
	instante_saida DATETIME, 
	km_saida INT,
    CONSTRAINT fk_saida_placa FOREIGN KEY(placa) REFERENCES veiculos(placa)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
	CONSTRAINT fk_saida_pedido FOREIGN KEY(id_pedido) REFERENCES pedidos_locacao(id_pedido)
	ON DELETE SET NULL
	ON UPDATE CASCADE,
	CONSTRAINT fk_saida_assistente FOREIGN KEY(id_assistente) REFERENCES usuarios(id_usuario)
	ON UPDATE CASCADE
);

-- Atualizar chaves estrangeiras que fazem referência às tabelas criadas por último

ALTER TABLE pedidos_locacao
	ADD CONSTRAINT fk_pedido_saida FOREIGN KEY(id_saida) REFERENCES saidas_veiculos(id_saida)
	ON UPDATE CASCADE,
	ADD CONSTRAINT fk_pedido_devolucao FOREIGN KEY(id_devolucao) REFERENCES devolucoes_veiculos(id_devolucao)
	ON UPDATE CASCADE;