CREATE TABLE tb_dom_status(
    id INT PRIMARY KEY AUTO_INCREMENT,
    status char NOT NULL
);

CREATE TABLE tb_dom_cargo_funcionario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    cargo VARCHAR(255) NOT NULL,
    descricao TEXT
);

CREATE TABLE tb_dom_tipo_produto (
    id INT PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(255) NOT NULL
);

CREATE TABLE tb_funcionario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(255),
    fk_cargo_funcionario INT,
    FOREIGN KEY (fk_cargo_funcionario) REFERENCES tb_dom_cargo_funcionario(id) 
);

CREATE TABLE tb_produto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    link_imagem VARCHAR(255) NOT NULL DEFAULT 'https://cdn.neemo.com.br/uploads/settings_webdelivery/logo/1209/nao-perfil.gif',
    fk_tipo_produto INT,
    FOREIGN KEY (fk_tipo_produto) REFERENCES tb_dom_tipo_produto(id)
);

CREATE TABLE tb_pedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    mesa INT NOT NULL,
    comanda VARCHAR(50) NOT NULL,
    data_hora_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fk_funcionario INT,
    FOREIGN KEY (fk_funcionario) REFERENCES tb_funcionario(id)
);

CREATE TABLE tb_itens_pedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    quantidade INT NOT NULL,
    observacao TEXT,
    fk_pedido INT NOT NULL,
    fk_produto INT NOT NULL,
    FOREIGN KEY (fk_pedido) REFERENCES tb_pedido(id),
    FOREIGN KEY (fk_produto) REFERENCES  tb_produto(id)
);

CREATE TABLE tb_status_pedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao_status VARCHAR(50) NOT NULL,
    fk_funcionario INT,
    fk_pedido INT NOT NULL,
  	fk_status INT NOT NULL,
    FOREIGN KEY (fk_funcionario) REFERENCES tb_funcionario(id),
    FOREIGN KEY (fk_pedido) REFERENCES tb_pedido(id),
    FOREIGN KEY (fk_status) REFERENCES tb_dom_status(id)
);



