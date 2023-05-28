CREATE TABLE model(id INT PRIMARY KEY, name VARCHAR(20) NOT NULL UNIQUE);
CREATE TABLE model_seq(next_val INT);
insert into model_seq values(1);