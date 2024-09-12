CREATE TABLE payment
(
    payment_id SERIAL PRIMARY KEY,
    order_id   INT            NOT NULL,
    date       DATE           NOT NULL,
    method     VARCHAR(50)    NOT NULL,
    amount     DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES "order" (order_id) on delete cascade
);