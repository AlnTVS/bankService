drop table if exists transactions cascade;
create table transactions (id bigserial, card_num_tx varchar(20), card_num_rx varchar(20), sum_of_transaction int, date_of_transaction timestamp, primary key(id));
