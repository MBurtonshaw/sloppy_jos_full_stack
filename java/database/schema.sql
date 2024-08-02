BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS sauce CASCADE;  
DROP TABLE IF EXISTS topping CASCADE;
DROP TABLE IF EXISTS diameter CASCADE;
DROP TABLE IF EXISTS crust CASCADE;
DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS item_topping CASCADE;


CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);


---sauces----------------------------------------------

CREATE TABLE sauce(
	sauce_id serial NOT NULL,
	sauce_name varchar(20) NOT NULL,
	CONSTRAINT PK_sauce PRIMARY KEY (sauce_id)
);



---toppings----------------------------------------------------
CREATE TABLE topping (
	topping_id SERIAL NOT NULL,
	topping_name varchar(36),
	CONSTRAINT PK_topping PRIMARY KEY (topping_id)
);


------diameter------------------------------------------------
CREATE TABLE diameter (
	size_id SERIAL NOT NULL,
	size_name varchar(36),
	CONSTRAINT PK_size PRIMARY KEY (size_id)
);

-----crust----------------------------------------------------
CREATE TABLE crust (
	crust_id SERIAL NOT NULL,
	crust_name varchar(36),
	CONSTRAINT PK_crust PRIMARY KEY (crust_id)
);

-------item table----------------------------------------------
CREATE TABLE item(
	item_id SERIAL NOT NULL,
	sauce_id int,
	topping_id int,
	crust_id int,
	CONSTRAINT PK_item PRIMARY KEY (item_id),
	CONSTRAINT FK_item_sauce FOREIGN KEY (sauce_id) REFERENCES sauce (sauce_id),
 	CONSTRAINT FK_item_item_topping FOREIGN KEY (topping_id) REFERENCES topping (topping_id),
	CONSTRAINT FK_item_crust FOREIGN KEY (crust_id) REFERENCES crust (crust_id)
);

CREATE TABLE item_topping (
	item_id int,
	topping_id int,
	CONSTRAINT PK_item_topping PRIMARY KEY(item_id, topping_id),
	CONSTRAINT FK_item_topping_item FOREIGN KEY (item_id) REFERENCES item (item_id),
	CONSTRAINT FK_item_topping_topping FOREIGN KEY (topping_id) REFERENCES topping (topping_id)
);

-- ALTER TABLE item ADD CONSTRAINT FK_item_item_topping FOREIGN KEY (topping_id) REFERENCES topping (topping_id);

-- ----specialty order-------------------------------------
-- CREATE TABLE specialty_pizza (
--     specialty_pizza_Id  SERIAL NOT NULL,
--     name   varchar NOT NULL
-- 	CONSTRAINT PK 
-- );


-- CREATE TABLE specialty_pizza_order
--     specialty_pizza_Id int NOT NULL
--     order_id int NOT NULL
-- CONSTRAINT PK specialty_pizza_order FOREIGN KEY (specialty_pizza, order_id) REFERENCES (specialty_pizza, Food_Order, ),


-- ROLLBACK;


COMMIT TRANSACTION;




