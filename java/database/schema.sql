BEGIN TRANSACTION;

DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS sauce CASCADE;  
DROP TABLE IF EXISTS topping CASCADE;
DROP TABLE IF EXISTS diameter CASCADE;
DROP TABLE IF EXISTS crust CASCADE;
DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS item_topping CASCADE;
DROP TABLE IF EXISTS specialty_pizza CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS side CASCADE;
DROP TABLE IF EXISTS food_order CASCADE;
DROP TABLE IF EXISTS specialty_pizza CASCADE;



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

-- ----specialty pizzas-------------------------------------
CREATE TABLE specialty_pizza (
 specialty_pizza_id  SERIAL NOT NULL,
 name varchar NOT NULL,
 base_price decimal,
 CONSTRAINT PK_specialty_pizza PRIMARY KEY (specialty_pizza_id)
);


------Customer Table---------------
CREATE TABLE customer (
    customer_id SERIAL NOT NULL,
    first_name varchar(50),
	last_name varchar(50),
	street_address int,
	city varchar(25),
	phone_number char(10),
	email_address varchar(50),
	is_registered boolean,
	credit_card char(16),
	customer_fav varchar(50),
	CONSTRAINT PK_customer PRIMARY KEY (customer_id)
);

-----side table----------------------
CREATE TABLE side (
	side_id SERIAL NOT NULL, 
	side_name varchar(50),
	base_price decimal,
	CONSTRAINT PK_side PRIMARY KEY (side_id)
);

------Order Table-----------------
CREATE TABLE food_order (
	food_order_id SERIAL NOT NULL,
	item_id int,
	user_id int,
	customer_id int,
	side_id int,
	specialty_pizza_id int,
	CONSTRAINT PK_food_order PRIMARY KEY (food_order_id),
	CONSTRAINT FK_food_order_item FOREIGN KEY (item_id) REFERENCES item (item_id),
	CONSTRAINT FK_food_order_users FOREIGN KEY (user_id) REFERENCES users (user_id),
	CONSTRAINT FK_food_order_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id),
	CONSTRAINT FK_food_order_side FOREIGN KEY (side_id) REFERENCES side (side_id),
	CONSTRAINT FK_food_order_specialty_pizza FOREIGN KEY (specialty_pizza_id) REFERENCES specialty_pizza (specialty_pizza_id)
);

-- ROLLBACK;


COMMIT TRANSACTION;




