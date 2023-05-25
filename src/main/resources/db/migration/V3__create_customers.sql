CREATE TABLE customer(
	id INT PRIMARY KEY,
	first_name VARCHAR(20),
	last_name VARCHAR(20),
	dob DATE
);

CREATE TABLE customer_seq(next_val INT);
insert into customer_seq values(1);