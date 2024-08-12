BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

COMMIT TRANSACTION;

BEGIN TRANSACTION;

INSERT INTO topping (topping_id, topping_name)
VALUES (1, 'Pepperoni'), (2, 'Bacon'), (3, 'Ham'), (4, 'Mushrooms'), (5, 'Black Olives'), (6, 'Green Olives'), (7, 'Bell Pepper'),
		(8, 'Italian Sausage'), (9, 'Onions'), (10, 'Pineapple'), (11, 'Extra Cheese'), (12, 'Prosciutto'), (13, 'Meatballs'),
		(14, 'Anchioves'), (15, 'Ginger-Infused Pineapple'), (16, 'Truffles'), (17, 'Artichokes'), (18, 'Blue Cheese'), (19, 'Jalapenos'), (20, 'Ground Beef'), (21, 'Sloppy Joe Sauce');

-- ROLLBACK;

INSERT INTO diameter (size_name)
VALUES ('Small'), ('Medium'), ('Large'), ('Half-Sheet'), ('Full-Sheet');


INSERT INTO crust (crust_name)
VALUES ('Regular'), ('Thin'), ('Pan');


INSERT INTO sauce (sauce_name)
VALUES ('Traditional'), ('Basil Pesto'), ('Garlic Parmesean');

INSERT INTO specialty_pizza (specialty_pizza_id, specialty_pizza_name, base_price)
VALUES (1, 'Sloppy Joe Pizza', 25.00), (2, 'The Supreme', 23.00), (3, 'Meatlovers', 22.00), (4, 'Zesty Trio', 23.50);


INSERT INTO side (side_id, side_name, base_price)
VALUES (1, 'Garlic Bread Sticks', 7.00), (2, 'Cheese Bread', 10.00), (3, 'Caesar Salad', 10.00), (4, 'Mozzarella Sticks', 10.00),  (5, 'Buffalo Wings - 6pc', 10.00), (6, 'Buffalo Wings - 12pc', 16.00);

 INSERT INTO specialty_topping(specialty_id, topping_id)
 VALUES(1, 11), (1, 20), (1, 21), (2, 1), (2, 2), (2, 4), (2, 5), (2, 6), (2, 7), (2, 8), (2, 9), (3, 1), (3, 2), (3, 3), (3, 8), (3, 12), (3, 13), (4, 1), (4, 4), (4, 19);

-- INSERT INTO food_order VALUES(1, null, null);

COMMIT TRANSACTION;
--ROLLBACK