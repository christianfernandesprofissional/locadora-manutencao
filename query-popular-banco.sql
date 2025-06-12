USE db_locadora;

-- Desabilitar checagem de FK temporariamente pode ser útil para cargas em lote complexas,
-- mas tentaremos evitar se a ordem dos inserts e updates for correta.
-- SET FOREIGN_KEY_CHECKS=0;

-- TABELAS AUXILIARES (Mesmas da query anterior)

-- categorias_veiculos
INSERT INTO categorias_veiculos (descricao) VALUES
('Hatch Compacto'), ('Sedan Compacto'), ('Sedan Médio'), ('SUV Compacto'), ('SUV Médio'),
('Picape Pequena'), ('Picape Média'), ('Minivan'), ('Esportivo'), ('Luxo'),
('Van de Carga'), ('Van de Passageiros'), ('Motocicleta Urbana'), ('Motocicleta Esportiva'), ('Motocicleta Custom'),
('Econômico'), ('Intermediário'), ('Executivo'), ('Utilitário'), ('Premium');

-- tipos_seguro
INSERT INTO tipos_seguro (nome, descricao, taxa) VALUES
('Básico', 'Cobre danos parciais e roubo/furto com franquia.', 50.00),
('Completo', 'Cobre danos parciais, roubo/furto e terceiros, com franquia reduzida.', 80.00),
('Premium', 'Cobertura total, sem franquia, e assistência 24h completa.', 120.00),
('Terceiros', 'Cobre apenas danos causados a terceiros.', 30.00),
('Roubo e Furto', 'Cobre apenas situações de roubo ou furto do veículo.', 40.00),
('Perda Total', 'Cobre apenas perda total do veículo por colisão ou incêndio.', 60.00),
('Vidros e Faróis', 'Cobertura específica para vidros, faróis, lanternas e retrovisores.', 25.00),
('Carro Reserva', 'Disponibiliza carro reserva em caso de sinistro.', 35.00),
('Acidentes Pessoais', 'Cobre acidentes pessoais de passageiros.', 45.00),
('Danos Morais', 'Cobertura para danos morais em caso de acidente com terceiros.', 55.00),
('APP - Morte', 'Acidentes Pessoais Passageiros - Cobertura por Morte.', 20.00),
('APP - Invalidez', 'Acidentes Pessoais Passageiros - Cobertura por Invalidez.', 22.00),
('Assistência Plus', 'Assistência 24h com guincho ilimitado e chaveiro.', 18.00),
('Seguro Franquia Zero', 'Isenção de pagamento da franquia em caso de sinistro.', 90.00),
('Seguro Passageiro Vip', 'Cobertura VIP para passageiros incluindo despesas médicas.', 70.00),
('Proteção para Bagagem', 'Cobre perda ou dano à bagagem no interior do veículo.', 15.00),
('Extensão Perímetro', 'Extensão da cobertura para países do Mercosul.', 75.00),
('Despesas Extras', 'Reembolso de despesas extras em caso de sinistro longe da residência.', 33.00),
('Higienização Especial', 'Cobertura para higienização especial pós-sinistro.', 28.00),
('Seguro Equipamentos', 'Proteção para equipamentos instalados no veículo (som, GPS).', 42.00);

-- meios_de_pagamento
INSERT INTO meios_de_pagamento (descricao) VALUES
('Cartão de Crédito'), ('Cartão de Débito'), ('PIX'), ('Dinheiro'), ('Transferência Bancária'),
('Boleto Bancário'), ('Vale Presente'), ('Débito Automático'), ('Cheque'), ('PayPal'),
('PagSeguro'), ('Mercado Pago'), ('Criptomoeda'), ('Carteira Digital A'), ('Carteira Digital B'),
('Voucher Empresa'), ('Permuta'), ('Financiamento Loja'), ('Consórcio'), ('Crediário Próprio');

-- usuarios
INSERT INTO usuarios (nome, email, senha, tipo_usuario, ativo) VALUES
('Admin Geral', 'admin@locadora.com', 'senha123', 1, TRUE),                            -- ID 1
('Ana Silva Atendente', 'ana.silva@locadora.com', 'senha123', 2, TRUE),                 -- ID 2
('Bruno Costa Atendente', 'bruno.costa@locadora.com', 'senha123', 2, TRUE),            -- ID 3
('Carlos Lima Gerente', 'carlos.lima@locadora.com', 'senha123', 1, TRUE),               -- ID 4
('Daniela Souza Atendente', 'daniela.souza@locadora.com', 'senha123', 2, TRUE),        -- ID 5
('Eduardo Pereira Assistente', 'eduardo.pereira@locadora.com', 'senha123', 2, TRUE),    -- ID 6
('Fernanda Alves Atendente', 'fernanda.alves@locadora.com', 'senha123', 2, FALSE),      -- ID 7
('Gustavo Rocha Supervisor', 'gustavo.rocha@locadora.com', 'senha123', 1, TRUE),        -- ID 8
('Helena Santos Atendente', 'helena.santos@locadora.com', 'senha123', 2, TRUE),         -- ID 9
('Igor Martins Assistente', 'igor.martins@locadora.com', 'senha123', 2, TRUE),         -- ID 10
('Julia Oliveira Atendente', 'julia.oliveira@locadora.com', 'senha123', 2, TRUE),      -- ID 11
('Kevin Borges', 'kevin.borges@locadora.com', 'senha123', 2, TRUE),
('Larissa Mendes', 'larissa.mendes@locadora.com', 'senha123', 2, TRUE),
('Marcos Vinicius', 'marcos.vinicius@locadora.com', 'senha123', 1, TRUE),
('Natalia Barros', 'natalia.barros@locadora.com', 'senha123', 2, TRUE),
('Otavio Nunes', 'otavio.nunes@locadora.com', 'senha123', 2, TRUE),
('Patricia Ramos', 'patricia.ramos@locadora.com', 'senha123', 2, TRUE),
('Ricardo Gomes', 'ricardo.gomes@locadora.com', 'senha123', 1, TRUE),
('Sofia Dias', 'sofia.dias@locadora.com', 'senha123', 2, TRUE),
('Thiago Ferreira', 'thiago.ferreira@locadora.com', 'senha123', 2, TRUE);                -- ID 20

