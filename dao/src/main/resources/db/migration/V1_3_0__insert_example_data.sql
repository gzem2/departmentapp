INSERT INTO departments(department_name) VALUES('Department A');
INSERT INTO departments(department_name) VALUES('Department B');
INSERT INTO departments(department_name) VALUES('Department C');

INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (1, 'Surname', 'Name', 'Patronymic', CAST('1970-01-01' AS DATE), 10000);
INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (1, 'Surname', 'Name', 'Patronymic', CAST('1970-01-01' AS DATE), 10000);

INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (2, 'Surname', 'Name', 'Patronymic', CAST('1970-01-01' AS DATE), 10000);
INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (2, 'Surname', 'Name', 'Patronymic', CAST('1970-01-01' AS DATE), 10000);

INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (3, 'Surname', 'Name', 'Patronymic', CAST('1970-01-01' AS DATE), 10000);
INSERT INTO employees(department_id, surname, name, patronymic, birthday, salary) 
    VALUES (3, 'Surname', 'Name', 'Patronymic', CAST('1970-01-01' AS DATE), 10000);