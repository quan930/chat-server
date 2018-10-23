create table users (
	 id char(20) not null,
	 password char(10),
	 primary key (id)
 );

select * from users

insert into users values('0000','0000',true);
insert into users values('0001','0001',null);
insert into users values('0002','0002',null);
insert into users values('0003','0003',null);
insert into users values('0004','0004',null);


truncate  table users;