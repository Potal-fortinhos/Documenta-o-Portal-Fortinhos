-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS sistema_escolar;
USE sistema_escolar;

-- ==============================================
-- CRIAÇÃO DAS TABELAS
-- ==============================================

-- Tabela 1: Aluno
CREATE TABLE aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ra VARCHAR(20) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    dados_pessoais_cript TEXT NOT NULL,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP,
    ativo BOOLEAN DEFAULT TRUE
);

-- Tabela 2: Professor
CREATE TABLE professor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    especialidade VARCHAR(100),
    data_contratacao DATE,
    ativo BOOLEAN DEFAULT TRUE
);

-- Tabela 3: Turma_Disciplina
CREATE TABLE turma_disciplina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INT NOT NULL,
    professor_id INT NOT NULL,
    semestre VARCHAR(10),
    ano INT,
    ativo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

-- Tabela 4: Nota
CREATE TABLE nota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT NOT NULL,
    disciplina_id INT NOT NULL,
    bimestre INT NOT NULL,
    valor DECIMAL(5,2) NOT NULL,
    data_lancamento DATETIME NOT NULL,
    lancado_por INT NOT NULL,
    observacao TEXT,
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (disciplina_id) REFERENCES turma_disciplina(id),
    FOREIGN KEY (lancado_por) REFERENCES professor(id)
);

-- Tabela 5: Log_Alteracao_Nota
CREATE TABLE log_alteracao_nota (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nota_id INT NOT NULL,
    data_alteracao DATETIME NOT NULL,
    professor_id INT NOT NULL,
    valor_antigo DECIMAL(5,2) NOT NULL,
    valor_novo DECIMAL(5,2) NOT NULL,
    motivo VARCHAR(255),
    FOREIGN KEY (nota_id) REFERENCES nota(id),
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

-- Tabela 6: Rematricula
CREATE TABLE rematricula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT NOT NULL,
    semestre VARCHAR(10) NOT NULL,
    data_solicitacao DATETIME NOT NULL,
    data_processamento DATETIME,
    status ENUM('pendente', 'aprovado', 'rejeitado', 'concluido') NOT NULL DEFAULT 'pendente',
    boleto_simulado TEXT,
    observacao TEXT,
    processado_por INT,
    FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    FOREIGN KEY (processado_por) REFERENCES professor(id)
);

-- Tabela 7: Calendario_Evento
CREATE TABLE calendario_evento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    descricao TEXT,
    data DATE NOT NULL,
    data_fim DATE,
    tipo ENUM('prova', 'feriado', 'reuniao', 'evento', 'matricula') NOT NULL,
    turma_id INT,
    FOREIGN KEY (turma_id) REFERENCES turma_disciplina(id)
);

-- Tabela 8: Login
CREATE TABLE login (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT NOT NULL,
    tipo_usuario ENUM('aluno', 'professor', 'admin') NOT NULL,
    email VARCHAR(100) NOT NULL,
    token VARCHAR(255),
    data_login DATETIME DEFAULT CURRENT_TIMESTAMP,
    data_logout DATETIME,
    ip_address VARCHAR(45),
    user_agent TEXT,
    sucesso BOOLEAN DEFAULT TRUE
);

-- ==============================================
-- INSERÇÃO DE DADOS DE EXEMPLO
-- ==============================================

-- Inserir professores
INSERT INTO professor (nome, email, senha, especialidade, data_contratacao) VALUES
('João Silva', 'joao.silva@escola.com', 'senha123', 'Matemática', CURDATE()),
('Maria Santos', 'maria.santos@escola.com', 'senha456', 'Português', CURDATE()),
('Carlos Oliveira', 'carlos.oliveira@escola.com', 'senha789', 'História', CURDATE());

-- Inserir alunos
INSERT INTO aluno (ra, nome, email, senha, dados_pessoais_cript) VALUES
('2024001', 'Ana Souza', 'ana.souza@email.com', 'aluno123', 'dados_cript_ana'),
('2024002', 'Pedro Lima', 'pedro.lima@email.com', 'aluno456', 'dados_cript_pedro'),
('2024003', 'Mariana Costa', 'mariana.costa@email.com', 'aluno789', 'dados_cript_mariana');

