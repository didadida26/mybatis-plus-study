use test;
CREATE TABLE student (
                         id bigint(20) primary key auto_increment,
                         name varchar(32) not null,
                         password  varchar(32) not null,
                         age int(3) not null ,
                         tel varchar(32) not null
);
insert into student values(null,'tom','123456',12,'12345678910');
insert into student values(null,'jack','123456',8,'12345678910');
insert into student values(null,'jerry','123456',15,'12345678910');
insert into student values(null,'tom','123456',9,'12345678910');
insert into student values(null,'snake','123456',28,'12345678910');
insert into student values(null,'张益达','123456',22,'12345678910');
insert into student values(null,'张大炮','123456',16,'12345678910');

select * from student;