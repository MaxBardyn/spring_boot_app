CREATE DATABASE IF NOT EXISTS bardyn_lab3;

USE bardyn_lab3;

# ================================= DROP TABLES =================================

DROP TABLE IF EXISTS artist_has_event;
DROP TABLE IF EXISTS user_has_ticket;
DROP TABLE IF EXISTS artist;
DROP TABLE IF EXISTS ticket;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS sex;
DROP TABLE IF EXISTS event;
DROP TABLE IF EXISTS ticket_office;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS city;

# ================================= CREATE NEW TABLES =================================

CREATE TABLE sex (
	id INT PRIMARY KEY AUTO_INCREMENT,
	sex VARCHAR(45) NOT NULL
);

CREATE TABLE artist (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(45) NOT NULL,
	surname VARCHAR(45) NOT NULL,
	age INT,
	alias VARCHAR(15),
	birthday_date DATE,
	sex_id INT
  );

CREATE TABLE city (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    postal_code VARCHAR(6)
    );

CREATE TABLE location (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(45),
	address VARCHAR(45) NOT NULL,
	city_id INT NOT NULL
 );

CREATE TABLE event (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  date DATE NOT NULL,
  location_id INT NOT NULL
  );

CREATE TABLE artist_has_event (
  artist_id INT NOT NULL,
  event_id INT NOT NULL,
  PRIMARY KEY (artist_id, event_id)
);

CREATE TABLE ticket_office (
  id INT PRIMARY KEY AUTO_INCREMENT,
  card_payment TINYINT NOT NULL,
  location_id INT NOT NULL
  );

CREATE TABLE ticket (
  id INT PRIMARY KEY AUTO_INCREMENT,
  price INT NOT NULL,
  sector VARCHAR(10),
  place_number VARCHAR(15) NOT NULL,
  event_id INT NOT NULL,
  ticket_office_id INT NOT NULL
 );
 
CREATE TABLE user (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(15) NOT NULL,
  surname VARCHAR(15) NOT NULL,
  age INT,
  birthday_date DATE NOT NULL,
  sex_id INT
  );

CREATE TABLE user_has_ticket (
  user_id INT NOT NULL,
  ticket_id INT NOT NULL,
  PRIMARY KEY (user_id, ticket_id)
);

# ================================= FOREIGN KEYS =================================

ALTER TABLE artist
ADD CONSTRAINT fk_artist_sex
    FOREIGN KEY (sex_id)
    REFERENCES sex (id)
    ON DELETE SET NULL
    ON UPDATE NO ACTION;
    
