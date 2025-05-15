-- TABELAS AUXILIARES

CREATE TABLE categorias_veiculos (
	id_categoria INT PRIMARY KEY AUTO_INCREMENT, 
    descricao VARCHAR(30) NOT NULL
);

CREATE TABLE tipos_seguro (
	id_seguro INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) UNIQUE NOT NULL,
    descricao TEXT NOT NULL,
    taxa DECIMAL(10,2) NOT NULL
);

CREATE TABLE meios_de_pagamento (
	id_pagamento INT PRIMARY KEY AUTO_INCREMENT,
    descricao VARCHAR(30) NOT NULL
);

CREATE TABLE usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT NOT NULL, 
    nome VARCHAR(80) NOT NULL, 
    email VARCHAR(80) UNIQUE NOT NULL,
    senha VARCHAR(80) NOT NULL,
    tipo_usuario INT NOT NULL
);

CREATE TABLE clientes (
	id_cliente INT PRIMARY KEY AUTO_INCREMENT, 
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
	placa CHAR(7) PRIMARY KEY, 
    marca VARCHAR(30) NOT NULL, 
    cor VARCHAR(30) NOT NULL, 
    ano INT NOT NULL, 
    chassi VARCHAR(17) UNIQUE NOT NULL, 
    modelo VARCHAR(30) NOT NULL, 
    quilometragem INT NOT NULL, 
    id_categoria INT NOT NULL, 
    preco_base DECIMAL(10,2) NOT NULL, 
    CONSTRAINT fk_veiculo_categoria FOREIGN KEY(id_categoria) REFERENCES categorias_veiculos(id_categoria)
);

-- TABELAS DE MANUTENÇÃO

CREATE TABLE manutencoes (
	id_manutencao INT PRIMARY KEY AUTO_INCREMENT,
	placa CHAR(7) NOT NULL, 
	descricao TEXT NOT NULL, 
	finalizado BOOLEAN DEFAULT FALSE, 
	instante_chegada TIMESTAMP, 
	instante_saida TIMESTAMP,
	total DECIMAL(10,2),
	CONSTRAINT fk_manutencao_placa FOREIGN KEY(placa) REFERENCES veiculos(placa)
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
    ON DELETE CASCADE, 
    CONSTRAINT fk_ms_servico FOREIGN KEY(id_servico) REFERENCES servicos(id_servico)
    ON DELETE CASCADE
);

-- TABELAS DE LOCAÇÃO

CREATE TABLE pedidos_locacao (
	id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    id_atendente INT NOT NULL,
    id_cliente INT NOT NULL,
    id_seguro INT,
    placa CHAR(7) NOT NULL, 
    id_saida INT,
    id_devolucao INT, 
    devolucao_esperada DATE NOT NULL, 
    forma_de_pagamento INT NOT NULL, 
    finalizado BOOLEAN DEFAULT FALSE, 
    valor_total DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_pedido_atendente FOREIGN KEY(id_atendente) REFERENCES usuarios(id_usuario),
	CONSTRAINT fk_pedido_cliente FOREIGN KEY(id_cliente) REFERENCES clientes(id_cliente),
    CONSTRAINT fk_pedido_placa FOREIGN KEY(placa) REFERENCES veiculos(placa), 
    CONSTRAINT fk_pedido_pagamento FOREIGN KEY(forma_de_pagamento) REFERENCES meios_de_pagamento(id_pagamento),
    CONSTRAINT fk_pedido_seguro FOREIGN KEY(id_seguro) REFERENCES tipos_seguro(id_seguro)
);

-- TABELAS QUE DEPENDEM DO PEDIDO

CREATE TABLE devolucoes_veiculos (
	id_devolucao INT PRIMARY KEY AUTO_INCREMENT,
	id_pedido INT NOT NULL,
	id_assistente INT,
	instante_devolucao TIMESTAMP, 
    placa CHAR(7) NOT NULL,
	km_chegada INT,
	id_manutencao INT, 
    CONSTRAINT fk_devolucao_placa FOREIGN KEY(placa) REFERENCES veiculos(placa)
    ON DELETE CASCADE,
	CONSTRAINT fk_devolucao_pedido FOREIGN KEY(id_pedido) REFERENCES pedidos_locacao(id_pedido),
	CONSTRAINT fk_devolucao_assistente FOREIGN KEY(id_assistente) REFERENCES usuarios(id_usuario),
	CONSTRAINT fk_devolucao_manutencao FOREIGN KEY(id_manutencao) REFERENCES manutencoes(id_manutencao)
);

