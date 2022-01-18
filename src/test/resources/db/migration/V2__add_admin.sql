INSERT INTO users (id, archive, email, name, password, role)
VALUES(1, false, 'admin@eshop.com', 'Admin', 'pass', 'ADMIN');

ALTER sequence user_seq RESTART WITH 2;