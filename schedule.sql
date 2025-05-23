use `schedule-app`;


-- create table (Lv.0 - 2)
CREATE TABLE schedule
(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT 'schedule identifier',
    username VARCHAR(50) NOT NULL COMMENT 'author name',
    contents TEXT NOT NULL COMMENT 'to do content',
    password VARCHAR(50) NOT NULL COMMENT 'password',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'date created',
    modified_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'date modified'
);


select *
from schedule;
