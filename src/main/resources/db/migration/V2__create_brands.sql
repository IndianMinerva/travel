CREATE TABLE brand(id INT PRIMARY KEY, name VARCHAR(20) NOT NULL UNIQUE);
CREATE SEQUENCE IF NOT EXISTS brand_seq;
--insert into brand_seq values(1);