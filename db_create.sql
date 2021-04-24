create database fin_per_assistant;
create user 'fin_user'@'localhost' identified by 'fin123';

grant all privileges on fin_per_assistant.* to 'fin_user'@'localhost';

flush privileges;
create table income( id varchar(255) primary key not null, name varchar(100), value double, income_date varchar(100), user_id int);
