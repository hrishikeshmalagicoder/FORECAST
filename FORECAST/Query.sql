
create table validate_user(username varchar(100),password varchar(100));
insert into  validate_user values ('hrishikesh','hrishikesh');


create table api_tracker(id int,name varchar(50),request_type varchar(10),lower_bound int,upper_bound int,response varchar(3000));

select * from api_tracker;

delete from api_tracker;


create table temperature(time int,city varchar(50), temperature int);

insert into temperature values(6,'bangalore',45);

insert into temperature values(5,'mumbai',44);

select * from temperature where temperature=35;


select * from temperature;


// JDBC AWS RDS URL

"jdbc:mysql://prozeal.c4zmwip5h5pg.us-east-2.rds.amazonaws.com:3306/prozeal","bhanusimha","ProZeal123!"

Note : Please dont misuse this ..


