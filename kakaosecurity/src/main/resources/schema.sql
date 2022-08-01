create table customer
(
 id bigint generated by default as identity,
 customer_id int,
 name varchar(255),
 age int,
 enter_date varchar(10),
 primary key (id)
);


create table account
(
 id bigint generated by default as identity,
 account_no varchar(255),
 customer_id int,
  primary key (id)
);


create table transaction
(
 id bigint generated by default as identity,
 account_no varchar(255),
  trans_date varchar(10),
 transfer_yn varchar(10),
 ammount bigint,
 primary key (id)
);