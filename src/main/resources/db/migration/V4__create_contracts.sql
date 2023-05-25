create table contract(
    id INT PRIMARY KEY,
    rate DOUBLE,
    customer_id INT,
    CONSTRAINT fk_contract_customer_customer_id FOREIGN KEY(customer_id) REFERENCES customer(id)
);

CREATE TABLE contract_seq(next_val INT);
insert into contract_seq values(1);