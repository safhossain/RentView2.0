-- *************** TABLE CREATION *****************
-- Optimal creation order: MEMBER, DIRECTOR, GENRE, MOVIE,
-- ... MOVIE_DIRECTOR, MOVIE_GENRE, REVIEW, RENTAL, PAYMENT
-- *************************************************
CREATE TABLE MEMBER (
	member_ID INT UNIQUE NOT NULL AUTO_INCREMENT,
	email_address VARCHAR(255) UNIQUE NOT NULL,
	hashed_password VARCHAR(255) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	date_of_birth DATE NOT NULL,
        member_type ENUM('manager', 'user') NOT NULL DEFAULT 'user',
	last_four_digits CHAR(4),
	card_type VARCHAR(20),
	expiration_date DATE,
	PRIMARY KEY(member_id)
);
CREATE TABLE DIRECTOR (
	director_ID INT PRIMARY KEY AUTO_INCREMENT,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL
);
CREATE TABLE GENRE (
	genre_ID INT PRIMARY KEY AUTO_INCREMENT,
	genre_type VARCHAR(50) NOT NULL
);
CREATE TABLE MOVIE (
	movie_ID INT PRIMARY KEY AUTO_INCREMENT,
	movie_name VARCHAR(255) NOT NULL,
	release_year YEAR NOT NULL,
	rental_cost DECIMAL(10, 2) NOT NULL,
        movie_image_path VARCHAR(255),
        is_movie_featured BOOLEAN DEFAULT FALSE
);
CREATE TABLE MOVIE_DIRECTOR (
	movie_ID INT AUTO_INCREMENT,
	director_ID INT,
	PRIMARY KEY (movie_ID, director_ID),
	FOREIGN KEY (movie_ID) REFERENCES MOVIE(movie_ID),
	FOREIGN KEY (director_ID) REFERENCES DIRECTOR(director_ID)
);
CREATE TABLE MOVIE_GENRE (
	movie_ID INT,
	genre_ID INT,
	PRIMARY KEY (movie_ID, genre_ID),
	FOREIGN KEY (movie_ID) REFERENCES MOVIE(movie_ID),
	FOREIGN KEY (genre_ID) REFERENCES GENRE(genre_ID)
);
CREATE TABLE REVIEW (
	review_ID INT PRIMARY KEY AUTO_INCREMENT,
	review_description TEXT,
	rating INT CHECK (rating >= 1 AND rating <= 5),
	member_ID INT,
	movie_ID INT,
	FOREIGN KEY (member_ID) REFERENCES MEMBER(member_ID),
	FOREIGN KEY (movie_ID) REFERENCES MOVIE(movie_ID)
);
CREATE TABLE RENTAL (
	rental_ID INT PRIMARY KEY AUTO_INCREMENT,
	rental_date DATE NOT NULL,
	return_date DATE,
	member_ID INT,
	movie_ID INT,
	FOREIGN KEY (member_ID) REFERENCES MEMBER(member_ID),
	FOREIGN KEY (movie_ID) REFERENCES MOVIE(movie_ID)
);
CREATE TABLE PAYMENT (
	payment_ID INT PRIMARY KEY AUTO_INCREMENT,
	amount DECIMAL(10, 2) NOT NULL,
	payment_date DATE NOT NULL,
	rental_ID INT,
	member_ID INT,
	FOREIGN KEY (rental_ID) REFERENCES RENTAL(rental_ID),
	FOREIGN KEY (member_ID) REFERENCES MEMBER(member_ID)
);
