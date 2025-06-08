-- Usar o banco de dados correto
USE db_locadora;

-- Populando tabelas auxiliares (sem dependências)

-- 1. categorias_veiculos
INSERT INTO categorias_veiculos (descricao) VALUES
('Econômico'),
('SUV'),
('Luxo'),
('Utilitário'),
('Esportivo');

-- 2. tipos_seguro
INSERT INTO tipos_seguro (nome, descricao, taxa) VALUES
('Seguro Básico', 'Cobre danos a terceiros e roubo/furto.', 150.00),
('Seguro Intermediário', 'Cobre danos a terceiros, roubo/furto e danos parciais ao veículo.', 250.00),
('Seguro Completo', 'Cobertura total, incluindo danos, roubo, terceiros e carro reserva.', 400.00),
('Seguro Premium', 'Cobertura completa com assistência VIP 24h e sem franquia.', 600.00),
('Seguro Contra Terceiros', 'Cobertura exclusiva para danos materiais e corporais causados a outras pessoas.', 100.00);

-- 3. meios_de_pagamento
INSERT INTO meios_de_pagamento (descricao) VALUES
('Cartão de Crédito'),
('Cartão de Débito'),
('PIX'),
('Dinheiro'),
('Transferência Bancária');

-- 4. usuarios (ex: 1=Atendente, 2=Gerente)
INSERT INTO usuarios (nome, email, senha, tipo_usuario) VALUES
('Carlos Silva', 'carlos.silva@locadora.com', 'senha123', 1),
('Ana Pereira', 'ana.pereira@locadora.com', 'senha123', 1),
('Roberto Souza', 'roberto.souza@locadora.com', 'senha123', 2),
('Mariana Costa', 'mariana.costa@locadora.com', 'senha123', 1),
('Lucas Martins', 'lucas.martins@locadora.com', 'senha123', 2);

-- 5. clientes
INSERT INTO clientes (cpf, nome, email, cep, rua, numero, bairro, cidade, estado, dataNascimento, telefone) VALUES
('11122233344', 'João da Silva', 'joao.silva@email.com', '01001000', 'Praça da Sé', '100', 'Sé', 'São Paulo', 'SP', '1990-05-15', '11987654321'),
('22233344455', 'Maria Oliveira', 'maria.oliveira@email.com', '20040030', 'Rua da Assembleia', '50', 'Centro', 'Rio de Janeiro', 'RJ', '1985-11-20', '21912345678'),
('33344455566', 'Pedro Santos', 'pedro.santos@email.com', '70070100', 'Praça dos Três Poderes', '1', 'Zona Cívico-Administrativa', 'Brasília', 'DF', '1992-02-10', '61988776655'),
('44455566677', 'Fernanda Lima', 'fernanda.lima@email.com', '40020000', 'Avenida Sete de Setembro', '250', 'Centro', 'Salvador', 'BA', '1998-07-30', '71999887766'),
('55566677788', 'Guilherme Mendes', 'guilherme.mendes@email.com', '30130005', 'Avenida Afonso Pena', '1200', 'Centro', 'Belo Horizonte', 'MG', '1988-12-01', '31977665544');

-- 6. servicos
INSERT INTO servicos (descricao, preco, isComum) VALUES
('Troca de óleo e filtro', 150.00, TRUE),
('Alinhamento e Balanceamento', 120.00, TRUE),
('Revisão Geral (freios, suspensão)', 450.00, FALSE),
('Troca de Pneus (unidade)', 350.00, FALSE),
('Lavagem Completa com Cera', 80.00, TRUE);


-- Populando tabelas com dependências

-- 7. veiculos (depende de categorias_veiculos)
INSERT INTO veiculos (placa, marca, cor, ano, chassi, modelo, quilometragem, id_categoria, preco_base, situacao) VALUES
('ABC1D23', 'Fiat', 'Branco', 2022, '9BWZSSAS3N2P51001', 'Mobi', 15000, 1, 90.00, 1),
('DEF4E56', 'Jeep', 'Preto', 2023, '9BWZSSAS3N2P51002', 'Renegade', 5000, 2, 180.00, 1),
('GHI7F89', 'BMW', 'Azul', 2023, '9BWZSSAS3N2P51003', 'Série 3', 8000, 3, 450.00, 1),
('JKL0M12', 'Fiat', 'Prata', 2021, '9BWZSSAS3N2P51004', 'Fiorino', 45000, 4, 150.00, 1),
('MNO3P45', 'Ford', 'Vermelho', 2022, '9BWZSSAS3N2P51005', 'Mustang', 12000, 5, 700.00, 1);


-- Populando tabelas de manutenção

-- 8. manutencoes (depende de veiculos)
INSERT INTO manutencoes (placa, descricao, finalizado, instante_chegada, instante_saida, total) VALUES
('ABC1D23', 'Manutenção preventiva de 15.000km.', TRUE, '2023-10-01 08:00:00', '2023-10-01 17:00:00', 270.00),
('DEF4E56', 'Verificar barulho na suspensão dianteira.', FALSE, '2023-10-05 09:30:00', NULL, NULL),
('GHI7F89', 'Ar condicionado não está gelando.', TRUE, '2023-09-20 10:00:00', '2023-09-21 11:00:00', 450.00),
('JKL0M12', 'Revisão periódica de 45.000km.', FALSE, '2023-10-10 14:00:00', NULL, NULL),
('MNO3P45', 'Lavagem e polimento.', TRUE, '2023-10-11 15:00:00', '2023-10-11 18:00:00', 80.00);

