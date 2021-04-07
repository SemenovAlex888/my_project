DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM restaurant;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User1', 'user1@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin'),
       ('User2', 'user2@yandex.ru', 'password'),
       ('User3', 'user3@yandex.ru', 'password'),
       ('User4', 'user4@yandex.ru', 'password');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001),
       ('USER', 100001),
       ('USER', 100002),
       ('USER', 100003),
       ('USER', 100004);

INSERT INTO restaurant (name, address)
VALUES ('Mansarda', 'Pochtamtskaya st., 3'),
       ('Milano', 'Moskovsky prospect, 97A'),
       ('Italy', 'Bolshaya Morskaya st., 14'),
       ('Gastronomica', 'st. Marata, 5/21'),
       ('Moscow', 'Nevsky prospect, 114');

INSERT INTO dishes(restaurant_id, name, price)
VALUES (100005, 'English breakfast', 750.00),
       (100005, 'Mozzarella and shrimp salad', 950.00),
       (100005, 'Beef Pho Bo', 1200.00),

       (100006, 'Salmon, poached egg', 490.00),
       (100006, 'Zucchini and apple salad', 450.00),
       (100006, 'Classic Miso soup', 250.00),

       (100007, 'Combo breakfast', 510.00),
       (100007, 'Octopus salad with honey', 1000.00),
       (100007, 'Tom Yum Potak', 650.00),

       (100008, 'Beefsteak with egg and bacon', 470.00),
       (100008, 'Crispy duck', 700.00),
       (100008, 'Meat solyanka', 550.00),

       (100009, 'Sunny side up eggs', 200.00),
       (100009, 'Salmon poke', 750.00),
       (100009, 'Mushroom soup', 400.00);





