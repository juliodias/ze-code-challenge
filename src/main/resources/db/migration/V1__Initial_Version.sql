CREATE TABLE IF NOT EXISTS partners(
id SERIAL,
created_at TIMESTAMP NOT NULL,
updated_at TIMESTAMP NOT NULL,
external_id UUID NOT NULL,
owner_name VARCHAR(255) NOT NULL,
trading_name VARCHAR(255) NOT NULL,
document VARCHAR(255) UNIQUE NOT NULL,
address geometry NOT NULL,
coverage_area geometry NOT NULL
);