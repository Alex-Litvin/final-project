INSERT INTO user (id, first_name, second_name, middle_name, role, password, email, status)
VALUES (47, 'Alex', 'Vjacheslavovich', 'Litvin', 'USER', 'qwerty001', 'alex.v.litvin@gmail.com', 'ACTIVE');
INSERT INTO user (id, first_name, second_name, middle_name, role, password, email, status)
VALUES (48, 'Andrej', 'Valerevich', 'Pjatov', 'ADMIN', 'qwerty002', 'pjatov@gmail.com', 'ACTIVE');
INSERT INTO user (id, first_name, second_name, middle_name, role, password, email, status)
VALUES (49, 'Evgen', 'Grigorovich', 'Hacherі́dі', 'USER', 'qwerty003', 'hacheridi@gmail.com', 'ACTIVE');
INSERT INTO user (id, first_name, second_name, middle_name, role, password, email, status)
VALUES (50, 'Jаroslav', 'Volodimirovich', 'Rakickij', 'USER', 'qwerty004', 'rakickij@gmail.com', 'ACTIVE');
INSERT INTO user (id, first_name, second_name, middle_name, role, password, email, status)
VALUES (51, 'Sergіj', 'Andrіjovich', 'Krivcov', 'USER', 'qwerty005', 'krivcov@gmail.com', 'ACTIVE');
INSERT INTO user (id, first_name, second_name, middle_name, role, password, email, status)
VALUES (52, 'Mikola', 'Oleksandrovich', 'Matvіenko', 'USER', 'qwerty006', 'matvienko@gmail.com', 'ACTIVE');
INSERT INTO user (id, first_name, second_name, middle_name, role, password, email, status)
VALUES (53, 'Oleksandr', 'Oleksandrovich', 'Karavaev', 'USER', 'qwerty007', 'karavaev@gmail.com', 'ACTIVE');
INSERT INTO user (id, first_name, second_name, middle_name, role, password, email, status)
VALUES (54, 'Mikita', 'Valerіjovich', 'Burda', 'USER', 'qwerty008', 'burda@gmail.com', 'ACTIVE');
INSERT INTO user (id, first_name, second_name, middle_name, role, password, email, status)
VALUES (55, 'Oleg', 'Sergіjovich', 'Danchenko', 'USER', 'qwerty009', 'danchenko@gmail.com', 'ACTIVE');
INSERT INTO user (id, first_name, second_name, middle_name, role, password, email, status)
VALUES (56, 'Andrіj', 'Volodimirovich', 'Curіkov', 'USER', 'qwerty010', 'curicov@gmail.com', 'ACTIVE');


INSERT INTO subject (id, title) VALUES (1,	'COMPUTER_SCIENCE');
INSERT INTO subject (id, title) VALUES (2,	'GEOGRAPHY');
INSERT INTO subject (id, title) VALUES (3,	'HISTORY');
INSERT INTO subject (id, title) VALUES (4,	'UKRAINIAN_LANGUAGE');
INSERT INTO subject (id, title) VALUES (5,	'BIOLOGY');
INSERT INTO subject (id, title) VALUES (6,	'CHEMISTRY');
INSERT INTO subject (id, title) VALUES (7,	'ENGLISH');
INSERT INTO subject (id, title) VALUES (8,	'PHYSICS');

INSERT INTO university (id, title, deleted) VALUES (1, 'Kyiv Polytechnic Institute', FALSE);
INSERT INTO university (id, title, deleted) VALUES (2, 'National aviation university', FALSE);
INSERT INTO university (id, title, deleted) VALUES (3, 'National university of "Kyiv-Mohyla Academy"', FALSE);

INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (1, 5, 'Programming Java', FALSE, 80, 'ACTIVE');
INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (2, 4, 'Programming Scala', FALSE, 75, 'ACTIVE');
INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (3, 6, 'Programming Kotlin', FALSE, 80, 'ACTIVE');
INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (4, 5, 'Project Management', FALSE, 85, 'ACTIVE');
INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (5, 6, 'Programming C++', FALSE, 80, 'ACTIVE');
INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (6, 7, 'Data Science', FALSE, 90, 'ACTIVE');

INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (7, 5, 'Programming Java', FALSE, 80, 'ACTIVE');
INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (8, 4, 'Programming Scala', FALSE, 75, 'ACTIVE');
INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (9, 6, 'Programming Kotlin', FALSE, 80, 'ACTIVE');
INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (10, 5, 'Project Management', FALSE, 85, 'ACTIVE');
INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (11, 6, 'Programming C++', FALSE, 80, 'ACTIVE');
INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (12, 7, 'Data Science', FALSE, 90, 'ACTIVE');

INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (13, 5, 'Project Management', FALSE, 85, 'ACTIVE');
INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (14, 6, 'Programming C++', FALSE, 80, 'ACTIVE');
INSERT INTO speciality (id, max_student_count, title, deleted, passmark, status)
VALUES (15, 7, 'Data Science', FALSE, 90, 'ACTIVE');

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (1, 1, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (2, 1, 7);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (3, 1, 8);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (4, 2, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (5, 2, 7);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (6, 2, 8);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (7, 3, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (8, 3, 7);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (9, 3, 8);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (10, 4, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (11, 4, 3);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (12, 4, 7);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (13, 5, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (14, 5, 7);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (15, 5, 8);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (16, 6, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (17, 6, 7);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (18, 6, 8);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (19, 7, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (20, 7, 7);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (21, 7, 8);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (22, 8, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (23, 8, 7);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (24, 8, 8);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (25, 9, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (26, 9, 7);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (27, 9, 8);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (28, 10, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (29, 10, 3);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (30, 10, 7);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (31, 11, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (32, 11, 7);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (33, 11, 8);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (34, 12, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (35, 12, 7);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (36, 12, 8);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (37, 13, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (38, 13, 3);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (39, 13, 7);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (40, 14, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (41, 14, 7);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (42, 14, 8);

INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (43, 15, 1);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (44, 15, 7);
INSERT INTO speciality_subject (id, speciality_id, subject_id) VALUES (45, 15, 8);

INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (42,	47,	1,	89, 'COMPUTER_SCIENCE');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (43,	47,	7,	90,	'ENGLISH');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (44,	47,	8, 87,	'PHYSICS');

INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (45,	49,	1,	88, 'COMPUTER_SCIENCE');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (46,	49,	7,	91,	'ENGLISH');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (47,	49,	8, 86,	'PHYSICS');

INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (48,	50,	1,	87, 'COMPUTER_SCIENCE');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (49,	50,	7,	92,	'ENGLISH');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (50,	50,	8, 93,	'PHYSICS');

INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (51,	51,	1,	75, 'COMPUTER_SCIENCE');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (52,	51,	7,	90,	'ENGLISH');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (53,	51,	8, 85,	'PHYSICS');

INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (54,	52,	1,	95, 'COMPUTER_SCIENCE');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (55,	52,	3, 90,	'HISTORY');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (56,	52,	7,	75,	'ENGLISH');

INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (57,	53,	1,	100, 'COMPUTER_SCIENCE');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (58,	53,	3, 90,	'HISTORY');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (59,	53,	7,	91,	'ENGLISH');

INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (60,	54,	1,	97, 'COMPUTER_SCIENCE');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (61,	54,	3, 97,	'HISTORY');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (62,	54,	7,	84,	'ENGLISH');

INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (63,	55,	1,	100, 'COMPUTER_SCIENCE');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (64,	55,	7,	50,	'ENGLISH');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (65,	55,	8, 89,	'PHYSICS');

INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (66,	56,	1,	94, 'COMPUTER_SCIENCE');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (67,	56,	7,	93,	'ENGLISH');
INSERT INTO exam (id, user_id, subject_id, mark, title) VALUES (68,	56,	8, 92,	'PHYSICS');

INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (1, 1, 1);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (2, 1, 2);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (3, 1, 3);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (4, 1, 4);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (5, 1, 5);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (6, 1, 6);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (7, 2, 7);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (8, 2, 8);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (9, 2, 9);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (10, 2, 10);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (11, 2, 11);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (12, 2, 12);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (13, 3, 13);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (14, 3, 14);
INSERT INTO university_speciality (id, university_id, speciality_id) VALUES (15, 3, 15);

INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (72,	47,	1,	1,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (73,	47,	8,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (74,	47,	12,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (75,	47,	14,	3,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (76,	49,	1,	1,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (77,	49,	8,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (78,	49,	12,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (79,	49,	15,	3,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (80,	50,	1,	1,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (81,	50,	7,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (82,	50,	12,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (83,	50,	15,	3,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (84,	51,	1,	1,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (85,	51,	8,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (86,	51,	12,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (87,	51,	15,	3,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (88,	52,	4,	1,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (89,	52,	10,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (90,	52,	13,	3,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (91,	53,	4,	1,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (92,	53,	10,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (93,	53,	13,	3,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (94,	54,	4,	1,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (95,	54,	10,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (96,	54,	13,	3,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (98,	55,	1,	1,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (97,	55,	8,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (99,	55,	12,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (100,	55,	15,	3,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (101,	56,	1,	1,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (102,	56,	8,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (103,	56,	12,	2,	'NO');
INSERT INTO user_speciality (id, user_id, speciality_id, university_id, enter_status) VALUES (104,	56,	15,	3,	'NO');