-- clientes
INSERT INTO clientes (cpf, nome, email, cep, rua, numero, complemento, bairro, cidade, estado, dataNascimento, telefone, ativo) VALUES
('11122233301', 'Alice Braga', 'alice.braga@email.com', '01001000', 'Rua Principal', '100', 'Apto 101', 'Centro', 'São Paulo', 'SP', '1990-05-15', '(11)99999-0001', TRUE),
('11122233302', 'Bernardo Campos', 'bernardo.campos@email.com', '20000010', 'Avenida Central', '200', NULL, 'Copacabana', 'Rio de Janeiro', 'RJ', '1985-11-20', '(21)98888-0002', TRUE),
('11122233303', 'Clara Diniz', 'clara.diniz@email.com', '30130001', 'Rua dos Aimorés', '300', 'Bloco B', 'Funcionários', 'Belo Horizonte', 'MG', '1992-02-10', '(31)97777-0003', TRUE),
('11122233304', 'Davi Escobar', 'davi.escobar@email.com', '40015010', 'Largo do Pelourinho', '45', NULL, 'Pelourinho', 'Salvador', 'BA', '1978-07-01', '(71)96666-0004', FALSE),
('11122233305', 'Elisa Fontes', 'elisa.fontes@email.com', '50020030', 'Rua da Aurora', '500', 'Sala 3', 'Boa Vista', 'Recife', 'PE', '2000-12-25', '(81)95555-0005', TRUE),
('11122233306', 'Felipe Guerra', 'felipe.guerra@email.com', '60170002', 'Avenida Beira Mar', '1234', 'Apto 202', 'Meireles', 'Fortaleza', 'CE', '1995-09-30', '(85)94444-0006', TRUE),
('11122233307', 'Gabriela Hoffmann', 'gabriela.hoffmann@email.com', '70340000', 'SQS 308 Bloco K', '10', 'Apto 404', 'Asa Sul', 'Brasília', 'DF', '1988-03-12', '(61)93333-0007', TRUE),
('11122233308', 'Heitor Iglésias', 'heitor.iglesias@email.com', '80010010', 'Rua XV de Novembro', '567', NULL, 'Centro', 'Curitiba', 'PR', '1991-08-05', '(41)92222-0008', TRUE),
('11122233309', 'Isabela Justo', 'isabela.justo@email.com', '90010150', 'Rua dos Andradas', '1000', 'Loja 10', 'Centro Histórico', 'Porto Alegre', 'RS', '1975-01-20', '(51)91111-0009', TRUE),
('11122233310', 'João Kleber', 'joao.kleber@email.com', '69057002', 'Avenida Djalma Batista', '2010', 'Condomínio Sol', 'Chapada', 'Manaus', 'AM', '1999-06-18', '(92)90000-0010', TRUE),
('11122233311', 'Laura Macedo', 'laura.macedo@email.com', '13010041', 'Rua Conceição', '150', NULL, 'Centro', 'Campinas', 'SP', '1993-04-22', '(19)99888-0011', TRUE),
('11122233312', 'Miguel Nogueira', 'miguel.nogueira@email.com', '29010002', 'Avenida Jerônimo Monteiro', '250', 'Apto 303', 'Centro', 'Vitória', 'ES', '1980-10-03', '(27)99777-0012', TRUE),
('11122233313', 'Natália Oliveira', 'natalia.oliveira@email.com', '74000010', 'Avenida Goiás', '350', NULL, 'Setor Central', 'Goiânia', 'GO', '1997-12-08', '(62)99666-0013', TRUE),
('11122233314', 'Otávio Pires', 'otavio.pires@email.com', '79002171', 'Rua 14 de Julho', '450', 'Sala 5', 'Centro', 'Campo Grande', 'MS', '1986-07-27', '(67)99555-0014', FALSE),
('11122233315', 'Patrícia Queiroz', 'patricia.queiroz@email.com', '65000100', 'Rua Grande', '550', 'Apto 505', 'Centro', 'São Luís', 'MA', '1994-01-14', '(98)99444-0015', TRUE),
('11122233316', 'Rafael Ribeiro', 'rafael.ribeiro@email.com', '57000200', 'Avenida da Paz', '650', NULL, 'Jaraguá', 'Maceió', 'AL', '1989-11-09', '(82)99333-0016', TRUE),
('11122233317', 'Sofia Souza', 'sofia.souza@email.com', '88010001', 'Rua Felipe Schmidt', '750', 'Bloco C', 'Centro', 'Florianópolis', 'SC', '1996-03-29', '(48)99222-0017', TRUE),
('11122233318', 'Thiago Teixeira', 'thiago.teixeira@email.com', '49000001', 'Avenida Ivo do Prado', '850', 'Apto 606', 'Centro', 'Aracaju', 'SE', '1983-09-17', '(79)99111-0018', TRUE),
('11122233319', 'Valentina Vieira', 'valentina.vieira@email.com', '58000001', 'Avenida Epitácio Pessoa', '950', NULL, 'Tambauzinho', 'João Pessoa', 'PB', '2001-05-01', '(83)99000-0019', TRUE),
('11122233320', 'William Xavier', 'william.xavier@email.com', '68900011', 'Avenida Presidente Vargas', '1050', 'Sala 12', 'Central', 'Macapá', 'AP', '1979-02-23', '(96)98999-0020', TRUE);


