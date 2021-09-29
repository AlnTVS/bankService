drop table if exists clients cascade;
create table clients (id bigserial, name varchar(255), surname varchar(255), age int, primary key(id));
insert into clients
(name, surname, age) values
('Name1', 'Surname1', 20),
('Name2', 'Surname2', 21),
('Name3', 'Surname3', 23),
('Name4', 'Surname4', 19),
('Name5', 'Surname5', 32),
('Name6', 'Surname6', 33),
('Name7', 'Surname7', 41),
('Name8', 'Surname8', 18),
('Name9', 'Surname9', 55),
('Name10', 'Surname10', 36),
('Name11', 'Surname11', 40),
('Name12', 'Surname12', 20),
('Name13', 'Surname13', 41),
('Name14', 'Surname14', 58),
('Name15', 'Surname15', 99);

drop table if exists banks cascade;
create table banks (id bigserial, name varchar(255), primary key(id));
insert into banks
(name) values
('ООО Банк1'),
('ЗАО Банк2'),
('ПАО Рога и Копыта Банк3');


drop table if exists bank_accounts cascade;
create table bank_accounts (id bigserial, number varchar(20), balance numeric(16,2), hold_balance numeric(16,2), client_id bigint references clients(id), bank_id bigint references banks(id), primary key(id));
insert into bank_accounts
(number, balance, hold_balance, client_id, bank_id) values
('1111 1111 1111 1101', 1000.00, 0.00, 1, 1),
('1111 1111 1111 1102', 1000.00, 0.00, 2, 2),
('1111 1111 1111 1103', 1000.00, 0.00, 3, 3),
('1111 1111 1111 1104', 1000.00, 0.00, 4, 1),
('1111 1111 1111 1105', 1000.00, 0.00, 5, 2),
('1111 1111 1111 1106', 1000.00, 0.00, 6, 3),
('1111 1111 1111 1107', 1000.00, 0.00, 7, 1),
('1111 1111 1111 1108', 1000.00, 0.00, 8, 2),
('1111 1111 1111 1109', 1000.00, 0.00, 9, 3),
('1111 1111 1111 1110', 1000.00, 0.00, 10, 1),
('1111 1111 1111 1111', 1000.00, 0.00, 11, 2),
('1111 1111 1111 1112', 1000.00, 0.00, 12, 3),
('1111 1111 1111 1113', 1000.00, 0.00, 13, 1),
('1111 1111 1111 1114', 1000.00, 0.00, 14, 2),
('1111 1111 1111 1115', 1000.00, 0.00, 15, 3),
('2111 1111 1111 1101', 1000.00, 0.00, 1, 2),
('2111 1111 1111 1102', 1000.00, 0.00, 2, 3),
('2111 1111 1111 1103', 1000.00, 0.00, 3, 1),
('2111 1111 1111 1104', 1000.00, 0.00, 4, 2);
