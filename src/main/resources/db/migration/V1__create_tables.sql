-- Tabela tb_usuario
CREATE TABLE tb_usuario (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            nome VARCHAR(255) NOT NULL,
                            email VARCHAR(255) UNIQUE NOT NULL
);

-- Tabela tb_genero
CREATE TABLE tb_genero (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           nome VARCHAR(255) NOT NULL
);

-- Tabela tb_livro
CREATE TABLE tb_livro (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          nome VARCHAR(255) NOT NULL,
                          autor VARCHAR(255) NOT NULL,
                          disponivel BOOLEAN DEFAULT TRUE
);

-- Tabela de associação tb_livro_genero (N-N)
CREATE TABLE tb_livro_genero (
                                 livro_id BIGINT NOT NULL,
                                 genero_id BIGINT NOT NULL,
                                 PRIMARY KEY (livro_id, genero_id),
                                 FOREIGN KEY (livro_id) REFERENCES tb_livro(id),
                                 FOREIGN KEY (genero_id) REFERENCES tb_genero(id)
);

-- Tabela tb_emprestimo (com chave composta)
CREATE TABLE tb_emprestimo (
                               usuario_id BIGINT NOT NULL,
                               livro_id BIGINT NOT NULL,
                               data_emprestimo DATE NOT NULL,
                               data_devolucao DATE NOT NULL,
                               PRIMARY KEY (usuario_id, livro_id),
                               FOREIGN KEY (usuario_id) REFERENCES tb_usuario(id),
                               FOREIGN KEY (livro_id) REFERENCES tb_livro(id)
);