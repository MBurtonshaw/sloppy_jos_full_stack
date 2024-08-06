BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

COMMIT TRANSACTION;

BEGIN TRANSACTION;

INSERT INTO topping (topping_name)
VALUES ('Pepperoni'), ('Bacon'), ('Ham'), ('Mushrooms'), ('Black Olives'), ('Green Olives'), ('Bell Pepper'),
		('Italian Sausage'), ('Onions'), ('Pineapple'), ('Extra Cheese'), ('Prosciutto'), ('Meatballs'),
		('Anchioves'), ('Ginger-Infused Pineapple'), ('Truffles'), ('Artichokes'), ('Blue Cheese');

-- ROLLBACK;


INSERT INTO diameter (size_name)
VALUES ('Small'), ('Medium'), ('Large'), ('Half-Sheet'), ('Full-Sheet');


INSERT INTO crust (crust_name)
VALUES ('Regular'), ('Thin'), ('Pan');


INSERT INTO sauce (sauce_name)
VALUES ('Traditional'), ('Basil Pesto'), ('Garlic Parmesean');


INSERT INTO specialty_pizza (name, base_price)
VALUES ('Sloppy Joe Pizza', 25.00), ('The Supreme', 23.00), ('Meatlovers', 22.00);
