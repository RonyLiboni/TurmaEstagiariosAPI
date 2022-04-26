CREATE TABLE estagiario(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
cpf VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL,
turma_id BIGINT NOT NULL,
FOREIGN KEY (turma_id) REFERENCES turma(id)
)ENGINE = innodb;

INSERT INTO estagiario(id, nome, cpf, email, turma_id) VALUES(1, 'Rony', '84648', 'rkai@g.com', 1);
INSERT INTO estagiario(id, nome, cpf, email, turma_id) VALUES(2, 'Atreus', '181114648', 'ATRE@g.com', 1);
INSERT INTO estagiario(id, nome, cpf, email, turma_id) VALUES(4, 'Naruto', '584648', 'NARU@g.com', 1);
INSERT INTO estagiario(id, nome, cpf, email, turma_id) VALUES(5, 'Deku', '824648', 'DEKU@g.com', 1);