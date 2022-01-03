INSERT INTO products (id, price, title)
VALUES (1, 855.55, 'Cheese'),
       (2, 105.00, 'Beer'),
       (3, 75.80, 'Milk'),
       (4, 355.10, 'Tomato'),
       (5, 49.65, 'Milk');

ALTER SEQUENCE product_seq RESTART WITH 6;