-- 9. manutencoes_servicos (tabela de ligação, depende de manutencoes e servicos)
INSERT INTO manutencoes_servicos (id_manutencao, id_servico, valor_item) VALUES
(1, 1, 150.00), -- Manutenção 1 usou o serviço 1
(1, 2, 120.00), -- Manutenção 1 também usou o serviço 2
(3, 3, 450.00), -- Manutenção 3 usou o serviço 3
(5, 5, 80.00),  -- Manutenção 5 usou o serviço 5
(2, 2, 120.00); -- Manutenção 2 (ainda não finalizada) está fazendo o serviço 2

-- Populando tabelas de locação (ETAPA 1: Criar Pedidos sem Saída/Devolução)
-- id_saida e id_devolucao serão NULL inicialmente

-- 10. pedidos_locacao (depende de usuarios, clientes, tipos_seguro, veiculos, meios_de_pagamento)
INSERT INTO pedidos_locacao (id_atendente, id_cliente, id_seguro, placa, devolucao_esperada, forma_de_pagamento, valor_total) VALUES
(1, 1, 1, 'ABC1D23', '2023-10-20', 1, 550.00), -- Atendente Carlos, Cliente João, Seguro Básico, Veículo Mobi, CC
(2, 2, 3, 'GHI7F89', '2023-10-25', 3, 2500.00),-- Atendente Ana, Cliente Maria, Seguro Completo, Veículo BMW, PIX
(4, 3, 2, 'DEF4E56', '2023-11-05', 2, 1270.00),-- Atendente Mariana, Cliente Pedro, Seguro Interm., Veículo Renegade, Débito
(1, 4, 1, 'JKL0M12', '2023-11-10', 4, 900.00), -- Atendente Carlos, Cliente Fernanda, Seguro Básico, Veículo Fiorino, Dinheiro
(5, 5, 4, 'MNO3P45', '2023-11-15', 1, 4100.00);-- Atendente Lucas, Cliente Guilherme, Seguro Premium, Veículo Mustang, CC


-- Populando tabelas de locação (ETAPA 2: Criar Saídas e Devoluções associadas aos Pedidos)

-- 11. saidas_veiculos (depende de pedidos_locacao, usuarios, veiculos)
INSERT INTO saidas_veiculos (id_pedido, id_assistente, placa, instante_saida, km_saida) VALUES
(1, 1, 'ABC1D23', '2023-10-15 10:00:00', 15270), -- Associado ao pedido 1
(2, 2, 'GHI7F89', '2023-10-20 11:30:00', 8450),  -- Associado ao pedido 2
(3, 4, 'DEF4E56', '2023-10-30 09:00:00', 5300),  -- Associado ao pedido 3
(4, 1, 'JKL0M12', '2023-11-01 14:20:00', 45800), -- Associado ao pedido 4
(5, 5, 'MNO3P45', '2023-11-05 18:00:00', 12100); -- Associado ao pedido 5

-- 12. devolucoes_veiculos (depende de pedidos_locacao, usuarios, veiculos, manutencoes)
-- Note que id_manutencao pode ser nulo se o carro não precisar de manutenção
INSERT INTO devolucoes_veiculos (id_pedido, id_assistente, instante_devolucao, placa, km_chegada, id_manutencao) VALUES
(1, 2, '2023-10-20 09:15:00', 'ABC1D23', 15800, NULL),       -- Pedido 1 devolvido pela Ana
(2, 1, '2023-10-25 10:00:00', 'GHI7F89', 9200, 3),          -- Pedido 2 devolvido pelo Carlos, gerou manutenção 3
(3, 4, '2023-11-05 11:00:00', 'DEF4E56', 5950, 2),          -- Pedido 3 devolvido pela Mariana, gerou manutenção 2
(4, 1, '2023-11-10 13:00:00', 'JKL0M12', 46500, NULL),       -- Pedido 4 devolvido pelo Carlos
(5, 5, '2023-11-15 17:45:00', 'MNO3P45', 12980, 5);          -- Pedido 5 devolvido pelo Lucas, gerou manutenção 5


-- Populando tabelas de locação (ETAPA 3: Atualizar Pedidos com os IDs de Saída e Devolução)
-- Esta etapa é crucial por causa da referência circular.

UPDATE pedidos_locacao SET id_saida = 1, id_devolucao = 1, finalizado = TRUE WHERE id_pedido = 1;
UPDATE pedidos_locacao SET id_saida = 2, id_devolucao = 2, finalizado = TRUE WHERE id_pedido = 2;
UPDATE pedidos_locacao SET id_saida = 3, id_devolucao = 3, finalizado = TRUE WHERE id_pedido = 3;
UPDATE pedidos_locacao SET id_saida = 4, id_devolucao = 4, finalizado = TRUE WHERE id_pedido = 4;
UPDATE pedidos_locacao SET id_saida = 5, id_devolucao = 5, finalizado = TRUE WHERE id_pedido = 5;