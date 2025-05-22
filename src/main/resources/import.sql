INSERT INTO tb_usuario (nome, email) VALUES ('João Silva', 'joao@email.com');
INSERT INTO tb_usuario (nome, email) VALUES ('Eduardo Ramos', 'eduardo@email.com');
INSERT INTO tb_usuario (nome, email) VALUES ('Gabriela Melquiades', 'gabriela@email.com');
INSERT INTO tb_usuario (nome, email) VALUES ('Nelio Alves', 'nelio@email.com');
INSERT INTO tb_usuario (nome, email) VALUES ('Marco Camara', 'camara@email.com');

INSERT INTO tb_livro (nome, autor, disponivel) VALUES ('Dom Quixote', 'Miguel de Cervantes', false);
INSERT INTO tb_livro (nome, autor, disponivel) VALUES ('1984', 'George Orwell', false);
INSERT INTO tb_livro (nome, autor, disponivel) VALUES ('O Senhor dos Anéis', 'J.R.R. Tolkien', false);
INSERT INTO tb_livro (nome, autor, disponivel) VALUES ('Orgulho e Preconceito', 'Jane Austen', true);
INSERT INTO tb_livro (nome, autor, disponivel) VALUES ('Cem Anos de Solidão', 'Gabriel García Márquez', true);
INSERT INTO tb_livro (nome, autor, disponivel) VALUES ('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', true);
INSERT INTO tb_livro (nome, autor, disponivel) VALUES ('A Revolução dos Bichos', 'George Orwell', true);
INSERT INTO tb_livro (nome, autor, disponivel) VALUES ('Frankenstein', 'Mary Shelley', true);
INSERT INTO tb_livro (nome, autor, disponivel) VALUES ('O Apanhador no Campo de Centeio', 'J.D. Salinger', true);
INSERT INTO tb_livro (nome, autor, disponivel) VALUES ('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', true);

INSERT INTO tb_genero (nome) VALUES ('Aventura');
INSERT INTO tb_genero (nome) VALUES ('Ficção');
INSERT INTO tb_genero (nome) VALUES ('Fantasia');
INSERT INTO tb_genero (nome) VALUES ('Romance');
INSERT INTO tb_genero (nome) VALUES ('Drama');

INSERT INTO tb_livro_genero (livro_id, genero_id) VALUES (1, 1), (1, 5);
INSERT INTO tb_livro_genero (livro_id, genero_id) VALUES (2, 2), (2, 5);
INSERT INTO tb_livro_genero (livro_id, genero_id) VALUES (3, 3), (3, 1);
INSERT INTO tb_livro_genero (livro_id, genero_id) VALUES (4, 4), (4, 5);
INSERT INTO tb_livro_genero (livro_id, genero_id) VALUES (5, 2), (5, 5);
INSERT INTO tb_livro_genero (livro_id, genero_id) VALUES (6, 3), (6, 1);
INSERT INTO tb_livro_genero (livro_id, genero_id) VALUES (7, 2), (7, 5);

INSERT INTO tb_emprestimo (usuario_id, livro_id, data_emprestimo, data_devolucao) VALUES (1, 1, '2025-05-01', '2025-05-08');
INSERT INTO tb_emprestimo (usuario_id, livro_id, data_emprestimo, data_devolucao) VALUES (2, 2, '2025-05-03', '2025-05-10');
INSERT INTO tb_emprestimo (usuario_id, livro_id, data_emprestimo, data_devolucao) VALUES (3, 3, '2025-05-05', '2025-05-12');