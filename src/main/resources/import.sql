--Admin User Creation
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, '2015-11-05 14:29:36');
--Student User Creation
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'vegeta', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Saiyan', 'Ojji', 'saiyanprincebejita@user.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (3, 'maxpayne', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Maxine', 'Payne', 'maxpayne@user.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (4, 'goku', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Saiyan', 'Kakarot', 'songoku@user.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (5, 'testone', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Test', 'User1', 'testuser@test.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (6, 'testtwo', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Test', 'User2', 'testuser@test.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (7, 'testthree', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Test', 'User3', 'testuser@test.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (8, 'testfour', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Test', 'User4', 'testuser@test.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (9, 'testfive', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Test', 'User5', 'testuser@test.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (10, 'testsix', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Test', 'User6', 'testuser@test.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (11, 'testseven', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Test', 'User7', 'testuser@test.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (12, 'testeight', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Test', 'User8', 'testuser@test.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (13, 'testnine', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Test', 'User9', 'testuser@test.com', 1, '2015-11-05 14:29:36');

--Adding details for student users
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (1, "2017", 2, 0, 2);
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (2, "2017", 0, 0, 3);
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (3, "2017", 1, 0, 4);
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (4, "2017", 2, 0, 5);
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (5, "2017", 0, 0, 6);
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (6, "2017", 1, 0, 7);
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (7, "2017", 2, 0, 8);
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (8, "2017", 0, 0, 9);
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (9, "2017", 1, 0, 10);
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (10, "2017", 2, 0, 11);
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (11, "2017", 0, 0, 12);
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (12, "2017", 1, 0, 13);

--User Authorities
INSERT INTO authority (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO authority (ID, NAME) VALUES (2, 'ROLE_ADMIN');

--Providing users their authorities
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (3, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (4, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (5, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (6, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (7, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (8, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (9, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (10, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (11, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (12, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (13, 1);



--Adding subjects
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Strength Of Materials");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Engineering Design");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Hydraulics");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Fluid Mechanics");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Thermodynamics");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Automobile Engineering");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Random Subject 1");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Random Subject 2");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Random Subject 3");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Random Subject 4");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Random Subject 5");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Random Subject 6");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Random Subject 7");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Random Subject 8");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Random Subject 9");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Random Subject 10");

INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Automata");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Design and Analysis");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Compiler Design");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Object Oriented Programming");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Networking");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Automobile Engineering");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Random Subject 11");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Random Subject 12");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Random Subject 13");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Random Subject 14");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Random Subject 15");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Random Subject 16");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Random Subject 17");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Random Subject 18");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Random Subject 19");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Random Subject 20");

INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Microwave");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Microprocessor");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Signals and Systems");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "NAS");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Semiconductors");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Digital Logic Design");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 21");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 22");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 23");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 24");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 25");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 26");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 27");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 28");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 29");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 30");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 31");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 32");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 33");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 34");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 35");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 36");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 37");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 38");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 39");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 40");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 41");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 42");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 43");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 44");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 45");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 46");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 47");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 48");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 49");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Random Subject 50");











