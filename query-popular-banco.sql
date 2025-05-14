USE db_locadora;

-- CATEGORIAS DE VEÍCULOS
INSERT INTO categorias_veiculos (descricao) VALUES 
('SUV'), 
('Sedan'), 
('Hatch'),
('Pickup'),
('Minivan');

-- TIPOS DE SEGURO
INSERT INTO tipos_seguro (nome, descricao, taxa) VALUES 
('Básico', 'Cobertura contra terceiros.', 100.00),
('Completo', 'Cobertura total e assistência 24h.', 250.00),
('Roubo e Incêndio', 'Cobertura contra roubo e incêndio.', 150.00),
('Premium', 'Cobertura total com carro reserva.', 300.00),
('Sem Cobertura', 'Sem seguro incluso.', 0.00);

-- MEIOS DE PAGAMENTO
INSERT INTO meios_de_pagamento (descricao) VALUES 
('Cartão de Crédito'), 
('Débito'), 
('Dinheiro'),
('PIX'),
('Boleto Bancário');

-- USUÁRIOS
INSERT INTO usuarios (nome, email, senha, tipo_usuario) VALUES 
('João Atendente', 'joao@locadora.com', 'senha123', 1),
('Maria Assistente', 'maria@locadora.com', 'senha123', 2),
('Pedro Atendente', 'pedro@locadora.com', 'senha123', 1),
('Ana Assistente', 'ana@locadora.com', 'senha123', 2),
('Carlos Atendente', 'carlos@locadora.com', 'senha123', 1);

-- CLIENTES
INSERT INTO clientes (cpf, nome, email, cep, rua, numero, complemento, bairro, cidade, estado, dataNascimento, telefone) VALUES 
('12345678900', 'Carlos Silva', 'carlos@gmail.com', '01001000', 'Rua A', '100', NULL, 'Centro', 'São Paulo', 'SP', '1990-05-10', '(11)91234-5678'),
('23456789011', 'Fernanda Costa', 'fernanda@gmail.com', '01002000', 'Rua B', '200', NULL, 'Vila Mariana', 'São Paulo', 'SP', '1988-06-15', '(11)92345-6789'),
('34567890122', 'Lucas Almeida', 'lucas@gmail.com', '01003000', 'Rua C', '300', NULL, 'Moema', 'São Paulo', 'SP', '1992-07-20', '(11)93456-7890'),
('45678901233', 'Juliana Pereira', 'juliana@gmail.com', '01004000', 'Rua D', '400', NULL, 'Bela Vista', 'São Paulo', 'SP', '1995-08-25', '(11)94567-8901'),
('56789012344', 'Marcos Rocha', 'marcos@gmail.com', '01005000', 'Rua E', '500', NULL, 'Liberdade', 'São Paulo', 'SP', '1985-09-30', '(11)95678-9012');

-- VEÍCULOS
INSERT INTO veiculos (placa, marca, cor, ano, chassi, modelo, quilometragem, id_categoria, preco_base) VALUES 
('ABC1234', 'Toyota', 'Preto', 2022, '9BWZZZ377VT004251', 'Corolla', 15000, 2, 150.00),
('XYZ5678', 'Ford', 'Branco', 2021, '9BWZZZ377VT004252', 'EcoSport', 23000, 1, 200.00),
('DEF4321', 'Chevrolet', 'Cinza', 2020, '9BWZZZ377VT004253', 'Onix', 18000, 3, 120.00),
('GHI8765', 'Fiat', 'Vermelho', 2019, '9BWZZZ377VT004254', 'Toro', 27000, 4, 180.00),
('JKL0987', 'Honda', 'Azul', 2023, '9BWZZZ377VT004255', 'Fit', 8000, 3, 140.00);

