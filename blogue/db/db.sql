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