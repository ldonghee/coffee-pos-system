-- 커피 메뉴 추가
INSERT INTO coffee_menu (coffee_menu_id, name, price, created_date, modified_date) values (1, '아이스 아메리카노', 4500, '2023-05-01 00:00:00', '2023-05-01 00:00:00');
INSERT INTO coffee_menu (coffee_menu_id, name, price, created_date, modified_date) values (2, '아이스 바닐라라떼', 5500, '2023-05-01 00:00:00', '2023-05-01 00:00:00');
INSERT INTO coffee_menu (coffee_menu_id, name, price, created_date, modified_date) values (3, '아이스 카라멜마끼야또', 5500, '2023-05-01 00:00:00', '2023-05-01 00:00:00');
INSERT INTO coffee_menu (coffee_menu_id, name, price, created_date, modified_date) values (4, '콜드 브루', 4500, '2023-05-01 00:00:00', '2023-05-01 00:00:00');
INSERT INTO coffee_menu (coffee_menu_id, name, price, created_date, modified_date) values (5, '아이스티', 3500, '2023-05-01 00:00:00', '2023-05-01 00:00:00');

-- 유저 추가
INSERT INTO users (user_id, name, created_date, modified_date) values (1, 'DHLEE', '2023-05-01 00:00:00', '2023-05-01 00:00:00');
INSERT INTO users (user_id, name, created_date, modified_date) values (2, 'TEST', '2023-05-01 00:00:00', '2023-05-01 00:00:00');
INSERT INTO users (user_id, name, created_date, modified_date) values (3, 'KIM', '2023-05-01 00:00:00', '2023-05-01 00:00:00');

-- 포인트 추가
INSERT INTO point (id, user_id, point, created_date, modified_date) values (1, 1, 20000, '2023-05-01 00:00:00', '2023-05-01 00:00:00');
INSERT INTO point (id, user_id, point, created_date, modified_date) values (2, 2, 20000, '2023-05-01 00:00:00', '2023-05-01 00:00:00');
INSERT INTO point (id, user_id, point, created_date, modified_date) values (3, 3, 20000, '2023-05-01 00:00:00', '2023-05-01 00:00:00');

