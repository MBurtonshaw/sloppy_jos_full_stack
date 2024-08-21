INSERT INTO food_order VALUES(1)

SELECT * FROM food_order

SELECT * FROM food_order_specialty

SELECT * FROM food_order_side

SELECT * FROM food_order_item

SELECT * FROM item

SELECT food_order_item.food_order_id, item.item_id, item.sauce_name, item.crust_name, item.size_name FROM item 
JOIN food_order_item ON item.item_id = food_order_item.item_id WHERE food_order_item.food_order_id = 11 AND item.item_id = 1;

SELECT food_order_id, user_id, customer_id FROM food_order;

SELECT food_order.food_order_id, food_order.user_id, food_order.customer_id, specialty_pizza.specialty_pizza_name, food_order_item.item_id, side.side_name FROM food_order
JOIN food_order_specialty ON food_order.food_order_id = food_order_specialty.food_order_id
INNER JOIN specialty_pizza ON food_order_specialty.specialty_pizza_id = specialty_pizza.specialty_pizza_id
JOIN food_order_item ON food_order.food_order_id = food_order_item.food_order_id
JOIN food_order_side ON food_order.food_order_id = food_order_side.food_order_id
JOIN side ON food_order_side.side_id = side.side_id;


select * from side

select * from specialty_pizza