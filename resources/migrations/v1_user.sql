CREATE TABLE "user"
(
    user_id  SERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    email    VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name     VARCHAR(100) NOT NULL
);

INSERT INTO "user" (username, email, password, name)
VALUES ('admin', 'admin@teste.com', '123456', 'Admin');