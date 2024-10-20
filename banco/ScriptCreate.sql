CREATE TABLE tb_dom_status_processo(
    id smallint PRIMARY KEY,
    status char NOT NULL,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE tb_dom_status_geral(
    id smallint PRIMARY KEY,
    status char NOT NULL,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE tb_dom_cargo_funcionario (
    id smallint PRIMARY KEY,
    cargo VARCHAR(255) NOT NULL,
    descricao TEXT
);

CREATE TABLE tb_dom_tipo_produto (
    id smallint PRIMARY KEY,
	nome VARCHAR(255) NOT NULL
);

CREATE TABLE tb_funcionario (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(255),
    fk_cargo_funcionario smallint,
    FOREIGN KEY (fk_cargo_funcionario) REFERENCES tb_dom_cargo_funcionario(id) 
);

CREATE TABLE tb_produto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    link_imagem VARCHAR(255) DEFAULT 'https://cdn.neemo.com.br/uploads/settings_webdelivery/logo/1209/nao-perfil.gif',
    fk_tipo_produto smallint  NOT NULL,
    fk_status_geral smallint NOT NULL DEFAULT 1,
    FOREIGN KEY (fk_status_geral) REFERENCES tb_dom_status_geral(id),
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

CREATE TABLE tb_controle_status_item_pedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    descricao_status VARCHAR(50),
    data_hora_iniciado TIMESTAMP,
    data_hora_pronto TIMESTAMP,
    data_hora_entregue TIMESTAMP,
    fk_funcionario INT,
  	fk_status_processo smallint NOT NULL DEFAULT 1,
    FOREIGN KEY (fk_funcionario) REFERENCES tb_funcionario(id),
    FOREIGN KEY (fk_status_processo) REFERENCES tb_dom_status_processo(id)
);

CREATE TABLE tb_item_pedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    observacao TEXT,
    fk_pedido INT NOT NULL,
    fk_produto INT NOT NULL,
    fk_controle_status_item_pedido INT NOT NULL,
    FOREIGN KEY (fk_pedido) REFERENCES tb_pedido(id),
    FOREIGN KEY (fk_produto) REFERENCES  tb_produto(id),
    FOREIGN KEY (fk_controle_status_item_pedido) REFERENCES tb_controle_status_item_pedido(id)
);


CREATE TABLE tb_login (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fk_funcionario INT NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL, -- Armazenar a senha em formato hash
    fk_status_geral smallint NOT NULL DEFAULT 1,
    FOREIGN KEY (fk_status_geral) REFERENCES tb_dom_status_geral(id),
    FOREIGN KEY (fk_funcionario) REFERENCES tb_funcionario(id)
);

-- insert domains
insert into tb_dom_tipo_produto(id,nome) values
(1, 'Prato'),
(2, 'Bebida'),
(3, 'Sobremesa');

insert into tb_dom_cargo_funcionario(id,cargo,descricao) values
(1, 'Cozinheiro', "Prepara pratos e sobremesas"),
(2, 'Garçon', "Serve pratos, sobremesas e bebidas"),
(3, 'Gerente', "Gerenecia Cozinheiro e Garçon");

insert into tb_dom_status_processo(id,status,descricao) values
(1, 'A','A Fazer'),
(2, 'F','Fazendo'),
(3, 'P','Pronto'),
(4, 'E','Entregue'),
(5, 'C','Cancelado');

insert into tb_dom_status_geral(id,status,descricao) values
(1, 'A','Ativo'),
(2, 'D','Desativado');
