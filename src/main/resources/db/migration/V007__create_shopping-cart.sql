CREATE TABLE IF NOT EXISTS shopping_cart (
    id INT PRIMARY KEY,
    order_id uuid NOT NULL,
    client_id INT REFERENCES client(id) NOT NULL,
    item_id INT REFERENCES item(id) NOT NULL,
    quantity_item INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);