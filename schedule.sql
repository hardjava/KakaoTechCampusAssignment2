use `schedule-app`;


-- create table (Lv.0 - 2)
CREATE TABLE schedule
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'schedule identifier',
    username VARCHAR(50) NOT NULL COMMENT 'author name',
    contents TEXT NOT NULL COMMENT 'to do content',
    password VARCHAR(50) NOT NULL COMMENT 'password',
    created_at DATETIME NOT NULL COMMENT 'date created',
    modified_at DATETIME NOT NULL COMMENT 'date modified'
);


-- create table (Lv.3 - 6)
CREATE TABLE author
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'author identifier',
    email VARCHAR(50) NOT NULL UNIQUE COMMENT 'author email',
    name VARCHAR(50) NOT NULL comment 'name',
    created_at DATETIME NOT NULL COMMENT 'date created',
    modified_at DATETIME NOT NULL COMMENT 'date modified'
);

CREATE TABLE schedule
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'schedule identifier',
    author_id BIGINT NOT NULL COMMENT 'author FK',
    contents TEXT NOT NULL COMMENT 'to do content',
    password VARCHAR(50) NOT NULL COMMENT 'password',
    created_at DATETIME NOT NULL COMMENT 'date created',
    modified_at DATETIME NOT NULL COMMENT 'date modified',
    FOREIGN KEY (author_id) REFERENCES author (id) ON DELETE CASCADE
);







-- Test Query
select *
from author;

select *
from schedule;

delete from author;


-- drop talbe
DROP TABLE schedule;

drop table author;


-- select all using condition and descending by modified_at
select *
from schedule
where 1=1 and date(modified_at) = "2025-05-23"
order by modified_at desc;

-- paging
select *
from schedule s
join author a on s.author_id = a.id
order by s.modified_at desc
limit 10 offset 1;

-- Lv0~2 Test data
INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user1', '할 일 1', 'pass1', '2025-05-01 10:00:00', '2025-05-01 11:00:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user2', '할 일 2', 'pass2', '2025-05-02 09:30:00', '2025-05-03 14:00:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user3', '할 일 3', 'pass3', '2025-05-04 08:00:00', '2025-05-05 09:00:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user4', '할 일 4', 'pass4', '2025-05-06 12:00:00', '2025-05-07 13:15:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user5', '할 일 5', 'pass5', '2025-05-07 18:30:00', '2025-05-08 19:00:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user6', '할 일 6', 'pass6', '2025-05-10 15:00:00', '2025-05-11 15:05:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user7', '할 일 7', 'pass7', '2025-05-11 11:11:00', '2025-05-13 20:00:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user8', '할 일 8', 'pass8', '2025-05-12 06:30:00', '2025-05-14 07:45:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user9', '할 일 9', 'pass9', '2025-05-13 21:00:00', '2025-05-15 22:00:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user10', '할 일 10', 'pass10', '2025-05-14 09:00:00', '2025-05-17 09:10:00');


INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user1', '할 일 A', 'pass1', '2025-05-01 10:00:00', '2025-05-01 11:00:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user2', '할 일 B', 'pass2', '2025-05-01 10:00:00', '2025-05-02 09:00:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user3', '할 일 C', 'pass3', '2025-05-03 08:30:00', '2025-05-04 09:30:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user4', '할 일 D', 'pass4', '2025-05-03 08:30:00', '2025-05-04 09:30:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user5', '할 일 E', 'pass5', '2025-05-05 12:00:00', '2025-05-06 13:00:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user6', '할 일 F', 'pass6', '2025-05-06 14:30:00', '2025-05-07 15:30:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user7', '할 일 G', 'pass7', '2025-05-07 09:00:00', '2025-05-08 10:00:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user8', '할 일 H', 'pass8', '2025-05-08 09:00:00', '2025-05-08 10:00:00');  -- modified_at 중복

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user9', '할 일 I', 'pass9', '2025-05-09 07:00:00', '2025-05-09 08:00:00');

INSERT INTO schedule (username, contents, password, created_at, modified_at)
VALUES ('user10', '할 일 J', 'pass10', '2025-05-01 10:00:00', '2025-05-09 08:00:00');  -- created_at 중복




-- Lv3~5 paging test data
INSERT INTO author (email, name, created_at, modified_at) VALUES
                                                                  ( 'user1@example.com', 'User One', '2025-05-01 08:00:00', '2025-05-01 08:00:00'),
                                                                  ( 'user2@example.com', 'User Two', '2025-05-02 08:00:00', '2025-05-02 08:00:00'),
                                                                  ( 'user3@example.com', 'User Three', '2025-05-03 08:00:00', '2025-05-03 08:00:00'),
                                                                  ( 'user4@example.com', 'User Four', '2025-05-04 08:00:00', '2025-05-04 08:00:00'),
                                                                  ( 'user5@example.com', 'User Five', '2025-05-05 08:00:00', '2025-05-05 08:00:00');



INSERT INTO schedule (author_id, contents, password, created_at, modified_at) VALUES
                                                                                  (1, '할 일 1', 'pw1', '2025-05-01 09:00:00', '2025-05-01 10:00:00'),
                                                                                  (2, '할 일 2', 'pw2', '2025-05-02 10:00:00', '2025-05-03 11:00:00'),
                                                                                  (3, '할 일 3', 'pw3', '2025-05-03 08:30:00', '2025-05-04 09:30:00'),
                                                                                  (4, '할 일 4', 'pw4', '2025-05-04 08:00:00', '2025-05-05 09:00:00'),
                                                                                  (5, '할 일 5', 'pw5', '2025-05-05 12:00:00', '2025-05-06 13:00:00'),
                                                                                  (1, '할 일 6', 'pw6', '2025-05-06 12:00:00', '2025-05-07 13:15:00'),
                                                                                  (2, '할 일 7', 'pw7', '2025-05-07 09:00:00', '2025-05-08 10:00:00'),
                                                                                  (3, '할 일 8', 'pw8', '2025-05-08 09:00:00', '2025-05-08 10:00:00'),
                                                                                  (4, '할 일 9', 'pw9', '2025-05-09 07:00:00', '2025-05-09 08:00:00'),
                                                                                  (5, '할 일 10', 'pw10', '2025-05-10 15:00:00', '2025-05-11 15:05:00'),
                                                                                  (1, '할 일 11', 'pw11', '2025-05-11 11:11:00', '2025-05-13 20:00:00'),
                                                                                  (2, '할 일 12', 'pw12', '2025-05-12 06:30:00', '2025-05-14 07:45:00'),
                                                                                  (3, '할 일 13', 'pw13', '2025-05-13 21:00:00', '2025-05-15 22:00:00'),
                                                                                  (4, '할 일 14', 'pw14', '2025-05-14 09:00:00', '2025-05-17 09:10:00'),
                                                                                  (5, '할 일 15', 'pw15', '2025-05-15 10:00:00', '2025-05-18 11:20:00'),
                                                                                  (1, '할 일 16', 'pw16', '2025-05-16 14:00:00', '2025-05-19 15:00:00'),
                                                                                  (2, '할 일 17', 'pw17', '2025-05-17 10:30:00', '2025-05-20 11:30:00'),
                                                                                  (3, '할 일 18', 'pw18', '2025-05-18 12:00:00', '2025-05-21 13:00:00'),
                                                                                  (4, '할 일 19', 'pw19', '2025-05-19 13:15:00', '2025-05-22 14:15:00'),
                                                                                  (5, '할 일 20', 'pw20', '2025-05-20 14:30:00', '2025-05-23 15:30:00');
