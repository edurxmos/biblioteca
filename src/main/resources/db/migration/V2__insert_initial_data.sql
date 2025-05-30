INSERT INTO tb_usuario (nome, email) VALUES
    ('João Silva', 'joao@email.com'),
    ('Eduardo Ramos', 'eduardo@email.com'),
    ('Gabriela Melquiades', 'gabriela@email.com'),
    ('Nelio Alves', 'nelio@email.com'),
    ('Marco Camara', 'camara@email.com');

INSERT INTO tb_livro (nome, autor, disponivel) VALUES
    ('Dom Quixote', 'Miguel de Cervantes', false),
    ('1984', 'George Orwell', false),
    ('O Senhor dos Anéis', 'J.R.R. Tolkien', false),
    ('Orgulho e Preconceito', 'Jane Austen', true),
    ('Cem Anos de Solidão', 'Gabriel García Márquez', true),
    ('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', true),
    ('A Revolução dos Bichos', 'George Orwell', true),
    ('Frankenstein', 'Mary Shelley', true),
    ('O Apanhador no Campo de Centeio', 'J.D. Salinger', true),
    ('O Pequeno Príncipe', 'Antoine de Saint-Exupéry', true);

INSERT INTO tb_genero (id, nome) VALUES
    (1, 'Aventura'),
    (2, 'Ficção'),
    (3, 'Fantasia'),
    (4, 'Romance'),
    (5, 'Drama');

INSERT INTO tb_livro_genero (livro_id, genero_id) VALUES
    (1, 1), (1, 5),
    (2, 2), (2, 5),
    (3, 3), (3, 1),
    (4, 4), (4, 5),
    (5, 2), (5, 5),
    (6, 3), (6, 1),
    (7, 2), (7, 5),
    (8, 2), (8, 5),
    (9, 5),
    (10, 2),
    (10, 4);

INSERT INTO tb_emprestimo (usuario_id, livro_id, data_emprestimo, data_devolucao) VALUES
    (1, 1, '2025-05-01', '2025-05-08'),
    (2, 2, '2025-05-03', '2025-05-10'),
    (3, 3, '2025-05-05', '2025-05-12');