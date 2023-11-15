CREATE TABLE IF NOT EXISTS vehicle(
	id INT PRIMARY KEY,
	version INT,
	brand_id INT,
	model_id INT,
	model_year INT,
	vin VARCHAR NULL UNIQUE,
	price DOUBLE PRECISION,
	contract_id INT NULL,
	CONSTRAINT fk_vehicle_bland_brand_id FOREIGN KEY(brand_id) REFERENCES brand(id),
	CONSTRAINT fk_vehicle_model_model_id FOREIGN KEY(model_id) REFERENCES model(id),
	CONSTRAINT fk_vehicle_contract_contract_id FOREIGN KEY(contract_id) REFERENCES contract(id)
);


CREATE SEQUENCE IF NOT EXISTS vehicle_seq INCREMENT BY 50;