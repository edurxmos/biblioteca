ALTER TABLE tb_usuario RENAME TO tb_user;
ALTER TABLE tb_livro RENAME TO tb_book;
ALTER TABLE tb_genero RENAME TO tb_genre;
ALTER TABLE tb_livro_genero RENAME TO tb_book_genre;
ALTER TABLE tb_emprestimo RENAME TO tb_loan;

ALTER TABLE tb_user RENAME COLUMN nome TO name;
ALTER TABLE tb_user RENAME COLUMN saldo TO balance;

ALTER TABLE tb_book RENAME COLUMN nome TO title;
ALTER TABLE tb_book RENAME COLUMN autor TO author;
ALTER TABLE tb_book RENAME COLUMN disponivel TO available;
ALTER TABLE tb_book RENAME COLUMN preco TO price;


ALTER TABLE tb_genre RENAME COLUMN nome TO name;

ALTER TABLE tb_book_genre RENAME COLUMN livro_id TO book_id;
ALTER TABLE tb_book_genre RENAME COLUMN genero_id TO genre_id;

ALTER TABLE tb_loan RENAME COLUMN usuario_id TO user_id;
ALTER TABLE tb_loan RENAME COLUMN livro_id TO book_id;
ALTER TABLE tb_loan RENAME COLUMN data_emprestimo TO loan_date;
ALTER TABLE tb_loan RENAME COLUMN data_devolucao TO return_date;

ALTER TABLE tb_loan DROP PRIMARY KEY;
ALTER TABLE tb_loan ADD PRIMARY KEY (user_id, book_id);