-- Inserir turmas
INSERT INTO turma_disciplina (nome, carga_horaria, professor_id, semestre, ano) VALUES
('Matemática', 80, 1, '2024.1', 2024),
('Português', 80, 2, '2024.1', 2024),
('História', 60, 3, '2024.1', 2024);

-- Inserir notas
INSERT INTO nota (aluno_id, disciplina_id, bimestre, valor, data_lancamento, lancado_por, observacao) VALUES
(1, 1, 1, 8.5, NOW(), 1, 'Primeira nota do bimestre'),
(1, 2, 1, 7.0, NOW(), 2, NULL),
(2, 1, 1, 9.0, NOW(), 1, NULL),
(3, 3, 1, 6.5, NOW(), 3, 'Aluno precisa melhorar');

-- Inserir logs de alteração
INSERT INTO log_alteracao_nota (nota_id, data_alteracao, professor_id, valor_antigo, valor_novo, motivo) VALUES
(1, NOW(), 1, 7.5, 8.5, 'Revisão de prova'),
(2, NOW(), 2, 6.5, 7.0, 'Recuperação');

-- Inserir rematriculas
INSERT INTO rematricula (aluno_id, semestre, data_solicitacao, status) VALUES
(1, '2024.2', NOW(), 'pendente'),
(2, '2024.2', NOW(), 'pendente');

-- Inserir eventos
INSERT INTO calendario_evento (titulo, descricao, data, tipo, turma_id) VALUES
('Prova de Matemática', 'Prova do 2º bimestre', '2024-06-15', 'prova', 1),
('Feriado Nacional', 'Independência do Brasil', '2024-09-07', 'feriado', NULL),
('Reunião de Pais', 'Reunião bimestral', '2024-05-10', 'reuniao', NULL);

-- Inserir logins de exemplo
INSERT INTO login (usuario_id, tipo_usuario, email, ip_address, user_agent, sucesso) VALUES
(1, 'aluno', 'ana.souza@email.com', '192.168.1.100', 'Mozilla/5.0', TRUE),
(1, 'professor', 'joao.silva@escola.com', '192.168.1.101', 'Mozilla/5.0', TRUE),
(2, 'aluno', 'pedro.lima@email.com', '192.168.1.102', 'Mozilla/5.0', FALSE);

-- ==============================================
-- CONSULTAS PARA VERIFICAR AS CARDINALIDADES
-- ==============================================

-- 1. Professor → Turma_Disciplina (1:N)
SELECT p.nome AS professor, td.nome AS disciplina
FROM professor p
LEFT JOIN turma_disciplina td ON p.id = td.professor_id
ORDER BY p.nome;

-- 2. Aluno → Nota (1:N)
SELECT a.nome AS aluno, n.valor, td.nome AS disciplina
FROM aluno a
LEFT JOIN nota n ON a.id = n.aluno_id
LEFT JOIN turma_disciplina td ON n.disciplina_id = td.id
ORDER BY a.nome;

-- 3. Turma_Disciplina → Nota (1:N)
SELECT td.nome AS disciplina, COUNT(n.id) AS quantidade_notas
FROM turma_disciplina td
LEFT JOIN nota n ON td.id = n.disciplina_id
GROUP BY td.id;

-- 4. Professor → Nota (lançamento) (1:N)
SELECT p.nome AS professor, COUNT(n.id) AS quantidade_lancamentos
FROM professor p
LEFT JOIN nota n ON p.id = n.lancado_por
GROUP BY p.id;

-- 5. Nota → Log_Alteracao (1:N)
SELECT n.id AS nota_id, n.valor AS valor_atual, COUNT(l.id) AS quantidade_alteracoes
FROM nota n
LEFT JOIN log_alteracao_nota l ON n.id = l.nota_id
GROUP BY n.id;

-- 6. Professor → Log_Alteracao (1:N)
SELECT p.nome AS professor, COUNT(l.id) AS quantidade_alteracoes_realizadas
FROM professor p
LEFT JOIN log_alteracao_nota l ON p.id = l.professor_id
GROUP BY p.id;

