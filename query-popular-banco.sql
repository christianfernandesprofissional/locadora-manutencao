USE db_locadora;

-- TABELAS AUXILIARES
INSERT INTO categorias_veiculos (descricao) VALUES
('Econômico'), ('Sedan'), ('SUV'), ('Caminhonete'), ('Esportivo');

INSERT INTO tipos_seguro (nome, descricao, taxa) VALUES
('Básico', 'Cobertura contra terceiros', 100.00),
('Intermediário', 'Cobertura contra terceiros e roubo', 200.00),
('Total', 'Cobertura total do veículo', 350.00),
('Premium', 'Cobertura total com assistência 24h', 500.00),
('Nenhum', 'Sem seguro incluso', 0.00);

INSERT INTO meios_de_pagamento (descricao) VALUES
('Dinheiro'), ('Cartão de Crédito'), ('Cartão de Débito'), ('Pix'), ('Boleto');

INSERT INTO usuarios (nome, email, senha, tipo_usuario, ativo) VALUES
('João Silva', 'joao@email.com', 'senha123', 1, TRUE),
('Maria Souza', 'maria@email.com', 'senha123', 2, TRUE),
('Carlos Lima', 'carlos@email.com', 'senha123', 1, TRUE),
('Ana Paula', 'ana@email.com', 'senha123', 2, TRUE),
('Rafael Costa', 'rafael@email.com', 'senha123', 2, TRUE);

INSERT INTO clientes (cpf, nome, email, cep, rua, numero, complemento, bairro, cidade, estado, dataNascimento, telefone, ativo) VALUES
('12345678900', 'Pedro Gomes', 'pedro@email.com', '12345678', 'Rua A', '100', NULL, 'Centro', 'São Paulo', 'SP', '1990-01-01', '11999990000', TRUE),
('98765432100', 'Laura Mendes', 'laura@email.com', '87654321', 'Rua B', '200', 'Ap 202', 'Jardins', 'Campinas', 'SP', '1985-05-20', '11988887777', TRUE),
('45678912300', 'Bruno Alves', 'bruno@email.com', '11112222', 'Rua C', '300', NULL, 'Moema', 'São Paulo', 'SP', '1992-09-15', '11977776666', TRUE),
('32165498700', 'Juliana Rocha', 'juliana@email.com', '33334444', 'Rua D', '400', 'Casa 1', 'Bela Vista', 'Santos', 'SP', '1988-11-10', '11966665555', TRUE),
('78912345600', 'Fernanda Lima', 'fernanda@email.com', '55556666', 'Rua E', '500', NULL, 'Vila Mariana', 'São Paulo', 'SP', '1995-07-07', '11955554444', TRUE);

-- TABELA VEÍCULOS
INSERT INTO veiculos (placa, marca, cor, ano, chassi, modelo, quilometragem, id_categoria, preco_base) VALUES
('AAA1A11', 'Fiat', 'Branco', 2020, '9BWZZZ377VT004251', 'Uno', 50000, 1, 100.00),
('BBB2B22', 'Chevrolet', 'Preto', 2021, '9BWZZZ377VT004252', 'Onix', 30000, 2, 120.00),
('CCC3C33', 'Toyota', 'Prata', 2022, '9BWZZZ377VT004253', 'Corolla', 20000, 2, 180.00),
('DDD4D44', 'Ford', 'Azul', 2021, '9BWZZZ377VT004254', 'EcoSport', 40000, 3, 160.00),
('EEE5E55', 'Honda', 'Vermelho', 2023, '9BWZZZ377VT004255', 'Civic', 10000, 5, 220.00);

