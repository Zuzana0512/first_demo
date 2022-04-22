INSERT INTO customers (firstname, lastname, address, email)
VALUES
    ('Zuzana', 'Sorokova', 'Amsterdam', 'zuzana@gmail.com'),
    ('Jos', 'Huisje', 'Den Haag', 'jos@gmail.com'),
    ('Wilma', 'Lamp', 'Bergen op Zoom', 'wilma@gmail.com');

INSERT INTO products (name, kind, packaging, description, price, vat_percentage)
VALUES
    ('White Widow', 'sativa', '3 zaden', 'White widow is een zogenaamde "witte" wietsoort met een hoog THC-gehalte. De plant wordt van origine ca. 75 centimeter hoog. Een plant brengt tussen de 100 en 150 gram droge wiet op.', 34.95, 9),
    ('Amnesia Haze', 'sativa/indica', '5 zaden', 'Amnesia Haze, ook wel bekend als "ami wiet" of "ammo wiet", trakteert op een bijna psychedelische high die je geest de stratosfeer in blaast.Hoewel Amnesia Haze buiten wel 210cm hoog kan worden, wordt ze bij binnenteelt rond de 140cm.', 55.90, 9);

INSERT INTO suppliers (name, address, product)
VALUES
    ('Dutch Passion', 'Amsterdam', 'White Widow'),
    ('Sensi Seeds', 'Groningen', 'Nothern Light');

INSERT INTO stock (product_name, product_amount, supplier_name)
VALUES
    ('White Widow', 100, 'Dutch Passion'),
    ('Amnesia Haze', 230, 'Sensi Seeds');

INSERT INTO order_rows(product_id, quantity)
VALUES
    (1, 3),
    (2, 9);


INSERT INTO orders (customer_id, date, paid, delivered)
VALUES
    (1, '2021-10-23', true, true),
    (2, '2021-10-30', false, false);

INSERT INTO payments (date, paid, order_id)
VALUES
    ('2021-10-23', true, 1);

INSERT INTO users (username, password, enabled)
VALUES
    ('admin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica' , true),
    ('user', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica' , true),
    ('finadmin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica' , true),
    ('productmanager', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica' , true);


INSERT INTO authorities (username, authority)
VALUES
    ('admin', 'ROLE_ADMIN'),
    ('finadmin', 'ROLE_FINADMIN'),
    ('productmanager', 'ROLE_PRODUCTMANAGER'),
    ('user', 'ROLE_USER');