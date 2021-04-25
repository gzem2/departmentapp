INSERT INTO departments(department_name) VALUES('Отдел A');
INSERT INTO departments(department_name) VALUES('Отдел B');
INSERT INTO departments(department_name) VALUES('Отдел C');

INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (1, 'Андреев', 'Артем', 'Антонович', CAST('1985-05-26' AS DATE), 36000);
INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (1, 'Виноградов', 'Валерий', 'Андреевич', CAST('1991-11-24' AS DATE), 27000);
INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (1, 'Маслов', 'Анатолий', 'Иосифович', CAST('1997-05-29' AS DATE), 28000);
INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (1, 'Ермолаев', 'Трофим', 'Петрович', CAST('1990-09-13' AS DATE), 20000);
INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (1, 'Измаилов', 'Марат', 'Дмитрьевич', CAST('1982-02-26' AS DATE), 28000);

INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (2, 'Некрасов', 'Артем', 'Олегович', CAST('1997-10-10' AS DATE), 29000);
INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (2, 'Ефремов', 'Роман', 'Даниилович', CAST('1988-04-09' AS DATE), 18000);
INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (2, 'Азаров', 'Игнат', 'Николаевич', CAST('1999-12-25' AS DATE), 22000);

INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (3, 'Васильев', 'Вячеслав', 'Федорович', CAST('1989-11-12' AS DATE), 30000);
INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (3, 'Юсупов', 'Егор', 'Владимирович', CAST('1987-09-11' AS DATE), 38000);
INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (1, 'Селезнев', 'Виктор', 'Ильич', CAST('1989-06-20' AS DATE), 16000);