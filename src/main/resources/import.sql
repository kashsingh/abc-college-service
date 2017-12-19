INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (2, 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', 1, '2015-11-05 14:29:36');
INSERT INTO user (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) VALUES (3, 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', 0, '2015-11-05 14:29:36');

INSERT INTO authority (ID, NAME) VALUES (1, 'ROLE_USER');
INSERT INTO authority (ID, NAME) VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (1, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (1, 2);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (2, 1);
INSERT INTO user_authority (USER_ID, AUTHORITY_ID) VALUES (3, 1);

INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Strength Of Materials");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Engineering Design");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Hydraulics");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Aerodynamics");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "Thermodynamics");
INSERT INTO subject (id, course_id, subject_name) VALUES (null, 0, "");