-- veiculos (50 linhas)
-- situacao: 1=Disponível, 2=Alugado, 3=Manutenção
-- Definindo as situações iniciais:
-- VEC0001 a VEC0003 (3) em Manutenção (situacao=3)
-- VEC0004 a VEC0023 (20) Alugados (situacao=2) - serão usados para pedidos com Saída OK/Pendente e Saída Pendente
-- VEC0024 a VEC0050 (27) Disponíveis (situacao=1) - serão usados para pedidos Finalizados
INSERT INTO veiculos (placa, marca, cor, ano, chassi, modelo, quilometragem, id_categoria, preco_base, situacao) VALUES
-- Manutenção (3)
('VEC0001', 'Fiat', 'Branco', 2021, 'CHASSI0000000VEC01', 'Mobi', 35000, 1, 90.00, 3),
('VEC0002', 'VW', 'Prata', 2022, 'CHASSI0000000VEC02', 'Gol', 22000, 1, 95.00, 3),
('VEC0003', 'Ford', 'Preto', 2020, 'CHASSI0000000VEC03', 'Ka Sedan', 45000, 2, 110.00, 3),
-- Alugados (20)
('VEC0004', 'Chevrolet', 'Azul', 2023, 'CHASSI0000000VEC04', 'Onix', 15000, 1, 100.00, 2),
('VEC0005', 'Hyundai', 'Vermelho', 2022, 'CHASSI0000000VEC05', 'HB20', 18000, 1, 105.00, 2),
('VEC0006', 'Renault', 'Cinza', 2021, 'CHASSI0000000VEC06', 'Sandero', 28000, 1, 90.00, 2),
('VEC0007', 'Toyota', 'Prata', 2023, 'CHASSI0000000VEC07', 'Yaris Sedan', 12000, 2, 120.00, 2),
('VEC0008', 'Honda', 'Branco', 2022, 'CHASSI0000000VEC08', 'City', 16000, 2, 130.00, 2),
('VEC0009', 'Jeep', 'Verde', 2021, 'CHASSI0000000VEC09', 'Renegade', 32000, 4, 150.00, 2),
('VEC0010', 'Fiat', 'Preto', 2023, 'CHASSI0000000VEC10', 'Pulse', 10000, 4, 140.00, 2),
('VEC0011', 'VW', 'Azul', 2022, 'CHASSI0000000VEC11', 'Nivus', 19000, 4, 145.00, 2),
('VEC0012', 'Chevrolet', 'Cinza', 2021, 'CHASSI0000000VEC12', 'Tracker', 30000, 4, 155.00, 2),
('VEC0013', 'Hyundai', 'Branco', 2023, 'CHASSI0000000VEC13', 'Creta', 8000, 4, 160.00, 2),
('VEC0014', 'Fiat', 'Vermelho', 2022, 'CHASSI0000000VEC14', 'Strada', 25000, 6, 130.00, 2),
('VEC0015', 'VW', 'Prata', 2021, 'CHASSI0000000VEC15', 'Saveiro', 38000, 6, 125.00, 2),
('VEC0016', 'Toyota', 'Preto', 2023, 'CHASSI0000000VEC16', 'Hilux', 12000, 7, 250.00, 2),
('VEC0017', 'Ford', 'Azul', 2022, 'CHASSI0000000VEC17', 'Ranger', 20000, 7, 240.00, 2),
('VEC0018', 'Chevrolet', 'Branco', 2021, 'CHASSI0000000VEC18', 'S10', 42000, 7, 230.00, 2),
('VEC0019', 'Mercedes', 'Prata', 2023, 'CHASSI0000000VEC19', 'Classe C', 5000, 10, 350.00, 2),
('VEC0020', 'BMW', 'Preto', 2022, 'CHASSI0000000VEC20', 'Série 3', 9000, 10, 360.00, 2),
('VEC0021', 'Audi', 'Cinza', 2021, 'CHASSI0000000VEC21', 'A3 Sedan', 15000, 3, 200.00, 2),
('VEC0022', 'Volvo', 'Branco', 2023, 'CHASSI0000000VEC22', 'XC40', 7000, 5, 280.00, 2),
('VEC0023', 'Peugeot', 'Vermelho', 2022, 'CHASSI0000000VEC23', '208', 11000, 1, 110.00, 2),
-- Disponíveis (27)
('VEC0024', 'Citroen', 'Prata', 2021, 'CHASSI0000000VEC24', 'C3', 29000, 1, 92.00, 1),
('VEC0025', 'Kia', 'Preto', 2023, 'CHASSI0000000VEC25', 'Cerato', 6000, 3, 180.00, 1),
('VEC0026', 'Nissan', 'Branco', 2022, 'CHASSI0000000VEC26', 'Versa', 13000, 2, 125.00, 1),
('VEC0027', 'Mitsubishi', 'Cinza', 2021, 'CHASSI0000000VEC27', 'L200 Triton', 55000, 7, 220.00, 1),
('VEC0028', 'Renault', 'Azul', 2023, 'CHASSI0000000VEC28', 'Duster', 4000, 4, 135.00, 1),
('VEC0029', 'Fiat', 'Prata', 2020, 'CHASSI0000000VEC29', 'Argo', 48000, 1, 85.00, 1),
('VEC0030', 'VW', 'Branco', 2022, 'CHASSI0000000VEC30', 'Virtus', 17000, 2, 135.00, 1),
('VEC0031', 'Chevrolet', 'Preto', 2023, 'CHASSI0000000VEC31', 'Spin', 3000, 8, 170.00, 1),
('VEC0032', 'Hyundai', 'Cinza', 2021, 'CHASSI0000000VEC32', 'HB20S', 33000, 2, 115.00, 1),
('VEC0033', 'Honda', 'Vermelho', 2022, 'CHASSI0000000VEC33', 'WR-V', 14000, 4, 165.00, 1),
('VEC0034', 'Toyota', 'Azul', 2023, 'CHASSI0000000VEC34', 'Corolla Cross', 9000, 5, 210.00, 1),
('VEC0035', 'Jeep', 'Prata', 2021, 'CHASSI0000000VEC35', 'Compass', 36000, 5, 190.00, 1),
('VEC0036', 'Ford', 'Branco', 2020, 'CHASSI0000000VEC36', 'EcoSport', 52000, 4, 120.00, 1),
('VEC0037', 'Caoa Chery', 'Preto', 2023, 'CHASSI0000000VEC37', 'Tiggo 5x', 2000, 4, 150.00, 1),
('VEC0038', 'Nissan', 'Vermelho', 2022, 'CHASSI0000000VEC38', 'Kicks', 10000, 4, 140.00, 1),
('VEC0039', 'Renault', 'Cinza', 2021, 'CHASSI0000000VEC39', 'Captur', 26000, 4, 148.00, 1),
('VEC0040', 'Peugeot', 'Branco', 2023, 'CHASSI0000000VEC40', '2008', 7500, 4, 152.00, 1),
('VEC0041', 'Citroen', 'Preto', 2022, 'CHASSI0000000VEC41', 'C4 Cactus', 13500, 4, 147.00, 1),
('VEC0042', 'BMW', 'Azul', 2021, 'CHASSI0000000VEC42', 'X1', 19500, 5, 290.00, 1),
('VEC0043', 'Audi', 'Prata', 2023, 'CHASSI0000000VEC43', 'Q3', 6500, 5, 300.00, 1),
('VEC0044', 'Mercedes', 'Branco', 2022, 'CHASSI0000000VEC44', 'GLA', 11500, 5, 310.00, 1),
('VEC0045', 'Land Rover', 'Verde', 2021, 'CHASSI0000000VEC45', 'Evoque', 22500, 10, 400.00, 1),
('VEC0046', 'Fiat', 'Preto', 2023, 'CHASSI0000000VEC46', 'Toro', 8500, 7, 190.00, 1),
('VEC0047', 'VW', 'Cinza', 2022, 'CHASSI0000000VEC47', 'Amarok', 21500, 7, 260.00, 1),
('VEC0048', 'Ford', 'Vermelho', 2021, 'CHASSI0000000VEC48', 'Mustang', 10500, 9, 500.00, 1),
('VEC0049', 'Chevrolet', 'Amarelo', 2023, 'CHASSI0000000VEC49', 'Camaro', 2500, 9, 550.00, 1),
('VEC0050', 'Porsche', 'Branco', 2022, 'CHASSI0000000VEC50', 'Cayman', 7800, 9, 600.00, 1);


