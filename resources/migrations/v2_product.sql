CREATE TABLE product
(
    product_id  SERIAL PRIMARY KEY,
    name        VARCHAR(100)   NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    stock       INT            NOT NULL
);

INSERT INTO product (name, description, price, stock)
VALUES ('Marmita 1', 'Carne de gado, arroz e feijão', 23.00, 10),
       ('Marmita 2', 'Carne de frango, arroz e feijão', 18.00, 20),
       ('Marmita 3', 'Carne de porco, arroz e feijão', 20.00, 30),
       ('Marmita 4', 'Carne de sol, arroz e feijão', 25.00, 40),
       ('Marmita 5', 'Peixe, arroz e feijão', 30.00, 50);