ALTER TABLE location
ADD CONSTRAINT fk_location_city
    FOREIGN KEY (city_id)
    REFERENCES city (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE event 
ADD CONSTRAINT fk_event_location
    FOREIGN KEY (location_id)
    REFERENCES location (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE artist_has_event
ADD CONSTRAINT fk_artist_has_event_artist
    FOREIGN KEY (artist_id)
    REFERENCES artist (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    
ADD CONSTRAINT fk_artist_has_event_event
    FOREIGN KEY (event_id)
    REFERENCES event (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE ticket_office 
ADD CONSTRAINT fk_ticket_office_location
    FOREIGN KEY (location_id)
    REFERENCES location (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
    
ALTER TABLE ticket
ADD CONSTRAINT fk_ticket_event
    FOREIGN KEY (event_id)
    REFERENCES event (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    
ADD CONSTRAINT fk_ticket_ticket_office
    FOREIGN KEY (ticket_office_id)
    REFERENCES ticket_office (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE user
ADD CONSTRAINT fk_user_sex
    FOREIGN KEY (sex_id)
    REFERENCES sex (id)
    ON DELETE SET NULL
    ON UPDATE NO ACTION;
    
ALTER TABLE user_has_ticket
ADD CONSTRAINT fk_user_has_ticket_ticket
    FOREIGN KEY (ticket_id)
    REFERENCES ticket (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    
ADD CONSTRAINT fk_user_has_ticket_user
    FOREIGN KEY (user_id)
    REFERENCES user (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
# ================================= FILL THE TABLES =================================
     
INSERT INTO sex (sex) VALUES
('male'),
('female');

INSERT INTO artist (name, surname, age, alias, birthday_date, sex_id) VALUES
('Billie', 'Eilish', 18, NULL, '2001-12-18', 2),
('Rakim ', 'Mayers', 32, 'ASAP Rocky', '1988-10-3', 1),
('Darold ', 'Brown ', 32, 'ASAP Ferg', '2001-10-20', 1),
('Unknown', 'Unknown', NULL, NULL, NULL, 2),
('Artist5', 'Artist5', NULL, NULL, '2000-01-01', 2),
('Artist6', 'Artist6', 21, NULL, '1999-02-18', 1),
('Artist7', 'Artist7', 45, 'Unknown', '1975-6-01', 2),
('Artist8', 'Artist8', 12, 'Nameless', NULL, 1),
('Artist9', 'Artist9', 33, NULL, '1987-03-14', 2),
('Artist10', 'Artist10', 24, NULL, '1996-05-20', 1);
     
INSERT INTO city (name, postal_code) VALUES
('Lviv', '79000'),
('Sambir', '81400'),
('Krivoy Rog', '80100'),
('Kyiv', '01000'),
('Skole', '10200'),
('Lutsk', '80200'),
('Odesa', "65000"),
('Ternopil', '83500'),
('Stryi', '82400'),
('Drohobych', '85200');

INSERT INTO location (name, address, city_id) VALUES
('NSK Olimpiyskiy', 'Velyka Vasylkivska',4),
('Dovzhenko Theater', 'Dovzhenka Street', 1),
('Arena Lviv', 'Stryjska', 1);
    
INSERT INTO event (name, date, location_id) VALUES
('Birthday Party1', '2020-08-11', 1),
('Party2', '2020-08-11', 2),
('New Year Party', '2021-01-01', 3),
('Birthday Party4', '2020-08-11', 1),
('Birthday Party5', '2020-08-11', 2),
('Birthday Party6', '2020-08-11', 3),
('Birthday Party7', '2020-08-11', 3),
('Birthday Party8', '2020-08-11', 2),
('Birthday Party9', '2020-08-11', 2),
('Birthday Party10', '2020-08-11', 1);
    
INSERT INTO artist_has_event(artist_id, event_id) VALUES
(1,2),
(2,3),
(3,4),
(4,5),
(5,6),
(5,7),
(7,8),  
(3,3),
(9,2),
(2,9);
    
INSERT INTO ticket_office (card_payment, location_id) VALUES
(1,1),
(0,2),
(0,3),
(1,3),
(0,2),
(1,1),
(1,1),
(1,2),
(1,3),
(0,1);
    
INSERT INTO ticket(price, sector, place_number, event_id,ticket_office_id) VALUES
(1300, '1B', '32A', 2, 3),
(2100, NULL, '4', 3, 4),
(200, '32B', '65', 5, 6),
(500, '48H', '11', 7, 7),
(100, NULL, '65', 8, 1),
(350, '1B', '2', 10, 4),
(500, NULL, '1', 2, 9),
(900, '1B', '92B', 4, 10),
(300, '1B', '31', 1, 3),
(60, NULL, '142', 7, 2);
    
INSERT INTO user(name, surname, age, birthday_date, sex_id) VALUES
('User1', 'User1', 19, '2001-10-20', 1),
('User2', 'User2', 32, '2001-10-20', 2),
('User3', 'User3', 14, '2001-10-20', 1),
('User4', 'User4', 64, '2001-10-20', 2),
('User5', 'User5', 12, '2001-10-20', 1),
('User6', 'User5', 76, '2001-10-20', 2),
('User7', 'User6', 54, '2001-10-20', 2),
('User8', 'User7', 45, '2001-10-20', 2),
('User9', 'User8', 32, '2001-10-20', 1),
('User10', 'User10', 45, '2001-10-20', 1);
    
INSERT INTO user_has_ticket(user_id, ticket_id) VALUES
(1,2),
(2,3),
(3,4),
(4,6),
(6,5),
(8,1),
(2,9),
(9,10),
(10,7),
(2,8);
    
# ================================= CREATE INDEXES =================================
    
CREATE INDEX user_age ON user(age);

CREATE INDEX ticket_price ON ticket(price);
