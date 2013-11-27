DROP TABLE IF EXISTS T_USER;
DROP TABLE IF EXISTS T_ACCOUNT;
CREATE TABLE T_ACCOUNT (
  id bigint NOT NULL AUTO_INCREMENT,
  identifier varchar(255) NOT NULL,
  uuid varchar(255) NOT NULL,
  name varchar(255),
  PRIMARY KEY (id),
  UNIQUE KEY identifier (identifier)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE T_USER (
  id bigint NOT NULL AUTO_INCREMENT,
  email varchar(255) NOT NULL,
  firstname varchar(60),
  lastname varchar(60),
  openid varchar(1024),
  account_id bigint NOT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY email (email),
  CONSTRAINT FK_ACCOUNT_USER FOREIGN KEY (account_id) REFERENCES T_ACCOUNT (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data
insert into T_ACCOUNT (identifier, uuid, name) values ('nguyen_tan_nguyen','d58d7686-76cc-428e-b844-71efbb8e7727', 'Think Smart Inc');
insert into T_USER (email,firstname,lastname,account_id) values ('ntnguyen2603@gmail.com','Tan Nguyen', 'Nguyen',1);