-- TABELAS DE MANUTENÇÃO (Mesmas da query anterior)

-- servicos
INSERT INTO servicos (descricao, preco, isComum) VALUES
('Troca de Óleo e Filtro', 150.00, TRUE), ('Alinhamento e Balanceamento', 120.00, TRUE),
('Revisão Completa (10.000km)', 450.00, TRUE), ('Troca de Pastilhas de Freio', 200.00, TRUE),
('Limpeza de Bicos Injetores', 180.00, FALSE), ('Substituição da Bateria', 350.00, TRUE),
('Reparo no Sistema de Ar Condicionado', 400.00, FALSE), ('Troca de Correia Dentada', 500.00, FALSE),
('Funilaria Leve (Pequenos Amassados)', 300.00, FALSE), ('Pintura Parcial (Retoque)', 400.00, FALSE),
('Higienização Interna Completa', 250.00, TRUE), ('Verificação Sistema Elétrico', 100.00, FALSE),
('Troca de Velas', 120.00, TRUE), ('Conserto de Pneu Furado', 50.00, TRUE),
('Diagnóstico Computadorizado', 90.00, FALSE), ('Troca de Amortecedores (par)', 600.00, FALSE),
('Limpeza do Sistema de Arrefecimento', 170.00, TRUE), ('Reparo no Motor de Partida', 320.00, FALSE),
('Troca da Embreagem', 800.00, FALSE), ('Cambagem', 80.00, TRUE);

-- manutencoes
INSERT INTO manutencoes (placa, descricao, finalizado, instante_chegada, total) VALUES
('VEC0001', 'Ruído estranho no motor, possível problema na correia.', FALSE, NOW() - INTERVAL 7 DAY, NULL),
('VEC0002', 'Sistema de freios apresentando falhas.', FALSE, NOW() - INTERVAL 3 DAY, NULL),
('VEC0003', 'Veículo não liga, possível problema elétrico ou bateria.', FALSE, NOW() - INTERVAL 1 DAY, NULL);
INSERT INTO manutencoes (placa, descricao, finalizado, instante_chegada, instante_saida, total) VALUES
('VEC0024', 'Revisão periódica dos 30.000km.', TRUE, '2023-10-01 08:00:00', '2023-10-01 17:00:00', 450.00),
('VEC0025', 'Troca de óleo e filtro de rotina.', TRUE, '2023-10-05 09:30:00', '2023-10-05 11:00:00', 150.00),
('VEC0026', 'Alinhamento e balanceamento após troca de pneus.', TRUE, '2023-10-10 14:00:00', '2023-10-10 15:30:00', 120.00),
('VEC0027', 'Reparo no ar condicionado (não gela).', TRUE, '2023-09-15 10:00:00', '2023-09-16 12:00:00', 400.00),
('VEC0028', 'Verificação de barulho na suspensão dianteira.', TRUE, '2023-09-20 11:00:00', '2023-09-20 16:00:00', 280.00),
('VEC0029', 'Troca de pastilhas de freio dianteiras.', TRUE, '2023-11-01 08:30:00', '2023-11-01 10:30:00', 200.00),
('VEC0030', 'Higienização interna e troca do filtro de cabine.', TRUE, '2023-11-05 13:00:00', '2023-11-05 17:00:00', 280.00),
('VEC0031', 'Falha no vidro elétrico porta do motorista.', TRUE, '2023-08-10 16:00:00', '2023-08-11 09:00:00', 180.00),
('VEC0032', 'Substituição da bateria (descarregando rápido).', TRUE, '2023-08-15 09:00:00', '2023-08-15 09:45:00', 350.00),
('VEC0033', 'Pequeno amassado na porta traseira direita.', TRUE, '2023-07-20 14:30:00', '2023-07-22 17:00:00', 300.00),
('VEC0034', 'Luz de injeção acesa no painel.', TRUE, '2023-07-25 08:00:00', '2023-07-25 15:00:00', 270.00),
('VEC0035', 'Manutenção preventiva - sistema de arrefecimento.', TRUE, '2023-06-10 10:00:00', '2023-06-10 12:30:00', 170.00),
('VEC0036', 'Revisão dos 50.000km, troca de correias.', TRUE, '2023-06-15 09:00:00', '2023-06-16 11:00:00', 950.00),
('VEC0037', 'Vazamento de óleo identificado no cárter.', TRUE, '2023-05-01 11:30:00', '2023-05-02 16:00:00', 420.00),
('VEC0038', 'Problema no sensor de estacionamento traseiro.', TRUE, '2023-05-10 15:00:00', '2023-05-10 17:30:00', 190.00),
('VEC0039', 'Inspeção geral pré-viagem longa.', TRUE, '2023-04-12 08:00:00', '2023-04-12 12:00:00', 220.00),
('VEC0040', 'Substituição do parabrisa trincado.', TRUE, '2023-04-18 13:30:00', '2023-04-19 10:00:00', 650.00);


