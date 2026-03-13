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