CREATE TABLE vehicle(
	id INT PRIMARY KEY,
	brand_id INT,
	model_id INT,
	year INT(4),
	vin VARCHAR(10) NULL UNIQUE,
	price DOUBLE,
	contract_id INT,
	CONSTRAINT fk_vehicle_bland_brand_id FOREIGN KEY(brand_id) REFERENCES brand(id),
	CONSTRAINT fk_vehicle_model_model_id FOREIGN KEY(model_id) REFERENCES model(id),
	CONSTRAINT fk_vehicle_contract_contract_id FOREIGN KEY(contract_id) REFERENCES contract(id)
);


CREATE TABLE vehicle_seq(next_val INT);
insert into vehicle_seq values(1);