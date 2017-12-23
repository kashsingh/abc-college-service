INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'vegeta', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Saiyan', 'Ojji', 'saiyanprincebejita@user.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (3, 'maxpayne', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Maxine', 'Payne', 'maxpayne@user.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (4, 'goku', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'Saiyan', 'Kakarot', 'songoku@user.com', 1, '2015-11-05 14:29:36');

INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (1, "2017", 2, 0, 2);
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (2, "2017", 0, 0, 3);
INSERT INTO student (id, batch, course_id, current_semester, user_id) VALUES (3, "2017", 1, 0, 4);

INSERT INTO authority (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO authority (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (3, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (4, 1);


INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Strength Of Materials");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Engineering Design");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Hydraulics");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Fluid Mechanics");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Thermodynamics");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Automobile Engineering");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Automata");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Design and Analysis");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Compiler Design");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Object Oriented Programming");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Networking");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 1, "Automobile Engineering");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Microwave");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Microprocessor");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Signals and Systems");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "NAS");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Semiconductors");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 2, "Digital Logic Design");









