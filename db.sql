-- TODO 
-- Add Database creation automation
-- Assign Staff 

CREATE TABLE Users_tbl (
	id INTeger PRIMARY KEY AUTOINCREMENT NOT NULL,
  	username TEXT,
 	password TEXT,
    address TEXT,
  	first_name TEXT,
    email TEXT,
    phone_number TEXT,
    last_name TEXT,
    uta_id INT,
  	user_type INT
);

INSERT INTO Users_tbl VALUES(1, "wolf", "password123", "hi street", "James", "james@yahoo.com", "6388823299", "Farshow", 1004544300, 1);
INSERT INTO Users_tbl VALUES(2, "minnie", "king123", "nice street", "Jordan", "jkmao@yahoo.com", "6388822299", "Clarke", 1004542300, 2);
INSERT INTO Users_tbl VALUES(3, "gordon", "cook123", "chef street", "Gordon", "gramsay@yahoo.com", "6388821299", "Ramsay", 1002542300, 3);
INSERT INTO Users_tbl VALUES(4, "foodie", "eat123", "lol street", "Jason", "jasonoo@yahoo.com", "6388722299", "Kasprowicz", 1004542303, 1);

CREATE TABLE Hall_tbl (
	hall_id INTeger PRIMARY KEY AUTOINCREMENT NOT NULL,
  	name TEXT,
 	price INTEGER,
    capacity INTEGER,
    address TEXT
);


INSERT INTO Hall_tbl VALUES(1, "Maverick Hall", 50, 150, "Planet UTA");
INSERT INTO Hall_tbl VALUES(2, "Arlington Hall", 60, 250, "Planet Arlington");
INSERT INTO Hall_tbl VALUES(3, "KC Hall", 70, 350, "Planet Tarrant");
INSERT INTO Hall_tbl VALUES(4, "Shard Hall", 80, 100, "Planet DFW");
INSERT INTO Hall_tbl VALUES(5, "Liberty Hall", 50, 20, "Planet TX");

CREATE TABLE Events_tbl (
	event_id INTeger PRIMARY KEY AUTOINCREMENT NOT NULL,
  event_name TEXT,
  event_type TEXT,
 bookedDateStart TEXT,
bookedDateEnd TEXT,
desired_attendees INTEGER,
  alco_or_not INTEGER,
    hall_id INTEGER,
	user_id INTEGER,
    formality INTEGER,
    status INTEGER,
    CONSTRAINT fk_hall FOREIGN KEY (hall_id) REFERENCES Hall_tbl(hall_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES Users_tbl(user_id)
    
);

INSERT INTO Events_tbl VALUES(1, "My Own Event", "Party", "2018-04-22 01:22:20", "2018-04-22 04:22:20",50, 0, 2, 1, 1, 0);
INSERT INTO Events_tbl VALUES(2, "Dads Own Event", "Party", "2018-04-23 01:22:20", "2018-04-23 04:22:20",50, 1, 3, 2, 1, 0);
-- By Default we keep events as 0 as pending
-- 1 as Accepted
-- 2 as Declined
-- SELECT * FROM 'Events_tbl' INNER JOIN Hall_tbl WHERE Hall_tbl.hall_id = Events_tbl.hall_id;

CREATE TABLE bookedSlot (
	bookedDateStart TEXT,
bookedDateEnd TEXT,
    hall_id INTEGER,
   user_id INTEGER,
CONSTRAINT fk_hall_user
FOREIGN KEY(user_id)
REFERENCES Users_tbl(user_id),
  	CONSTRAINT fk_hall1
    FOREIGN KEY (hall_id)
    REFERENCES Hall_tbl(hall_id)
);

InSERT INTO bookedSlot VALUES("2018-04-22 01:22:20", "2018-04-22 04:22:20", 2, 1);


CREATE TABLE venue_tbl(
	venue_id INTEGER,
	name_venue TEXT
);

INSERT INTO venue_tbl VALUES(100, "Pizza");
INSERT INTO venue_tbl VALUES(101, "French");
INSERT INTO venue_tbl VALUES(102, "Chinese");
INSERT INTO venue_tbl VALUES(103, "Mexican");
INSERT INTO venue_tbl VALUES(104, "Italian");
INSERT INTO venue_tbl VALUES(105, "American");
INSERT INTO venue_tbl VALUES(106, "Greek");
INSERT INTO venue_tbl VALUES(107, "Indian");
INSERT INTO venue_tbl VALUES(108, "Japanese");

CREATE TABLE drinks_tbl(
	drink_id INTEGER,
	name_drink TEXT
);

INSERT INTO drinks_tbl VALUES(200, "Alcoholic");
INSERT INTO drinks_tbl VALUES(201, "Non Alcoholic");

CREATE TABLE meal_tbl(
	meal_type_id INTEGER,
	name_meal TEXT
);


INSERT INTO meal_tbl VALUES(300, "Breakfast");
INSERT INTO meal_tbl VALUES(301, "Lunch");
INSERT INTO meal_tbl VALUES(302, "Dinner");

CREATE TABLE event_resources(
	name TEXT,
	price INTEGER,
	drink_id INTEGER,
        venue_id INTEGER,
        meal_type_id INTEGER,
	event_id INTEGER,
	quantity INTEGER,
    FOREIGN KEY (event_id)
    REFERENCES Events_tbl(event_id),
    FOREIGN KEY (drink_id)
    REFERENCES drink_tbl(drink_id),
    FOREIGN KEY (venue_id)
    REFERENCES venue_tbl(venue_id),
    FOREIGN KEY (meal_type_id)
    REFERENCES meal_tbl(meal_type_id)
);

INSERT INTO event_resources VALUES("Venue", 40, NULL, 106, NULL, 1, 1);

-- SELECT strftime('%H', bookedDate) FROM 'bookedSlot WHERE hall_id=2';
-- SELECT date(bookedDate) FROM 'bookedSlot' WHERE hall_id=2;

