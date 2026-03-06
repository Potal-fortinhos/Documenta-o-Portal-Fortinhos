-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS sistema_escolar;
USE sistema_escolar;

-- Tabela 1: Aluno
CREATE TABLE aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ra VARCHAR(20) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    dados_pessoais_cript TEXT NOT NULL
);

-- Tabela 2: Professor
CREATE TABLE professor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

-- Tabela 3: Turma_Disciplina
CREATE TABLE turma_disciplina (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INT NOT NULL,
    professor_id INT NOT NULL,
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
    FOREIGN KEY (nota_id) REFERENCES nota(id),
    FOREIGN KEY (professor_id) REFERENCES professor(id)
);

-- Tabela 6: Rematricula
CREATE TABLE rematricula (
    id INT AUTO_INCREMENT PRIMARY KEY,
    aluno_id INT NOT NULL,
    semestre VARCHAR(10) NOT NULL,
    data_solicitacao DATETIME NOT NULL,
    status ENUM('pendente', 'concluido') NOT NULL,
    boleto_simulado TEXT,
    FOREIGN KEY (aluno_id) REFERENCES aluno(id)
);

-- Tabela 7: Calendario_Evento
CREATE TABLE calendario_evento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    data DATE NOT NULL,
    tipo ENUM('prova', 'feriado') NOT NULL
);

-- Inserção de dados de exemplo para teste

-- Inserir professores
INSERT INTO professor (nome, email, senha) VALUES
('João Silva', 'joao.silva@escola.com', 'senha123'),
('Maria Santos', 'maria.santos@escola.com', 'senha456'),
('Carlos Oliveira', 'carlos.oliveira@escola.com', 'senha789');

-- Inserir alunos
INSERT INTO aluno (ra, nome, email, senha, dados_pessoais_cript) VALUES
('2024001', 'Ana Souza', 'ana.souza@email.com', 'aluno123', 'dados_criptografados_1'),
('2024002', 'Pedro Lima', 'pedro.lima@email.com', 'aluno456', 'dados_criptografados_2'),
('2024003', 'Mariana Costa', 'mariana.costa@email.com', 'aluno789', 'dados_criptografados_3');

-- Inserir turmas/disciplinas
INSERT INTO turma_disciplina (nome, carga_horaria, professor_id) VALUES
('Matemática', 80, 1),
('Português', 80, 2),
('História', 60, 3);

-- Inserir notas
INSERT INTO nota (aluno_id, disciplina_id, bimestre, valor, data_lancamento, lancado_por) VALUES
(1, 1, 1, 8.5, NOW(), 1),
(1, 2, 1, 7.0, NOW(), 2),
(2, 1, 1, 9.0, NOW(), 1),
(3, 3, 1, 6.5, NOW(), 3);

-- Inserir log de alterações
INSERT INTO log_alteracao_nota (nota_id, data_alteracao, professor_id, valor_antigo, valor_novo) VALUES
(1, NOW(), 1, 7.5, 8.5),
(3, NOW(), 1, 8.0, 9.0);

-- Inserir rematrículas
INSERT INTO rematricula (aluno_id, semestre, data_solicitacao, status, boleto_simulado) VALUES
(1, '2024.1', NOW(), 'concluido', 'boleto_simulado_1'),
(2, '2024.1', NOW(), 'pendente', 'boleto_simulado_2');

-- Inserir eventos no calendário
INSERT INTO calendario_evento (titulo, data, tipo) VALUES
('Prova de Matemática', '2024-06-15', 'prova'),
('Prova de Português', '2024-06-18', 'prova'),
('Feriado Nacional', '2024-09-07', 'feriado');

-- Consultas para verificar as cardinalidades

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

-- Comentários sobre as cardinalidades
/* 
CARDINALIDADES DO BANCO DE DADOS:

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
*/