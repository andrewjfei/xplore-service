CREATE TABLE if NOT EXISTS "user" (
	id 				UUID		PRIMARY KEY,
	username 		VARCHAR		UNIQUE 		NOT NULL,
	first_name 		VARCHAR		NOT NULL,
	last_name		VARCHAR 	NOT NULL,
	email			VARCHAR 	UNIQUE		NOT NULL,
	mobile			VARCHAR 	UNIQUE		NOT NULL,
	password        VARCHAR     NOT NULL,
	is_verified		BOOLEAN		DEFAULT 	FALSE,
	created 		TIMESTAMP	NOT NULL	DEFAULT     CURRENT_TIMESTAMP,
	last_modified	TIMESTAMP,
	deleted			TIMESTAMP
);

INSERT INTO "user" VALUES
('11afa5b4-c622-4320-a4e9-7c374172b63d', 'johnsmith', 'John', 'Smith', 'johnsmith@xplore.com', '0210000000', 'password', false),
('26770bad-887c-4ef7-a77c-f582d50e201c', 'caseywang', 'Casey', 'Wang', 'caseywang@xplore.com', '0210000001', 'password', true),
('ee934861-9c00-4795-b78d-0bb760517dd4', 'remmyjones', 'Remmy', 'Jones', 'remmyjones@xplore.com', '0210000002', 'password', false);
