-- Insert Catalogues
INSERT INTO CATALOGUE (NAME, CODE, CLASS_CODE, CATALOGUE_YEAR)
VALUES ('Mathematics Catalogue', 'CAT20259A', '9A', 2025);
INSERT INTO CATALOGUE (NAME, CODE, CLASS_CODE, CATALOGUE_YEAR)
VALUES ('Physics Catalogue', 'CAT20259B', '9B', 2025);

-- Insert Semesters
INSERT INTO SEMESTER (ID, NAME, CATALOGUE_ID) VALUES (1, 'Semester 1', 1);
INSERT INTO SEMESTER (ID, NAME, CATALOGUE_ID) VALUES (2, 'Semester 2', 1);
INSERT INTO SEMESTER (ID, NAME, CATALOGUE_ID) VALUES (3, 'Semester 3', 1);
INSERT INTO SEMESTER (ID, NAME, CATALOGUE_ID) VALUES (4, 'Semester 1', 2);
INSERT INTO SEMESTER (ID, NAME, CATALOGUE_ID) VALUES (5, 'Semester 2', 2);
INSERT INTO SEMESTER (ID, NAME, CATALOGUE_ID) VALUES (6, 'Semester 3', 2);

-- Insert Students
INSERT INTO STUDENT (NAME, CAT_CODE, CODE) VALUES ('Alice Smith', 'CAT20259A', 'S001');
INSERT INTO STUDENT (NAME, CAT_CODE, CODE) VALUES ('Bob Johnson', 'CAT20259A', 'S002');
INSERT INTO STUDENT (NAME, CAT_CODE, CODE) VALUES ('Charlie Brown', 'CAT20259B', 'S003');
-- Insert Disciplines
INSERT INTO DISCIPLINE (NAME, CODE) VALUES ('Mathematics', 'MATH');
INSERT INTO DISCIPLINE (NAME, CODE) VALUES ('Physics', 'PHYS');
INSERT INTO DISCIPLINE (NAME, CODE) VALUES ('Chemistry', 'CHEM');

-- Insert Grades
INSERT INTO GRADE (semester_id, discipline_id, student_id, professor_code, grade_value, date)
VALUES (1, 1, 1, 'P001', 8, '2025-06-01');
INSERT INTO GRADE (semester_id, discipline_id, student_id, professor_code, grade_value, date)
VALUES (1, 1, 2, 'P001', 9, '2025-06-01');
INSERT INTO GRADE (semester_id, discipline_id, student_id, professor_code, grade_value, date)
VALUES (3, 2, 3, 'P002', 7, '2025-06-01');