-- manutencoes_servicos (Populando para algumas manutenções)
INSERT INTO manutencoes_servicos (id_manutencao, id_servico, valor_item) VALUES
(1, 12, 100.00), (1, 8, 500.00), (2, 4, 200.00), (2, 12, 100.00), (3, 6, 350.00), (3, 18, 320.00),
(4, 3, 450.00), (5, 1, 150.00), (6, 2, 120.00), (7, 7, 400.00), (8, 16, 280.00),
(9, 4, 200.00), (10, 11, 250.00), (10, 13, 30.00), (11, 12, 100.00), (11, 5, 80.00),
(12, 6, 350.00),(13, 9, 300.00),(14, 5, 180.00), (14, 15, 90.00),(15, 17, 170.00),
(16, 3, 450.00), (16, 8, 500.00),(17, 1, 150.00), (17, 12, 100.00), (17, 5, 170.00),
(18, 12, 100.00), (18, 15, 90.00),(19, 1, 150.00), (19, 2, 70.00),(20, 10, 400.00);

-- TABELAS DE LOCAÇÃO

-- Pedidos (50)
-- IDs 1-20: FINALIZADO = TRUE
-- IDs 21-35: FINALIZADO = FALSE (Saída OK, Devolução Pendente)
-- IDs 36-50: FINALIZADO = FALSE (Saída Pendente, Devolução Pendente)

-- IDs atendentes variam de 2,3,5,6,9,10,11...
-- Placas para Grupo 1 (Finalizados 1-20): VEC0024 a VEC0043
-- Placas para Grupo 2 (Saída OK, Dev Pendente 21-35): VEC0004 a VEC0018
-- Placas para Grupo 3 (Saída Pendente, Dev Pendente 36-50): VEC0019 a VEC0023 (5) + VEC0044 a VEC0050 (7) + VEC0024, VEC0025, VEC0026 (3 que serão "re-reservados") - Total 15.

INSERT INTO pedidos_locacao (id_atendente, id_cliente, id_seguro, placa, devolucao_esperada, forma_de_pagamento, finalizado, valor_total) VALUES
-- Grupo 1: Pedidos 1-20 (Finalizados Completos) - Placas VEC0024 a VEC0043
(2, 1, 1, 'VEC0024', '2023-11-10', 1, TRUE, 300.00), (3, 2, 2, 'VEC0025', '2023-11-05', 2, TRUE, 560.00),
(5, 3, 3, 'VEC0026', '2023-10-20', 1, TRUE, 750.00), (6, 4, 1, 'VEC0027', '2023-10-15', 3, TRUE, 330.00),
(9, 5, 2, 'VEC0028', '2023-09-30', 1, TRUE, 890.00), (10, 6, 1, 'VEC0029', '2023-09-25', 2, TRUE, 280.00),
(11, 7, 3, 'VEC0030', '2023-09-10', 1, TRUE, 1250.00), (12, 8, 2, 'VEC0031', '2023-08-20', 3, TRUE, 480.00),
(13, 9, 1, 'VEC0032', '2023-08-15', 1, TRUE, 150.00), (14, 10, 2, 'VEC0033', '2023-07-30', 2, TRUE, 1100.00),
(15, 11, 1, 'VEC0034', '2023-07-20', 1, TRUE, 320.00), (16, 12, 3, 'VEC0035', '2023-07-05', 3, TRUE, 900.00),
(17, 13, 1, 'VEC0036', '2023-06-15', 1, TRUE, 450.00), (18, 14, 2, 'VEC0037', '2023-06-10', 2, TRUE, 600.00),
(19, 15, 1, 'VEC0038', '2023-05-25', 1, TRUE, 380.00), (20, 16, 3, 'VEC0039', '2023-05-10', 3, TRUE, 1500.00),
(2, 17, 2, 'VEC0040', '2023-04-20', 1, TRUE, 700.00), (3, 18, 1, 'VEC0041', '2023-04-05', 2, TRUE, 550.00),
(5, 19, 3, 'VEC0042', '2023-03-15', 1, TRUE, 2200.00), (6, 20, 2, 'VEC0043', '2023-03-01', 1, TRUE, 950.00),