-- MANUTENÇÕES
INSERT INTO manutencoes (placa, descricao, finalizado, instante_chegada, instante_saida, total) VALUES
('AAA1A11', 'Troca de óleo e filtro', TRUE, '2025-01-10 08:00:00', '2025-01-10 10:00:00', 150.00),
('BBB2B22', 'Revisão completa', TRUE, '2025-02-15 09:00:00', '2025-02-15 17:00:00', 600.00),
('CCC3C33', 'Alinhamento e balanceamento', TRUE, '2025-03-10 08:30:00', '2025-03-10 09:30:00', 200.00),
('DDD4D44', 'Troca de pastilhas de freio', TRUE, '2025-04-05 10:00:00', '2025-04-05 12:00:00', 300.00),
('EEE5E55', 'Reparo de lanternagem', FALSE, '2025-05-01 14:00:00', NULL, NULL);

-- SERVIÇOS
INSERT INTO servicos (descricao, preco, isComum) VALUES
('Troca de óleo', 100.00, TRUE),
('Revisão completa', 500.00, TRUE),
('Alinhamento', 80.00, TRUE),
('Troca de pastilhas', 200.00, FALSE),
('Lanternagem', 400.00, FALSE);

-- MANUTENÇÕES_SERVIÇOS
INSERT INTO manutencoes_servicos (id_manutencao, id_servico, valor_item) VALUES
(1, 1, 100.00),
(2, 2, 500.00),
(3, 3, 80.00),
(4, 4, 200.00),
(5, 5, 400.00);

-- PEDIDOS LOCAÇÃO
INSERT INTO pedidos_locacao (id_atendente, id_cliente, id_seguro, placa, forma_de_pagamento, devolucao_esperada, finalizado, valor_total) VALUES
(1, 1, 1, 'AAA1A11', 1, '2025-06-01', FALSE, 150.00),
(1, 2, 2, 'BBB2B22', 2, '2025-06-02', FALSE, 220.00),
(3, 3, 3, 'CCC3C33', 3, '2025-06-03', FALSE, 300.00),
(4, 4, 4, 'DDD4D44', 4, '2025-06-04', FALSE, 320.00),
(5, 5, 5, 'EEE5E55', 5, '2025-06-05', FALSE, 220.00);

-- SAÍDAS DE VEÍCULOS
INSERT INTO saidas_veiculos (id_pedido, id_assistente, placa, instante_saida, km_saida) VALUES
(1, 2, 'AAA1A11', '2025-05-25 10:00:00', 50000),
(2, 4, 'BBB2B22', '2025-05-25 11:00:00', 30000),
(3, 5, 'CCC3C33', '2025-05-25 12:00:00', 20000),
(4, 2, 'DDD4D44', '2025-05-25 13:00:00', 40000),
(5, 4, 'EEE5E55', '2025-05-25 14:00:00', 10000);

-- DEVOLUÇÕES DE VEÍCULOS
INSERT INTO devolucoes_veiculos (id_pedido, id_assistente, instante_devolucao, placa, km_chegada, id_manutencao) VALUES
(1, 2, '2025-05-26 10:00:00', 'AAA1A11', 50100, 1),
(2, 4, '2025-05-26 11:00:00', 'BBB2B22', 30200, 2),
(3, 5, '2025-05-26 12:00:00', 'CCC3C33', 20200, 3),
(4, 2, '2025-05-26 13:00:00', 'DDD4D44', 40100, 4),
(5, 4, '2025-05-26 14:00:00', 'EEE5E55', 10050, 5);

-- ATUALIZAR PEDIDOS COM ID_SAIDA E ID_DEVOLUCAO
UPDATE pedidos_locacao SET id_saida = 1, id_devolucao = 1 WHERE id_pedido = 1;
UPDATE pedidos_locacao SET id_saida = 2, id_devolucao = 2 WHERE id_pedido = 2;
UPDATE pedidos_locacao SET id_saida = 3, id_devolucao = 3 WHERE id_pedido = 3;
UPDATE pedidos_locacao SET id_saida = 4, id_devolucao = 4 WHERE id_pedido = 4;
UPDATE pedidos_locacao SET id_saida = 5, id_devolucao = 5 WHERE id_pedido = 5;
