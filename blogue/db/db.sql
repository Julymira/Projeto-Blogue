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

ALTER TABLE posts
ADD COLUMN title VARCHAR(150);

CREATE TABLE COMMENTS(
	id bigserial not null primary key,
	comment_text varchar(500) not null,
	user_id bigint not null references USERS(id),
	post_id bigint not null references POSTS(id)
)