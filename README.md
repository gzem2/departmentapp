## Приложение на Java написанное по тестовому заданию


## Чтобы пересобрать отдельный модуль
`gradle :model:build` <br>
`gradle :dao:build`

## Настройки соединения MySQL
Настройки соединения(логин и пароль) с MySQL находятся в файле:<br> 
`dao/src/main/resources/application-mysql.properties`

## Миграции ДБ

Чтобы создать таблицы используется инструмент миграций дб: flyway

Чтобы создать таблицы и заполнить дб тестовыми данными:<br>
`gradle :dao:flywayMigrate`

## Тестовое задание

https://www.cyberforum.ru/java-j2ee/thread1670800.html

Java многомодульное приложение с разделением на слои

1. Установить Tomcat.
2. База данных (БД): MySQL, H2, HSQLDB (для тестов использовать «In-memory database mode»).
3. Установить подключение к БД.
4. В БД необходимо создать две таблицы: Отделы и Сотрудники.
5. БД должна заполняться тестовыми данными.
6. Для отделов хранить название отдела.
7. Для сотрудников хранить: отдел, ФИО сотрудника, дату его рождения и его зарплату.
8. Дополнительные фреймворки Spring, MyBatis (если нужен).
9. Выбрать на свое усмотрение вариант доступа к БД: Spring JDBC или MyBatis реализация.
10. Написать (и уметь публиковать «руками» на сервер Tomcat) Web-сервис (RESTful или JAX-WS) для доступа к данным, который будет выбирать данные из БД и сохранять/изменять/удалять данные в БД.
11. Написать (и так же уметь публиковать «руками» на сервер Tomcat) простое Web-приложение для работы с отделами и сотрудниками отделов (технологии для реализации пользовательского интерфейса: Struts, Spring MVC). Данное приложение для работы с БД должно использовать Web-сервис указанный выше (п.10).
12. Web - приложение должно позволять:
· посмотреть список отделов и среднюю зарплату (рассчитывается автоматически) по этим отделам (первая списочная форма);
· список сотрудников в отделах с указанием зарплаты для каждого сотрудника и полем поиска для поиска сотрудников родившихся в определенную дату или в период между датами (вторая списочная форма);
· изменять (добавлять/редактировать/удалять) вышеуказанные данные.
13. Структура проекта: мультипроект с четким разделением на слои и покрытием тестами на каждом уровне:
Пример структуры проекта:
department-app
|-model
|-dao
|-service
|-rest
|-web-app

14. Проект ОБЯЗАТЕЛЬНО собирать либо с помощью Maven либо- Gradle.
15. Проект ОБЯЗАТЕЛЬНО должен собираться из командной строки.
16. Результат сборки приложения - 2 WAR файла:
· WAR файл для приложения REST-service;
o model
o dao
o services < - > rest
· WAR файл для приложения c WEB – интерфейсом.
o model
o services < - > rest
o web