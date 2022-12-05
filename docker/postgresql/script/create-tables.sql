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