-- Grupo 2: Pedidos 21-35 (Saída OK, Devolução Pendente) - Placas VEC0004 a VEC0018
(9, 1, 2, 'VEC0004', CURDATE() + INTERVAL 5 DAY, 1, FALSE, 550.00),
(10, 2, 1, 'VEC0005', CURDATE() + INTERVAL 3 DAY, 2, FALSE, 380.00),
(11, 3, 3, 'VEC0006', CURDATE() + INTERVAL 7 DAY, 3, FALSE, 780.00),
(12, 4, 1, 'VEC0007', CURDATE() + INTERVAL 2 DAY, 1, FALSE, 290.00),
(13, 5, 2, 'VEC0008', CURDATE() + INTERVAL 10 DAY, 2, FALSE, 1380.00),
(14, 6, 1, 'VEC0009', CURDATE() + INTERVAL 4 DAY, 1, FALSE, 650.00),
(15, 7, 3, 'VEC0010', CURDATE() + INTERVAL 6 DAY, 3, FALSE, 960.00),
(16, 8, 2, 'VEC0011', CURDATE() + INTERVAL 8 DAY, 1, FALSE, 1240.00),
(17, 9, 1, 'VEC0012', CURDATE() + INTERVAL 1 DAY, 2, FALSE, 205.00),
(18, 10, 2, 'VEC0013', CURDATE() + INTERVAL 12 DAY, 1, FALSE, 2000.00),
(19, 11, 1, 'VEC0014', CURDATE() + INTERVAL 5 DAY, 3, FALSE, 700.00),
(20, 12, 3, 'VEC0015', CURDATE() + INTERVAL 3 DAY, 1, FALSE, 500.00),
(2, 13, 1, 'VEC0016', CURDATE() + INTERVAL 7 DAY, 2, FALSE, 1800.00),
(3, 14, 2, 'VEC0017', CURDATE() + INTERVAL 2 DAY, 1, FALSE, 560.00),
(5, 15, 1, 'VEC0018', CURDATE() + INTERVAL 10 DAY, 3, FALSE, 2350.00),

-- Grupo 3: Pedidos 36-50 (Saída Pendente, Devolução Pendente)
-- Placas: VEC0019-VEC0023 (5) + VEC0044-VEC0050 (7) + VEC0024, VEC0025, VEC0026 (3) -> Reutilizando disponíveis
(6, 16, 2, 'VEC0019', CURDATE() + INTERVAL 4 DAY, 1, FALSE, 1480.00), -- VEC0019
(9, 17, 3, 'VEC0020', CURDATE() + INTERVAL 6 DAY, 2, FALSE, 2280.00), -- VEC0020
(10, 18, 1, 'VEC0021', CURDATE() + INTERVAL 8 DAY, 1, FALSE, 1650.00), -- VEC0021
(11, 19, 2, 'VEC0022', CURDATE() + INTERVAL 1 DAY, 3, FALSE, 400.00),  -- VEC0022
(12, 20, 3, 'VEC0023', CURDATE() + INTERVAL 12 DAY, 1, FALSE, 1440.00),-- VEC0023
(13, 1, 1, 'VEC0044', CURDATE() + INTERVAL 5 DAY, 2, FALSE, 1600.00), -- VEC0044
(14, 2, 2, 'VEC0045', CURDATE() + INTERVAL 3 DAY, 1, FALSE, 1280.00), -- VEC0045
(15, 3, 3, 'VEC0046', CURDATE() + INTERVAL 7 DAY, 2, FALSE, 1450.00), -- VEC0046
(16, 4, 1, 'VEC0047', CURDATE() + INTERVAL 2 DAY, 1, FALSE, 570.00),  -- VEC0047
(17, 5, 2, 'VEC0048', CURDATE() + INTERVAL 10 DAY, 3, FALSE, 5080.00),-- VEC0048
(18, 6, 3, 'VEC0049', CURDATE() + INTERVAL 4 DAY, 1, FALSE, 2320.00), -- VEC0049
(19, 7, 1, 'VEC0050', CURDATE() + INTERVAL 6 DAY, 2, FALSE, 3650.00), -- VEC0050
(20, 8, 2, 'VEC0024', CURDATE() + INTERVAL 8 DAY, 1, FALSE, 816.00),  -- VEC0024 (reutilizado)
(2, 9, 3, 'VEC0025', CURDATE() + INTERVAL 1 DAY, 3, FALSE, 300.00),   -- VEC0025 (reutilizado)
(3, 10, 1, 'VEC0026', CURDATE() + INTERVAL 12 DAY, 1, FALSE, 1550.00);-- VEC0026 (reutilizado)


