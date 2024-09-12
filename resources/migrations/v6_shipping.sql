CREATE TABLE shipping
(
    shipping_id SERIAL PRIMARY KEY,
    order_id    INT REFERENCES "order" (order_id) on delete cascade,
    address     VARCHAR(255) NOT NULL,
    city        VARCHAR(100) NOT NULL,
    cep         VARCHAR(9)   NOT NULL,
    state_id    INT          NOT NULL,
    status      VARCHAR(20)  NOT NULL,
    FOREIGN KEY (state_id) REFERENCES state (state_id) on delete cascade
);