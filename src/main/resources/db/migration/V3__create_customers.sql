CREATE TABLE customer(
	id INT PRIMARY KEY,
	version INT,
	first_name VARCHAR(20),
	last_name VARCHAR(20),
	dob DATE
);

CREATE TABLE IF NOT EXISTS customer_seq(next_val INT);
insert into customer_seq values(1);