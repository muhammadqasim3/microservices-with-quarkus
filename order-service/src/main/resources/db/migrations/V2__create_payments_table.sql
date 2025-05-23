CREATE TABLE payments (
    id SERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    payment_status VARCHAR(50) NOT NULL,
    amount NUMERIC(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT now()
);