-- 7. Aluno → Rematricula (1:N)
SELECT a.nome AS aluno, COUNT(r.id) AS quantidade_rematriculas
FROM aluno a
LEFT JOIN rematricula r ON a.id = r.aluno_id
GROUP BY a.id;

-- 8. Turma_Disciplina → Calendario_Evento (0:N)
SELECT td.nome AS disciplina, COUNT(c.id) AS quantidade_eventos
FROM turma_disciplina td
LEFT JOIN calendario_evento c ON td.id = c.turma_id
GROUP BY td.id;

-- 9. Login por usuário (N:1)
SELECT tipo_usuario, email, COUNT(*) AS total_logins
FROM login
GROUP BY tipo_usuario, email;

-- ==============================================
-- VIEWS PARA RELATÓRIOS
-- ==============================================

-- View para relatório de notas
CREATE VIEW vw_relatorio_notas AS
SELECT 
    a.nome AS aluno,
    a.ra,
    td.nome AS disciplina,
    p.nome AS professor,
    n.bimestre,
    n.valor,
    n.data_lancamento
FROM nota n
JOIN aluno a ON n.aluno_id = a.id
JOIN turma_disciplina td ON n.disciplina_id = td.id
JOIN professor p ON n.lancado_por = p.id;

-- View para alunos em dependência (média < 6)
CREATE VIEW vw_alunos_dependencia AS
SELECT 
    a.id AS aluno_id,
    a.nome AS aluno,
    td.nome AS disciplina,
    AVG(n.valor) AS media
FROM nota n
JOIN aluno a ON n.aluno_id = a.id
JOIN turma_disciplina td ON n.disciplina_id = td.id
GROUP BY a.id, a.nome, td.nome
HAVING AVG(n.valor) < 6;

-- ==============================================
-- EXEMPLOS DE CONSULTAS ÚTEIS
-- ==============================================

-- Boletim do aluno
SELECT 
    td.nome AS disciplina,
    AVG(CASE WHEN n.bimestre = 1 THEN n.valor END) AS bimestre1,
    AVG(CASE WHEN n.bimestre = 2 THEN n.valor END) AS bimestre2,
    AVG(CASE WHEN n.bimestre = 3 THEN n.valor END) AS bimestre3,
    AVG(CASE WHEN n.bimestre = 4 THEN n.valor END) AS bimestre4,
    ROUND(AVG(n.valor), 2) AS media_final
FROM nota n
JOIN turma_disciplina td ON n.disciplina_id = td.id
WHERE n.aluno_id = 1
GROUP BY td.nome;

-- Eventos do calendário por mês
SELECT * FROM calendario_evento 
WHERE YEAR(data) = 2024 AND MONTH(data) = 6
ORDER BY data;

-- Histórico de login de um usuário
SELECT * FROM login 
WHERE usuario_id = 1 AND tipo_usuario = 'aluno'
ORDER BY data_login DESC;

/* ==============================================
   CARDINALIDADES DO BANCO DE DADOS
   ==============================================

1. Professor → Turma_Disciplina: 1 : N
   - Um professor pode ministrar várias disciplinas
   - Cada disciplina tem apenas um professor

2. Aluno → Nota: 1 : N
   - Um aluno pode ter várias notas
   - Cada nota pertence a um único aluno

3. Turma_Disciplina → Nota: 1 : N
   - Uma disciplina pode ter várias notas lançadas
   - Cada nota está vinculada a uma única disciplina

4. Professor → Nota (lançamento): 1 : N
   - Um professor pode lançar várias notas
   - Cada nota é lançada por um único professor

5. Nota → Log_Alteracao: 1 : N
   - Uma nota pode ter várias alterações registradas
   - Cada alteração está vinculada a uma única nota

6. Professor → Log_Alteracao: 1 : N
   - Um professor pode realizar várias alterações
   - Cada alteração é feita por um único professor

7. Aluno → Rematricula: 1 : N
   - Um aluno pode fazer várias rematrículas
   - Cada rematrícula pertence a um único aluno

8. Professor → Rematricula (processamento): 0 : N
   - Um professor pode processar várias rematrículas
   - Cada rematrícula pode ser processada por um professor

9. Turma_Disciplina → Calendario_Evento: 0 : N
   - Uma disciplina pode ter vários eventos
   - Cada evento pode estar vinculado a uma disciplina ou ser geral

10. Login → Aluno/Professor: N : 1
    - Um usuário pode ter vários registros de login
    - Cada registro pertence a um único usuário


*/
-- =============================================
-- CRIAÇÃO DAS STORED PROCEDURES
-- =============================================

