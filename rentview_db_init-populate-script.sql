-- *************** DATA INSERTION *****************
-- Optimal insertion order: MEMBER, DIRECTOR, GENRE, MOVIE,
-- ... MOVIE_DIRECTOR, MOVIE_GENRE, REVIEW, RENTAL, PAYMENT
-- *************************************************

INSERT INTO MEMBER (email_address, hashed_password, first_name, last_name, date_of_birth, member_type)
    VALUES
    ('safhossain338@gmail.com', '98ab44a8', 'Safwan', 'Hossain', '2002-10-22', 'manager'),
    ('s2hossain@torontomu.ca', '15d0cbde', 'admin', 'admin', '2000-10-21', 'manager'),
    ('alice@example.com', '2f0e8570', 'Alice', 'Wonder', '1990-05-15', 'user', '1234', 'Visa', '2025-12-31'),
    ('BobJohn@example.com', '531142f', 'Bob', 'John', '1985-01-30', 'user', '5678', 'MasterCard', '2024-11-30'),
    ('CBrown@example.com', 'dcbca3ba', 'Chris', 'Brown', '1989-05-05', 'user', '9012', 'Amex', '2023-09-15'),
    ('D1Wilson@example.com', 'f0772152', 'Derek', 'Wilson', '1992-08-24', 'user'),
    ('ethan69@example.com', '97319cf0', 'Ethan', 'Smith', '2000-03-12', 'user');

INSERT INTO DIRECTOR (first_name, last_name)
    VALUES
    ('Peter', 'Jackson'),
    ('Chris', 'Columbus'),
    ('Greta', 'Gerwig'),
    ('John', 'Hughes'),
    ('Andrew', 'Stanton'),
    ('Robert', 'Zemeckis'),
    ('John', 'Lasseter'),
    ('Lana', 'Wachowski'),
    ('Christopher', 'Nolan'),
    ('Roger', 'Allers'),
    ('Lilly', 'Wachowski');

INSERT INTO GENRE (genre_type)
    VALUES
    ('Fantasy'),
    ('Adventure'),
    ('Drama'),
    ('Comedy'),
    ('Animation'),
    ('Romance'),
    ('Family');

INSERT INTO MOVIE (movie_name, release_year, rental_cost, movie_image_path, is_movie_featured)
    VALUES
    ('Lord of the Rings Fellowship of the Ring', 2001, 3.99, '/resources/movie_posters/lord_of_the_rings_fellowship_of_the_ring.jpg', TRUE),
    ('Harry Potter', 2001, 2.99, '/resources/movie_posters/harry_potter.jpg', TRUE),
    ('Little Women', 2019, 3.49, '/resources/movie_posters/little_women.jpg', TRUE),
    ('Home Alone', 1990, 1.99, '/resources/movie_posters/home_alone.jpg', FALSE),
    ('Finding Nemo', 2003, 2.49, '/resources/movie_posters/finding_nemo.jpg', FALSE),
    ('Forrest Gump', 1994, 2.99, '/resources/movie_posters/forrest_gump.jpg', FALSE),
    ('Toy Story', 1995, 2.99, '/resources/movie_posters/toy_story.jpg', FALSE),
    ('The Matrix', 1999, 3.99, '/resources/movie_posters/the_matrix.jpg', FALSE),
    ('Inception', 2010, 3.99, '/resources/movie_posters/inception.jpg', FALSE),
    ('The Lion King', 1994, 2.99, '/resources/movie_posters/the_lion_king.jpg', FALSE),
    ('Oppenheimer', 2023, 4.99, '/resources/movie_posters/oppenheimer.jpg', TRUE);

INSERT INTO MOVIE_DIRECTOR (movie_ID, director_ID)
    VALUES
    (1, 1), -- Lord of the Rings Fellowship of the Ring by Peter Jackson
    (2, 2), -- Harry Potter by Chris Columbus
    (3, 3), -- Little Women by Greta Gerwig
    (4, 4), -- Home Alone by John Hughes
    (5, 5), -- Finding Nemo by Andrew Stanton
    (6, 6), -- Forrest Gump by Robert Zemeckis
    (7, 7), -- Toy Story by John Lasseter
    (8, 8), -- The Matrix by Lana Wachowski
    (8, 11), -- The Matrix ALSO by Lilly Wachowski
    (9, 9), -- Inception by Christopher Nolan
    (10, 10), -- The Lion King by Roger Allers
    (11, 9); -- Oppenheimer by Christopher Nolan

-- Associating movies with multiple genres
INSERT INTO MOVIE_GENRE (movie_ID, genre_ID)
    VALUES
    (1, 1), -- Lord of the Rings Fellowship of the Ring is Fantasy
    (1, 2), -- Lord of the Rings Fellowship of the Ring is also Adventure
    (2, 2), -- Harry Potter is Adventure
    (2, 7), -- Harry Potter is also Family
    (3, 3),
    (3, 6),
    (4, 4),
    (4, 7),
    (5, 5),
    (5, 7),
    (6, 6),
    (6, 4), 
    (7, 5),
    (7, 7),
    (8, 1),
    (8, 2),
    (9, 2),
    (9, 3),
    (10, 5),
    (10, 7);

INSERT INTO REVIEW (review_description, rating, member_ID, movie_ID)
    VALUES
    ('Great movie!', 5, 3, 1),
    ('Loved it!', 4, 4, 2),
    ('Very entertaining', 4, 5, 3),
    ('A classic', 5, 6, 4),
    ('Heartwarming story', 5, 7, 5);

INSERT INTO RENTAL (rental_date, return_date, member_ID, movie_ID)
    VALUES
    ('2024-02-01', '2024-02-13', 3, 1), -- Rental ends before current date
    ('2024-02-10', '2024-02-22', 4, 2), -- Rental ends before current date
    ('2024-02-15', '2024-02-27', 5, 3),
    ('2024-02-05', '2024-02-17', 6, 4),
    ('2024-02-20', '2024-03-04', 7, 5);

INSERT INTO PAYMENT (amount, payment_date, rental_ID, member_ID)
    VALUES
    (3.99, '2024-02-05', 1, 3),
    (2.99, '2024-02-15', 2, 4),
    (3.49, '2024-02-20', 3, 5),
    (1.99, '2024-02-10', 4, 6),
    (2.49, '2024-02-25', 5, 7); 
