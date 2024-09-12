CREATE TABLE "order"
(
    order_id   SERIAL PRIMARY KEY,
    product_id INT         NOT NULL,
    user_id    INT         NOT NULL,
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status     VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "user" (user_id) on delete cascade,
    FOREIGN KEY (product_id) REFERENCES product (product_id) on delete cascade
);
