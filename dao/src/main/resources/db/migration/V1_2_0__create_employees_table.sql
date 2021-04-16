CREATE TABLE IF NOT EXISTS employees (

    id BIGINT(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    department_id BIGINT(20),
    foreign key (department_id) references departments(id),
    surname varchar(20),
    name varchar(20),
    patronymic varchar(20),
    birthday DATE,
    salary INT

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;