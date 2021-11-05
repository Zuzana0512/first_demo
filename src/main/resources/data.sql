INSERT INTO customers (name, address, email)
VALUES ('Zuzana', 'Amsterdam', 'zuzana@gmail.com');

INSERT INTO users (username, password, enabled)
VALUES
('admin', '$2a$12$0Win/adXs.HNhgszMNSjc.R3RngkwnkCGabtJsRtVYrn1Q.4Sz2vO' , true),
('user', '$2a$12$0Win/adXs.HNhgszMNSjc.R3RngkwnkCGabtJsRtVYrn1Q.4Sz2vO' , true),
('owner', '$2a$12$0Win/adXs.HNhgszMNSjc.R3RngkwnkCGabtJsRtVYrn1Q.4Sz2vO' , true),
('finadmin', '$2a$12$0Win/adXs.HNhgszMNSjc.R3RngkwnkCGabtJsRtVYrn1Q.4Sz2vO' , true),
('productmanag', '$2a$12$0Win/adXs.HNhgszMNSjc.R3RngkwnkCGabtJsRtVYrn1Q.4Sz2vO' , true);


INSERT INTO authorities (username, authority)
VALUES
    ('admin', 'ROLE_ADMIN'),
    ('admin', 'ROLE_USER'),
    ('admin', 'ROLE_OWNER'),
    ('admin', 'ROLE_FINADMIN'),
    ('admin', 'ROLE_PRODUCTMANAG'),
    ('owner', 'ROLE_OWNER'),
    ('finadmin', 'ROLE_FINADMIN'),
    ('productmanag', 'ROLE_PRODUCTMANAG'),
    ('user', 'ROLE_USER');