DELIMITER //

-- Procedure para gerenciar Aluno (INSERT, UPDATE, DELETE)
CREATE PROCEDURE sp_gerenciar_aluno(
    IN p_acao ENUM('INSERT', 'UPDATE', 'DELETE'),
    IN p_id_aluno INT,
    IN p_nome VARCHAR(100),
    IN p_cpf VARCHAR(14),
    IN p_data_nascimento DATE,
    IN p_email VARCHAR(100),
    IN p_telefone VARCHAR(20),
    IN p_endereco TEXT,
    IN p_status ENUM('Ativo', 'Inativo', 'Trancado')
)
BEGIN
    CASE p_acao
        WHEN 'INSERT' THEN
            INSERT INTO aluno (nome, cpf, data_nascimento, email, telefone, endereco, status)
            VALUES (p_nome, p_cpf, p_data_nascimento, p_email, p_telefone, p_endereco, p_status);
            
        WHEN 'UPDATE' THEN
            UPDATE aluno 
            SET nome = IFNULL(p_nome, nome),
                cpf = IFNULL(p_cpf, cpf),
                data_nascimento = IFNULL(p_data_nascimento, data_nascimento),
                email = IFNULL(p_email, email),
                telefone = IFNULL(p_telefone, telefone),
                endereco = IFNULL(p_endereco, endereco),
                status = IFNULL(p_status, status)
            WHERE id_aluno = p_id_aluno;
            
        WHEN 'DELETE' THEN
            DELETE FROM aluno WHERE id_aluno = p_id_aluno;
    END CASE;
END //

-- Procedure para gerenciar Professor
CREATE PROCEDURE sp_gerenciar_professor(
    IN p_acao ENUM('INSERT', 'UPDATE', 'DELETE'),
    IN p_id_professor INT,
    IN p_nome VARCHAR(100),
    IN p_cpf VARCHAR(14),
    IN p_data_nascimento DATE,
    IN p_email VARCHAR(100),
    IN p_telefone VARCHAR(20),
    IN p_especialidade VARCHAR(100),
    IN p_data_contratacao DATE,
    IN p_status ENUM('Ativo', 'Inativo', 'Ferias')
)
BEGIN
    CASE p_acao
        WHEN 'INSERT' THEN
            INSERT INTO professor (nome, cpf, data_nascimento, email, telefone, especialidade, data_contratacao, status)
            VALUES (p_nome, p_cpf, p_data_nascimento, p_email, p_telefone, p_especialidade, p_data_contratacao, p_status);
            
        WHEN 'UPDATE' THEN
            UPDATE professor 
            SET nome = IFNULL(p_nome, nome),
                cpf = IFNULL(p_cpf, cpf),
                data_nascimento = IFNULL(p_data_nascimento, data_nascimento),
                email = IFNULL(p_email, email),
                telefone = IFNULL(p_telefone, telefone),
                especialidade = IFNULL(p_especialidade, especialidade),
                data_contratacao = IFNULL(p_data_contratacao, data_contratacao),
                status = IFNULL(p_status, status)
            WHERE id_professor = p_id_professor;
            
        WHEN 'DELETE' THEN
            DELETE FROM professor WHERE id_professor = p_id_professor;
    END CASE;
END //

