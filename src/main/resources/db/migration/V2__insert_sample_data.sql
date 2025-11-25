INSERT INTO items (id, name, description, base_price, stock_quantity, has_variants)
VALUES (1, 'Plain T-Shirt', 'Basic unisex t-shirt', 75000, 100, FALSE);

INSERT INTO items (id, name, description, base_price, stock_quantity, has_variants)
VALUES (2, 'Running Shoes', 'Running shoes with size & color variants', NULL, 0, TRUE);

INSERT INTO item_variants (id, sku, size, color, price, stock_quantity, item_id)
VALUES (1, 'RS-42-BLK', '42', 'Black', 550000, 10, 2);

INSERT INTO item_variants (id, sku, size, color, price, stock_quantity, item_id)
VALUES (2, 'RS-43-BLU', '43', 'Blue', 560000, 5, 2);
