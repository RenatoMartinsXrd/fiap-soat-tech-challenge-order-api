CREATE TABLE order_entity_product (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    order_entity_id BIGINT REFERENCES order_entity(id),
    product_id BIGINT
);