CREATE TABLE funcionarios (
    id_funcionario Long DEFAULT RANDOM_UUID() PRIMARY KEY,
    nome_funcionario VARCHAR(255) NOT NULL,
    email_funcionario VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    cargo VARCHAR(255) NOT NULL,
);