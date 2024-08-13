CREATE TABLE tb_pedido (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mesa INT NOT NULL,
    comanda VARCHAR(50) NOT NULL,
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE tb_prato (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fk_pedido INT,
    prato VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    observacao VARCHAR(255),
    FOREIGN KEY (fk_pedido) REFERENCES tb_pedido(id) ON DELETE CASCADE
);

CREATE TABLE tb_bebida (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fk_pedido INT,
    bebida VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    observacao VARCHAR(255),
    FOREIGN KEY (fk_pedido) REFERENCES tb_pedido(id) ON DELETE CASCADE
);

CREATE TABLE tb_sobremesa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fk_pedido INT,
    sobremesa VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    observacao VARCHAR(255),
    FOREIGN KEY (fk_pedido) REFERENCES tb_pedido(id) ON DELETE CASCADE
);