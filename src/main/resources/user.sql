create table user_transactions(
id bigint auto_increment primary key,
user_Id bigint,
amount int not null,
transaction_Date timestamp,
foreign key (user_Id) references Users (id) on delete cascade
);

create table users(
id bigint auto_increment primary key,
name varchar(100) not null unique,
balance int not null
);