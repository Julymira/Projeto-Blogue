CREATE TABLE USERS(
	id bigserial not null primary key,
	name varchar(100) not null,
	email varchar(100) not null,
	password varchar(100) not null
)

CREATE TABLE POSTS(
	id bigserial not null primary key,
	post_text varchar(150) not null,
	dateTime timestamp not null,
	user_id bigint not null references USERS(id)
)

ALTER TABLE posts
ADD COLUMN image_url VARCHAR(255);

ALTER TABLE posts
ALTER COLUMN post_text TYPE VARCHAR(1000);