-- saidas_veiculos (50 linhas)
-- Para Pedidos 1-20 (Grupo 1): Saídas COMPLETAS
-- Para Pedidos 21-35 (Grupo 2): Saídas COMPLETAS
-- Para Pedidos 36-50 (Grupo 3): Saídas INCOMPLETAS (instante, km, assistente NULL)
INSERT INTO saidas_veiculos (id_pedido, id_assistente, placa, instante_saida, km_saida) VALUES
-- Saídas Completas para Pedidos 1-20 (Grupo 1) - Placas VEC0024 a VEC0043
(1, 2, 'VEC0024', '2023-11-08 10:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0024')),
(2, 3, 'VEC0025', '2023-11-01 14:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0025')),
(3, 5, 'VEC0026', '2023-10-15 09:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0026')),
(4, 6, 'VEC0027', '2023-10-10 11:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0027')),
(5, 9, 'VEC0028', '2023-09-20 08:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0028')),
(6, 10, 'VEC0029', '2023-09-22 13:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0029')),
(7, 11, 'VEC0030', '2023-09-01 10:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0030')),
(8, 12, 'VEC0031', '2023-08-15 16:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0031')),
(9, 13, 'VEC0032', '2023-08-14 09:30:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0032')),
(10, 14, 'VEC0033', '2023-07-20 11:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0033')),
(11, 15, 'VEC0034', '2023-07-18 14:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0034')),
(12, 16, 'VEC0035', '2023-07-01 09:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0035')),
(13, 17, 'VEC0036', '2023-06-10 08:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0036')),
(14, 18, 'VEC0037', '2023-06-05 13:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0037')),
(15, 19, 'VEC0038', '2023-05-20 10:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0038')),
(16, 20, 'VEC0039', '2023-05-01 16:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0039')),
(17, 2, 'VEC0040', '2023-04-15 09:30:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0040')),
(18, 3, 'VEC0041', '2023-04-01 11:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0041')),
(19, 5, 'VEC0042', '2023-03-10 14:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0042')),
(20, 6, 'VEC0043', '2023-02-25 09:00:00', (SELECT quilometragem FROM veiculos WHERE placa='VEC0043')),
-- Saídas Completas para Pedidos 21-35 (Grupo 2) - Placas VEC0004 a VEC0018
(21, 9, 'VEC0004', NOW() - INTERVAL 2 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0004')),
(22, 10, 'VEC0005', NOW() - INTERVAL 1 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0005')),
(23, 11, 'VEC0006', NOW() - INTERVAL 3 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0006')),
(24, 12, 'VEC0007', NOW() - INTERVAL 0 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0007')),
(25, 13, 'VEC0008', NOW() - INTERVAL 5 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0008')),
(26, 14, 'VEC0009', NOW() - INTERVAL 1 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0009')),
(27, 15, 'VEC0010', NOW() - INTERVAL 2 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0010')),
(28, 16, 'VEC0011', NOW() - INTERVAL 4 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0011')),
(29, 17, 'VEC0012', NOW() - INTERVAL 0 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0012')),
(30, 18, 'VEC0013', NOW() - INTERVAL 6 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0013')),
(31, 19, 'VEC0014', NOW() - INTERVAL 2 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0014')),
(32, 20, 'VEC0015', NOW() - INTERVAL 1 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0015')),
(33, 2, 'VEC0016', NOW() - INTERVAL 3 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0016')),
(34, 3, 'VEC0017', NOW() - INTERVAL 0 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0017')),
(35, 5, 'VEC0018', NOW() - INTERVAL 5 DAY, (SELECT quilometragem FROM veiculos WHERE placa='VEC0018')),
-- Saídas INCOMPLETAS para Pedidos 36-50 (Grupo 3)
-- Placas VEC0019-VEC0023, VEC0044-VEC0050, VEC0024-VEC0026
(36, NULL, 'VEC0019', NULL, NULL), (37, NULL, 'VEC0020', NULL, NULL), (38, NULL, 'VEC0021', NULL, NULL),
(39, NULL, 'VEC0022', NULL, NULL), (40, NULL, 'VEC0023', NULL, NULL), (41, NULL, 'VEC0044', NULL, NULL),
(42, NULL, 'VEC0045', NULL, NULL), (43, NULL, 'VEC0046', NULL, NULL), (44, NULL, 'VEC0047', NULL, NULL),
(45, NULL, 'VEC0048', NULL, NULL), (46, NULL, 'VEC0049', NULL, NULL), (47, NULL, 'VEC0050', NULL, NULL),
(48, NULL, 'VEC0024', NULL, NULL), (49, NULL, 'VEC0025', NULL, NULL), (50, NULL, 'VEC0026', NULL, NULL);


-- devolucoes_veiculos (50 linhas)
-- Para Pedidos 1-20 (Grupo 1): Devoluções COMPLETAS
-- Para Pedidos 21-35 (Grupo 2): Devoluções INCOMPLETAS
-- Para Pedidos 36-50 (Grupo 3): Devoluções INCOMPLETAS
INSERT INTO devolucoes_veiculos (id_pedido, id_assistente, instante_devolucao, placa, km_chegada, id_manutencao) VALUES
-- Devoluções Completas para Pedidos 1-20 (Grupo 1) - Placas VEC0024 a VEC0043
(1, 2, '2023-11-10 18:00:00', 'VEC0024', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=1) + 200, NULL),
(2, 3, '2023-11-05 17:00:00', 'VEC0025', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=2) + 400, NULL),
(3, 5, '2023-10-20 10:00:00', 'VEC0026', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=3) + 500, NULL),
(4, 6, '2023-10-15 15:00:00', 'VEC0027', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=4) + 300, NULL),
(5, 9, '2023-09-30 09:00:00', 'VEC0028', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=5) + 700, NULL),
(6, 10, '2023-09-25 18:00:00', 'VEC0029', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=6) + 250, NULL),
(7, 11, '2023-09-10 11:00:00', 'VEC0030', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=7) + 1000, NULL),
(8, 12, '2023-08-20 17:00:00', 'VEC0031', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=8) + 350, NULL),
(9, 13, '2023-08-15 10:30:00', 'VEC0032', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=9) + 100, NULL),
(10, 14, '2023-07-30 14:00:00', 'VEC0033', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=10) + 800, NULL),
(11, 15, '2023-07-20 19:00:00', 'VEC0034', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=11) + 280, NULL),
(12, 16, '2023-07-05 10:00:00', 'VEC0035', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=12) + 650, NULL),
(13, 17, '2023-06-15 09:00:00', 'VEC0036', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=13) + 400, NULL),
(14, 18, '2023-06-10 18:00:00', 'VEC0037', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=14) + 520, NULL),
(15, 19, '2023-05-25 11:00:00', 'VEC0038', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=15) + 330, NULL),
(16, 20, '2023-05-10 17:00:00', 'VEC0039', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=16) + 1200, NULL),
(17, 2, '2023-04-20 10:30:00', 'VEC0040', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=17) + 610, NULL),
(18, 3, '2023-04-05 14:00:00', 'VEC0041', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=18) + 480, NULL),
(19, 5, '2023-03-15 19:00:00', 'VEC0042', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=19) + 1800, NULL),
(20, 6, '2023-03-01 10:00:00', 'VEC0043', (SELECT km_saida FROM saidas_veiculos WHERE id_pedido=20) + 750, NULL),
-- Devoluções INCOMPLETAS para Pedidos 21-35 (Grupo 2) - Placas VEC0004 a VEC0018
(21, NULL, NULL, 'VEC0004', NULL, NULL), (22, NULL, NULL, 'VEC0005', NULL, NULL), (23, NULL, NULL, 'VEC0006', NULL, NULL),
(24, NULL, NULL, 'VEC0007', NULL, NULL), (25, NULL, NULL, 'VEC0008', NULL, NULL), (26, NULL, NULL, 'VEC0009', NULL, NULL),
(27, NULL, NULL, 'VEC0010', NULL, NULL), (28, NULL, NULL, 'VEC0011', NULL, NULL), (29, NULL, NULL, 'VEC0012', NULL, NULL),
(30, NULL, NULL, 'VEC0013', NULL, NULL), (31, NULL, NULL, 'VEC0014', NULL, NULL), (32, NULL, NULL, 'VEC0015', NULL, NULL),
(33, NULL, NULL, 'VEC0016', NULL, NULL), (34, NULL, NULL, 'VEC0017', NULL, NULL), (35, NULL, NULL, 'VEC0018', NULL, NULL),
-- Devoluções INCOMPLETAS para Pedidos 36-50 (Grupo 3)
-- Placas VEC0019-VEC0023, VEC0044-VEC0050, VEC0024-VEC0026
(36, NULL, NULL, 'VEC0019', NULL, NULL), (37, NULL, NULL, 'VEC0020', NULL, NULL), (38, NULL, NULL, 'VEC0021', NULL, NULL),
(39, NULL, NULL, 'VEC0022', NULL, NULL), (40, NULL, NULL, 'VEC0023', NULL, NULL), (41, NULL, NULL, 'VEC0044', NULL, NULL),
(42, NULL, NULL, 'VEC0045', NULL, NULL), (43, NULL, NULL, 'VEC0046', NULL, NULL), (44, NULL, NULL, 'VEC0047', NULL, NULL),
(45, NULL, NULL, 'VEC0048', NULL, NULL), (46, NULL, NULL, 'VEC0049', NULL, NULL), (47, NULL, NULL, 'VEC0050', NULL, NULL),
(48, NULL, NULL, 'VEC0024', NULL, NULL), (49, NULL, NULL, 'VEC0025', NULL, NULL), (50, NULL, NULL, 'VEC0026', NULL, NULL);