-- Procedure para gerenciar Turma
CREATE PROCEDURE sp_gerenciar_turma(
    IN p_acao ENUM('INSERT', 'UPDATE', 'DELETE'),
    IN p_id_turma INT,
    IN p_nome_turma VARCHAR(50),
    IN p_id_professor INT,
    IN p_ano_letivo INT,
    IN p_periodo ENUM('Manhã', 'Tarde', 'Noite'),
    IN p_capacidade_max INT,
    IN p_data_inicio DATE,
    IN p_data_fim DATE,
    IN p_status ENUM('Ativa', 'Concluída', 'Cancelada')
)
BEGIN
    CASE p_acao
        WHEN 'INSERT' THEN
            INSERT INTO turma (nome_turma, id_professor, ano_letivo, periodo, capacidade_max, data_inicio, data_fim, status)
            VALUES (p_nome_turma, p_id_professor, p_ano_letivo, p_periodo, p_capacidade_max, p_data_inicio, p_data_fim, p_status);
            
        WHEN 'UPDATE' THEN
            UPDATE turma 
            SET nome_turma = IFNULL(p_nome_turma, nome_turma),
                id_professor = IFNULL(p_id_professor, id_professor),
                ano_letivo = IFNULL(p_ano_letivo, ano_letivo),
                periodo = IFNULL(p_periodo, periodo),
                capacidade_max = IFNULL(p_capacidade_max, capacidade_max),
                data_inicio = IFNULL(p_data_inicio, data_inicio),
                data_fim = IFNULL(p_data_fim, data_fim),
                status = IFNULL(p_status, status)
            WHERE id_turma = p_id_turma;
            
        WHEN 'DELETE' THEN
            DELETE FROM turma WHERE id_turma = p_id_turma;
    END CASE;
END //

-- Procedure para gerenciar Nota com log automático
CREATE PROCEDURE sp_gerenciar_nota(
    IN p_acao ENUM('INSERT', 'UPDATE', 'DELETE'),
    IN p_id_nota INT,
    IN p_id_aluno INT,
    IN p_id_turma INT,
    IN p_tipo_avaliacao VARCHAR(50),
    IN p_nota DECIMAL(4,2),
    IN p_bimestre INT,
    IN p_observacao TEXT,
    IN p_usuario VARCHAR(100)
)
BEGIN
    DECLARE v_nota_anterior DECIMAL(4,2);
    
    CASE p_acao
        WHEN 'INSERT' THEN
            INSERT INTO nota (id_aluno, id_turma, tipo_avaliacao, nota, bimestre, observacao)
            VALUES (p_id_aluno, p_id_turma, p_tipo_avaliacao, p_nota, p_bimestre, p_observacao);
            
            -- Registrar log
            INSERT INTO log_alteracao_nota (id_nota, id_aluno, id_turma, nota_nova, tipo_avaliacao, usuario_responsavel, acao)
            VALUES (LAST_INSERT_ID(), p_id_aluno, p_id_turma, p_nota, p_tipo_avaliacao, p_usuario, 'INSERT');
            
        WHEN 'UPDATE' THEN
            -- Obter nota anterior
            SELECT nota INTO v_nota_anterior FROM nota WHERE id_nota = p_id_nota;
            
            UPDATE nota 
            SET id_aluno = IFNULL(p_id_aluno, id_aluno),
                id_turma = IFNULL(p_id_turma, id_turma),
                tipo_avaliacao = IFNULL(p_tipo_avaliacao, tipo_avaliacao),
                nota = IFNULL(p_nota, nota),
                bimestre = IFNULL(p_bimestre, bimestre),
                observacao = IFNULL(p_observacao, observacao)
            WHERE id_nota = p_id_nota;
            
            -- Registrar log
            INSERT INTO log_alteracao_nota (id_nota, id_aluno, id_turma, nota_anterior, nota_nova, tipo_avaliacao, usuario_responsavel, acao)
            VALUES (p_id_nota, p_id_aluno, p_id_turma, v_nota_anterior, p_nota, p_tipo_avaliacao, p_usuario, 'UPDATE');
            
        WHEN 'DELETE' THEN
            -- Registrar log antes de deletar
            SELECT nota, id_aluno, id_turma, tipo_avaliacao 
            INTO v_nota_anterior, p_id_aluno, p_id_turma, p_tipo_avaliacao 
            FROM nota WHERE id_nota = p_id_nota;
            
            INSERT INTO log_alteracao_nota (id_nota, id_aluno, id_turma, nota_anterior, tipo_avaliacao, usuario_responsavel, acao)
            VALUES (p_id_nota, p_id_aluno, p_id_turma, v_nota_anterior, p_tipo_avaliacao, p_usuario, 'DELETE');
            
            DELETE FROM nota WHERE id_nota = p_id_nota;
    END CASE;
