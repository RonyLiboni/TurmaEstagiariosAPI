CREATE TABLE TURMA(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
tecnologia VARCHAR(255) NOT NULL,
nome VARCHAR(255) NOT NULL
)ENGINE = innodb;

INSERT INTO Turma(id, nome, tecnologia) VALUES(1, 'TURMA 1 -2022', 'Java');
INSERT INTO Turma(id, nome, tecnologia) VALUES(2, 'TURMA 1 -2022', 'C#');
INSERT INTO Turma(id, nome, tecnologia) VALUES(3, 'TURMA 1 -2022', 'PHP');
INSERT INTO Turma(id, nome, tecnologia) VALUES(4, 'TURMA 1 -2022', 'Kotlin');