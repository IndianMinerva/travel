CREATE TABLE vehicle(
	id INT PRIMARY KEY,
	brand_id INT,
	model_id INT,
	model_year INT(4),
	vin VARCHAR(10) NULL UNIQUE,
	price DOUBLE,
	CONSTRAINT fk_brand_id FOREIGN KEY(brand_id) REFERENCES brand(id),
	CONSTRAINT fk_model_id FOREIGN KEY(model_id) REFERENCES model(id)
);