END //

-- Procedure para gerenciar Calendario Eventos
CREATE PROCEDURE sp_gerenciar_calendario(
    IN p_acao ENUM('INSERT', 'UPDATE', 'DELETE'),
    IN p_id_evento INT,
    IN p_titulo VARCHAR(200),
    IN p_descricao TEXT,
    IN p_tipo_evento ENUM('Feriado', 'Prova', 'Reunião', 'Evento', 'Férias', 'Outro'),
    IN p_data_inicio DATETIME,
    IN p_data_fim DATETIME,
    IN p_local_evento VARCHAR(200),
    IN p_id_turma INT,
    IN p_publico_alvo ENUM('Todos', 'Alunos', 'Professores', 'TurmaEspecifica')
)
BEGIN
    CASE p_acao
        WHEN 'INSERT' THEN
            INSERT INTO calendario_eventos (titulo, descricao, tipo_evento, data_inicio, data_fim, local_evento, id_turma, publico_alvo)
            VALUES (p_titulo, p_descricao, p_tipo_evento, p_data_inicio, p_data_fim, p_local_evento, p_id_turma, p_publico_alvo);
            
        WHEN 'UPDATE' THEN
            UPDATE calendario_eventos 
            SET titulo = IFNULL(p_titulo, titulo),
                descricao = IFNULL(p_descricao, descricao),
                tipo_evento = IFNULL(p_tipo_evento, tipo_evento),
                data_inicio = IFNULL(p_data_inicio, data_inicio),
                data_fim = IFNULL(p_data_fim, data_fim),
                local_evento = IFNULL(p_local_evento, local_evento),
                id_turma = IFNULL(p_id_turma, id_turma),
                publico_alvo = IFNULL(p_publico_alvo, publico_alvo)
            WHERE id_evento = p_id_evento;
            
        WHEN 'DELETE' THEN
            DELETE FROM calendario_eventos WHERE id_evento = p_id_evento;
    END CASE;
END //

-- Procedure para consultar calendário por período
CREATE PROCEDURE sp_consultar_calendario(
    IN p_data_inicio DATE,
    IN p_data_fim DATE,
    IN p_tipo_evento VARCHAR(50)
)
BEGIN
    SELECT * FROM calendario_eventos 
    WHERE DATE(data_inicio) BETWEEN p_data_inicio AND p_data_fim
    AND (p_tipo_evento IS NULL OR tipo_evento = p_tipo_evento)
    ORDER BY data_inicio;
END //

DELIMITER ;

-- =============================================
-- EXEMPLOS DE USO DAS PROCEDURES
-- =============================================

-- Inserir um aluno
CALL sp_gerenciar_aluno('INSERT', NULL, 'João Silva', '123.456.789-00', '2000-01-15', 
                        'joao@email.com', '(11) 99999-9999', 'Rua A, 123', 'Ativo');

-- Inserir um professor
CALL sp_gerenciar_professor('INSERT', NULL, 'Maria Oliveira', '987.654.321-00', '1980-05-20',
                           'maria@email.com', '(11) 88888-8888', 'Matemática', '2020-02-01', 'Ativo');

-- Inserir uma turma
CALL sp_gerenciar_turma('INSERT', NULL, 'Turma A - Matemática', 1, 2024, 'Manhã', 
                        30, '2024-02-01', '2024-12-15', 'Ativa');

-- Inserir uma nota (com log automático)
CALL sp_gerenciar_nota('INSERT', NULL, 1, 1, 'Prova Bimestral', 8.5, 1, 
                       'Primeira avaliação', 'Sistema');

-- Inserir evento no calendário
CALL sp_gerenciar_calendario('INSERT', NULL, 'Prova de Matemática', 
                            'Avaliação bimestral da turma A', 'Prova', 
                            '2024-03-15 08:00:00', '2024-03-15 10:00:00', 
                            'Sala 101', 1, 'TurmaEspecifica');

-- Consultar eventos do calendário
CALL sp_consultar_calendario('2024-03-01', '2024-03-31', 'Prova');