CREATE TABLE saidas_veiculos (
	id_saida INT PRIMARY KEY AUTO_INCREMENT,
	id_pedido INT NOT NULL,
	id_assistente INT NOT NULL, 
    placa CHAR(7) NOT NULL,
	instante_saida TIMESTAMP, 
	km_saida INT,
    CONSTRAINT fk_saida_placa FOREIGN KEY(placa) REFERENCES veiculos(placa)
    ON DELETE CASCADE,
	CONSTRAINT fk_saida_pedido FOREIGN KEY(id_pedido) REFERENCES pedidos_locacao(id_pedido),
	CONSTRAINT fk_saida_assistente FOREIGN KEY(id_assistente) REFERENCES usuarios(id_usuario)
);

-- Atualizar chaves estrangeiras que fazem referência às tabelas criadas por último

ALTER TABLE pedidos_locacao
	ADD CONSTRAINT fk_pedido_saida FOREIGN KEY(id_saida) REFERENCES saidas_veiculos(id_saida);

ALTER TABLE pedidos_locacao
	ADD CONSTRAINT fk_pedido_devolucao FOREIGN KEY(id_devolucao) REFERENCES devolucoes_veiculos(id_devolucao);



INSERT INTO categorias_veiculos (descricao) VALUES
('Econômico'), ('SUV'), ('Esportivo'), ('Luxo'), ('Utilitário');

-- tipos_seguro
INSERT INTO tipos_seguro (nome, descricao, taxa) VALUES
('Básico', 'Cobertura contra roubo e furto.', 150.00),
('Intermediário', 'Cobertura total, exceto danos a terceiros.', 250.00),
('Completo', 'Cobertura total, incluindo terceiros.', 350.00),
('VIP', 'Seguro completo com assistência premium.', 500.00),
('Simples', 'Somente contra roubo.', 100.00);

-- meios_de_pagamento
INSERT INTO meios_de_pagamento (descricao) VALUES
('Dinheiro'), ('Cartão de Crédito'), ('Cartão de Débito'), ('PIX'), ('Boleto');

-- usuarios
INSERT INTO usuarios (nome, email, senha, tipo_usuario) VALUES
('João Silva', 'joao@email.com', '1234', 1),
('Maria Souza', 'maria@email.com', '1234', 2),
('Carlos Lima', 'carlos@email.com', '1234', 1),
('Ana Costa', 'ana@email.com', '1234', 2),
('Rafael Dias', 'rafael@email.com', '1234', 1);

-- clientes
INSERT INTO clientes (cpf, nome, email, cep, rua, numero, complemento, bairro, cidade, estado, dataNascimento, telefone, ativo) VALUES
('12345678901', 'Cliente A', 'clientea@email.com', '12345678', 'Rua 1', '100', 'Apto 1', 'Bairro A', 'Cidade A', 'Estado A', '1990-01-01', '11999990000', TRUE),
('23456789012', 'Cliente B', 'clienteb@email.com', '23456789', 'Rua 2', '101', NULL, 'Bairro B', 'Cidade B', 'Estado B', '1985-02-02', '21999990000', TRUE),
('34567890123', 'Cliente C', 'clientec@email.com', '34567890', 'Rua 3', '102', '', 'Bairro C', 'Cidade C', 'Estado C', '1995-03-03', '31999990000', TRUE),
('45678901234', 'Cliente D', 'cliented@email.com', '45678901', 'Rua 4', '103', 'Casa', 'Bairro D', 'Cidade D', 'Estado D', '1980-04-04', '41999990000', TRUE),
('56789012345', 'Cliente E', 'clientee@email.com', '56789012', 'Rua 5', '104', '', 'Bairro E', 'Cidade E', 'Estado E', '2000-05-05', '51999990000', TRUE);