-- MANUTENÇÕES
INSERT INTO manutencoes (placa, descricao, finalizado, instante_chegada, instante_saida, total) VALUES 
('ABC1234', 'Troca de óleo', TRUE, '2025-05-01 08:00:00', '2025-05-01 10:00:00', 250.00),
('XYZ5678', 'Revisão de freios', TRUE, '2025-05-02 09:00:00', '2025-05-02 11:00:00', 300.00),
('DEF4321', 'Alinhamento', TRUE, '2025-05-03 10:00:00', '2025-05-03 11:30:00', 150.00),
('GHI8765', 'Revisão geral', TRUE, '2025-05-04 08:30:00', '2025-05-04 12:00:00', 500.00),
('JKL0987', 'Troca de pneus', TRUE, '2025-05-05 14:00:00', '2025-05-05 16:00:00', 600.00);

-- SERVIÇOS
INSERT INTO servicos (descricao, preco, isComum) VALUES 
('Troca de óleo', 120.00, TRUE),
('Alinhamento e balanceamento', 100.00, TRUE),
('Revisão elétrica', 150.00, FALSE),
('Troca de pneus', 250.00, FALSE),
('Revisão completa', 400.00, FALSE);

-- MANUTENÇÕES_SERVIÇOS
INSERT INTO manutencoes_servicos (id_manutencao, id_servico, valor_item) VALUES 
(1, 1, 120.00),
(2, 2, 100.00),
(3, 2, 100.00),
(4, 5, 400.00),
(5, 4, 250.00);

-- PEDIDOS DE LOCAÇÃO
INSERT INTO pedidos_locacao (id_atendente, id_cliente, id_seguro, placa, devolucao_esperada, forma_de_pagamento, finalizado, valor_total) VALUES 
(1, 1, 1, 'ABC1234', '2025-05-15', 1, FALSE, 450.00),
(3, 2, 2, 'XYZ5678', '2025-05-16', 2, FALSE, 500.00),
(1, 3, 3, 'DEF4321', '2025-05-17', 3, FALSE, 300.00),
(5, 4, 4, 'GHI8765', '2025-05-18', 4, FALSE, 600.00),
(3, 5, 5, 'JKL0987', '2025-05-19', 5, FALSE, 350.00);

-- SAÍDAS DE VEÍCULOS
INSERT INTO saidas_veiculos (id_pedido, id_assistente, placa, instante_saida, km_saida) VALUES 
(1, 2, 'ABC1234', '2025-05-10 09:00:00', 15000),
(2, 4, 'XYZ5678', '2025-05-11 10:00:00', 23000),
(3, 2, 'DEF4321', '2025-05-12 11:00:00', 18000),
(4, 4, 'GHI8765', '2025-05-13 12:00:00', 27000),
(5, 2, 'JKL0987', '2025-05-14 13:00:00', 8000);

-- DEVOLUÇÕES DE VEÍCULOS
INSERT INTO devolucoes_veiculos (id_pedido, id_assistente, instante_devolucao, placa, km_chegada, id_manutencao) VALUES 
(1, 2, '2025-05-14 18:00:00', 'ABC1234', 15300, 1),
(2, 4, '2025-05-15 18:00:00', 'XYZ5678', 23400, 2),
(3, 2, '2025-05-16 18:00:00', 'DEF4321', 18350, 3),
(4, 4, '2025-05-17 18:00:00', 'GHI8765', 27500, 4),
(5, 2, '2025-05-18 18:00:00', 'JKL0987', 8400, 5);

-- ATUALIZAÇÃO DOS IDs EM PEDIDOS
UPDATE pedidos_locacao SET id_saida = 1, id_devolucao = 1 WHERE id_pedido = 1;
UPDATE pedidos_locacao SET id_saida = 2, id_devolucao = 2 WHERE id_pedido = 2;
UPDATE pedidos_locacao SET id_saida = 3, id_devolucao = 3 WHERE id_pedido = 3;
UPDATE pedidos_locacao SET id_saida = 4, id_devolucao = 4 WHERE id_pedido = 4;
UPDATE pedidos_locacao SET id_saida = 5, id_devolucao = 5 WHERE id_pedido = 5;
