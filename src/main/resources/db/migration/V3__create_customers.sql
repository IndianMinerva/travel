CREATE TABLE IF NOT EXISTS customer(
	id INT PRIMARY KEY,
	version INT,
	first_name VARCHAR(20),
	last_name VARCHAR(20),
	dob DATE
);

CREATE SEQUENCE IF NOT EXISTS customer_seq INCREMENT BY 50;
--insert into customer_seq values(1);