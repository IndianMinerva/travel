CREATE TABLE brand(id INT PRIMARY KEY, name VARCHAR(20) NOT NULL UNIQUE);
CREATE TABLE brand_seq(next_val INT);
insert into brand_seq values(1);