-- ATUALIZAÇÕES NECESSÁRIAS APÓS INSERTS

-- Atualizar pedidos_locacao com id_saida e id_devolucao
-- id_saida e id_devolucao em pedidos_locacao devem ser os IDs AUTO_INCREMENT das tabelas respectivas.
-- Assume-se que os IDs foram gerados sequencialmente a partir de 1.
UPDATE pedidos_locacao p SET p.id_saida = p.id_pedido WHERE p.id_pedido BETWEEN 1 AND 50;
UPDATE pedidos_locacao p SET p.id_devolucao = p.id_pedido WHERE p.id_pedido BETWEEN 1 AND 50;


-- Atualizar quilometragem e situação dos veículos com base nos pedidos.
-- 1. Veículos de Pedidos Finalizados (1-20): VEC0024 a VEC0043
--    Situação volta para 1 (Disponível), KM é a de chegada da devolução.
DELIMITER //
CREATE PROCEDURE UpdateKmVehiclesFinalizados()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 20 DO
        UPDATE veiculos v
        JOIN devolucoes_veiculos dv ON v.placa = dv.placa AND dv.id_pedido = i
        SET v.quilometragem = dv.km_chegada, v.situacao = 1
        WHERE dv.id_pedido = i AND dv.km_chegada IS NOT NULL; -- Apenas se devolução foi completa
        SET i = i + 1;
    END WHILE;
END //
DELIMITER ;
CALL UpdateKmVehiclesFinalizados();
DROP PROCEDURE UpdateKmVehiclesFinalizados;

-- 2. Veículos de Pedidos com Saída OK, Devolução Pendente (21-35): VEC0004 a VEC0018
--    Situação é 2 (Alugado), KM é a de saída.
DELIMITER //
CREATE PROCEDURE UpdateKmVehiclesSaidaOkDevPendente()
BEGIN
    DECLARE i INT DEFAULT 21;
    WHILE i <= 35 DO
        UPDATE veiculos v
        JOIN saidas_veiculos sv ON v.placa = sv.placa AND sv.id_pedido = i
        SET v.quilometragem = sv.km_saida, v.situacao = 2
        WHERE sv.id_pedido = i AND sv.km_saida IS NOT NULL; -- Apenas se saída foi completa
        SET i = i + 1;
    END WHILE;
END //
DELIMITER ;
CALL UpdateKmVehiclesSaidaOkDevPendente();
DROP PROCEDURE UpdateKmVehiclesSaidaOkDevPendente;

-- 3. Veículos de Pedidos com Saída Pendente (36-50):
--    Placas VEC0019-VEC0023, VEC0044-VEC0050, VEC0024-VEC0026 (os 3 reutilizados)
--    Situação deve ser 2 (Alugado/Reservado). Quilometragem original (não mudou pois não saiu).
--    (Os veículos VEC0019-VEC0023 já estão como situacao=2)
--    (Os veículos VEC0044-VEC0050, VEC0024-VEC0026 precisam mudar para situacao=2 se já não estiverem)
UPDATE veiculos SET situacao = 2 WHERE placa IN (
    'VEC0019', 'VEC0020', 'VEC0021', 'VEC0022', 'VEC0023', -- Já são situacao=2
    'VEC0044', 'VEC0045', 'VEC0046', 'VEC0047', 'VEC0048', 'VEC0049', 'VEC0050', -- Eram situacao=1
    'VEC0024', 'VEC0025', 'VEC0026' -- Eram situacao=1 (após a finalização do pedido 1-3), agora são reservados novamente
);

update db_locadora.pedidos_locacao set devolucao_esperada = '2025-06-30' where finalizado = 0;
-- SET FOREIGN_KEY_CHECKS=1; -- Reabilitar se desabilitado anteriormente.