-- veiculos
INSERT INTO veiculos (placa, marca, cor, ano, chassi, modelo, quilometragem, id_categoria, preco_base) VALUES
('AAA1234', 'Fiat', 'Prata', 2020, '9BWZZZ377VT004251', 'Uno', 20000, 1, 100.00),
('BBB1234', 'Chevrolet', 'Branco', 2019, '9BWZZZ377VT004252', 'Onix', 30000, 2, 120.00),
('CCC1234', 'Ford', 'Preto', 2021, '9BWZZZ377VT004253', 'Ka', 15000, 1, 110.00),
('DDD1234', 'Toyota', 'Vermelho', 2022, '9BWZZZ377VT004254', 'Corolla', 10000, 4, 200.00),
('EEE1234', 'Renault', 'Azul', 2018, '9BWZZZ377VT004255', 'Logan', 50000, 5, 90.00);

-- manutencoes
INSERT INTO manutencoes (placa, descricao, finalizado, instante_chegada, instante_saida, total) VALUES
('AAA1234', 'Troca de óleo', TRUE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 150.00),
('BBB1234', 'Revisão geral', FALSE, CURRENT_TIMESTAMP(), NULL, 300.00),
('CCC1234', 'Alinhamento', TRUE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 100.00),
('DDD1234', 'Freio', FALSE, CURRENT_TIMESTAMP(), NULL, 200.00),
('EEE1234', 'Pneus', TRUE, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 400.00);

-- servicos
INSERT INTO servicos (descricao, preco, isComum) VALUES
('Troca de óleo', 150.00, TRUE),
('Revisão completa', 300.00, TRUE),
('Alinhamento e balanceamento', 100.00, TRUE),
('Substituição de freios', 200.00, FALSE),
('Troca de pneus', 400.00, FALSE);

-- manutencoes_servicos
INSERT INTO manutencoes_servicos (id_manutencao, id_servico, valor_item) VALUES
(1, 1, 150.00),
(2, 2, 300.00),
(3, 3, 100.00),
(4, 4, 200.00),
(5, 5, 400.00);

-- pedidos_locacao
INSERT INTO pedidos_locacao (id_atendente, id_cliente, id_seguro, placa, id_saida, id_devolucao, devolucao_esperada, forma_de_pagamento, finalizado, valor_total) VALUES
(1, 1, 1, 'AAA1234', NULL, NULL, '2025-06-01', 1, FALSE, 300.00),
(2, 2, 2, 'BBB1234', NULL, NULL, '2025-06-02', 2, FALSE, 400.00),
(3, 3, 3, 'CCC1234', NULL, NULL, '2025-06-03', 3, FALSE, 350.00),
(4, 4, 4, 'DDD1234', NULL, NULL, '2025-06-04', 4, FALSE, 500.00),
(5, 5, 5, 'EEE1234', NULL, NULL, '2025-06-05', 5, FALSE, 280.00);

-- devolucoes_veiculos
INSERT INTO devolucoes_veiculos (id_pedido, id_assistente, instante_devolucao, placa, km_chegada, id_manutencao) VALUES
(1, 2, CURRENT_TIMESTAMP(), 'AAA1234', 20500, 1),
(2, 3, CURRENT_TIMESTAMP(), 'BBB1234', 30500, 2),
(3, 4, CURRENT_TIMESTAMP(), 'CCC1234', 15500, 3),
(4, 5, CURRENT_TIMESTAMP(), 'DDD1234', 10500, 4),
(5, 1, CURRENT_TIMESTAMP(), 'EEE1234', 51000, 5);

-- saidas_veiculos
INSERT INTO saidas_veiculos (id_pedido, id_assistente, placa, instante_saida, km_saida) VALUES
(1, 1, 'AAA1234', CURRENT_TIMESTAMP(), 20000),
(2, 2, 'BBB1234', CURRENT_TIMESTAMP(), 30000),
(3, 3, 'CCC1234', CURRENT_TIMESTAMP(), 15000),
(4, 4, 'DDD1234', CURRENT_TIMESTAMP(), 10000),
(5, 5, 'EEE1234', CURRENT_TIMESTAMP(), 50000);

-- Atualiza os pedidos com os IDs de saída e devolução
UPDATE pedidos_locacao SET id_saida = 1, id_devolucao = 1 WHERE id_pedido = 1;
UPDATE pedidos_locacao SET id_saida = 2, id_devolucao = 2 WHERE id_pedido = 2;
UPDATE pedidos_locacao SET id_saida = 3, id_devolucao = 3 WHERE id_pedido = 3;
UPDATE pedidos_locacao SET id_saida = 4, id_devolucao = 4 WHERE id_pedido = 4;
UPDATE pedidos_locacao SET id_saida = 5, id_devolucao = 5 WHERE id_pedido = 5;
