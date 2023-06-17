create database jwt;

use jwt;
SET FOREIGN_KEY_CHECKS=0;

CREATE TABLE `users` (
  id int(11) NOT NULL AUTO_INCREMENT,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE `roles` (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

create table user_roles(
	id int(11) NOT NULL AUTO_INCREMENT primary key,
	user_id int(11) NOT NULL ,
    role_id int(11) NOT NULL ,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);



INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

insert into users (username ,email, password)
values('Anh', 'abc@gmail.com',  '123456'),
('The', 'hust@gmail.com',  '123456'),
('Le', 'soict@gmail.com',  '123456');

insert into user_roles(user_id, role_id)
values(1, 2),
(1,1),
(1,3),
(2,1),
(3,1),
(3,2);

