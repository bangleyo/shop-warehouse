DROP TABLE IF EXISTS item_variants;
DROP TABLE IF EXISTS items;

CREATE TABLE items (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(255) NOT NULL UNIQUE,
                       description VARCHAR(1000),
                       base_price DECIMAL(19, 2),
                       stock_quantity INT DEFAULT 0,
                       has_variants BOOLEAN NOT NULL
);

CREATE TABLE item_variants (
                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               sku VARCHAR(255) NOT NULL,
                               size VARCHAR(100),
                               color VARCHAR(100),
                               price DECIMAL(19, 2) NOT NULL,
                               stock_quantity INT NOT NULL,
                               item_id BIGINT NOT NULL,
                               CONSTRAINT fk_item_variant_item
                                   FOREIGN KEY (item_id)
                                       REFERENCES items(id)
                                       ON DELETE CASCADE
);

CREATE INDEX idx_item_variants_item_id ON item_variants(item_id);
CREATE INDEX idx_item_variants_sku ON item_variants(sku);
