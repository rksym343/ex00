create table tbl_member (
	userid varchar(50) not null,
	userpw varchar(50) not null,
	username varchar(50) not null,
	email varchar(100),
	regdate timestamp default now(),
	updatedate timestamp default now(),
	primary key(userid)
);

alter table tbl_board add column replycnt int default 0;

delete from tbl_board where bno < 550;

select count(bno) from tbl_board;

update tbl_board set replycnt = ( select count(rno) from tbl_reply where bno = tbl_board.bno);


CREATE DATABASE spring;

create table spring.project(
	pno int not null auto_increment,
	p_name varchar(100) not null,
	p_detail text,
	start_date timestamp,
	end_date timestamp,
	p_condition varchar(5),
	primary key(pno)
);


INSERT INTO spring.project (p_name, p_detail, start_date, end_date, p_condition)
VALUES('프로젝트1', '프로젝트 설명1', '2017-01-01', '2017-02-01', '준비'),
('프로젝트2', '프로젝트 설명1', '2017-03-01', '2017-05-01', '종료'),
('프로젝트3', '프로젝트 설명1', '2017-04-01', '2017-08-01', '진행중'),
('프로젝트4', '프로젝트 설명1', '2017-08-01', '2017-10-01', '보류');

select max(pno) from spring.project;
