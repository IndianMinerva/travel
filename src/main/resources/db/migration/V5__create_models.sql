CREATE TABLE model(id INT PRIMARY KEY, name VARCHAR(20) NOT NULL UNIQUE);
CREATE TABLE IF NOT EXISTS model_seq(next_val INT);
insert into model_seq values(1);