-- USER
DROP sequence if EXISTS user_seq;
CREATE sequence user_seq start 1 increment 1;

DROP TABLE if EXISTS users CASCADE;
CREATE TABLE users
(
        id int8 NOT NULL,
        archive boolean NOT NULL,
        email varchar(255),
        name varchar(255),
        password varchar(255),
        role varchar(255),
        PRIMARY KEY (id)
);

-- BUCKET
DROP sequence if EXISTS bucket_seq;
CREATE sequence bucket_seq start 1 increment 1;

DROP TABLE if EXISTS buckets CASCADE;
CREATE TABLE buckets
(
        id int8 NOT NULL,
        user_id int8,
        PRIMARY KEY (id)
);

ALTER TABLE if EXISTS buckets
        ADD CONSTRAINT buckets_fk_users
            FOREIGN KEY (user_id) REFERENCES users;

-- CATEGORY
DROP sequence if EXISTS category_seq;
CREATE sequence category_seq start 1 increment 1;

DROP TABLE if EXISTS categories CASCADE;
CREATE TABLE categories
(
        id int8 NOT NULL,
        title varchar(255),
        PRIMARY KEY (id)
);

-- PRODUCT
DROP sequence if EXISTS product_seq;
CREATE sequence product_seq start 1 increment 1;

DROP TABLE if EXISTS products CASCADE;
CREATE TABLE products
(
        id int8 NOT NULL,
        price numeric(19, 2),
        title varchar(255),
        PRIMARY KEY (id)
);

-- CATEGORY AND PRODUCT
DROP TABLE if EXISTS products_categories CASCADE;
CREATE TABLE products_categories
(
        product_id int8 NOT NULL,
        category_id int8 NOT NULL
);

ALTER TABLE if EXISTS products_categories
        ADD CONSTRAINT products_categories_fk_categories
            FOREIGN KEY (category_id) REFERENCES categories;
ALTER TABLE if EXISTS products_categories
        ADD CONSTRAINT products_categories_fk_products
            FOREIGN KEY (product_id) REFERENCES products;

-- PRODUCT IN BUCKET
DROP TABLE if EXISTS buckets_products CASCADE;
CREATE TABLE buckets_products
(
        bucket_id int8 NOT NULL,
        product_id int8 NOT NULL
);

ALTER TABLE if EXISTS buckets_products
        ADD CONSTRAINT buckets_products_fk_products
            FOREIGN KEY (product_id) REFERENCES products;
ALTER TABLE if EXISTS buckets_products
        ADD CONSTRAINT buckets_products_fk_buckets
            FOREIGN KEY (bucket_id) REFERENCES buckets;

-- ORDERS
DROP sequence if EXISTS order_seq;
CREATE sequence order_seq start 1 increment 1;

CREATE TABLE orders
(
        id int8 NOT NULL,
        address varchar(255),
        created timestamp,
        status varchar(255),
        sum numeric(19, 2),
        updated timestamp,
        user_id int8,
        PRIMARY KEY (id)
);

ALTER TABLE if EXISTS orders
        ADD CONSTRAINT orders_fk_users
            FOREIGN KEY (user_id) REFERENCES users;

-- ORDER DETAILS
DROP sequence if EXISTS order_details_seq;
CREATE sequence order_details_seq start 1 increment 1;

DROP TABLE if EXISTS orders_details CASCADE;
CREATE TABLE orders_details
(
        id int8 NOT NULL,
        amount numeric(19, 2),
        price numeric(19, 2),
        order_id int8,
        product_id int8,
        details_id int8 NOT NULL,
        PRIMARY KEY (id)
);

ALTER TABLE if EXISTS orders_details
        ADD CONSTRAINT orders_details_fk_orders
            FOREIGN KEY (order_id) REFERENCES orders;
ALTER TABLE if EXISTS orders_details
        ADD CONSTRAINT orders_details_fk_products
            FOREIGN KEY (product_id) REFERENCES products;
ALTER TABLE if EXISTS orders_details
        ADD CONSTRAINT orders_details_fk_orders_details
            FOREIGN KEY (details_id) REFERENCES orders_details;


--ALTER TABLE if EXISTS orders_details ADD CONSTRAINT orders_details_uk_details_id UNIQUE (details_id)