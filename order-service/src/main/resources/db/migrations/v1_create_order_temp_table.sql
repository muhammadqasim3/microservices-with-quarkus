CREATE TABLE order_temp (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    order_date TIMESTAMP DEFAULT now()
);
