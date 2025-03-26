--liquibase formatted sql
--changeset Abu:add_initial_data

insert into GOAL_TYPE(name)
values ('похудение'),('набор массы'),('поддержание');

INSERT INTO USERS(name, age, height, weight, email, goal_type_id)
VALUES ('John Doe', 21, 175.3, 55, 'john.doe@example.com', (select id from GOAL_TYPE where NAME like 'набор массы')),
       ('Jane Doe', 19, 164.3, 76, 'jane.doe@example.com', (select id from GOAL_TYPE where NAME like 'похудение')),
       ('Jacob Doe', 21, 175.3, 76, 'jacob.doe@example.com', (select id from GOAL_TYPE where NAME like 'поддержание'));


INSERT INTO MEAL_TYPE (NAME)
VALUES ('Завтрак'), ('Обед'), ('Ужин'), ('Перекус');

INSERT INTO DISHES (name, calories, protein, fats, carbohydrates)
VALUES
    ('Овсянка с бананом', 350, 10, 5, 60),
    ('Гречка с курицей', 450, 35, 10, 50),
    ('Салат Цезарь', 300, 20, 15, 25),
    ('Суп гороховый', 250, 15, 8, 30),
    ('Омлет с сыром', 400, 25, 20, 30),
    ('Йогурт натуральный', 150, 10, 5, 15);

INSERT INTO MEALS (meal_time, USER_ID, date)
VALUES
    ((SELECT id FROM MEAL_TYPE WHERE NAME = 'Завтрак'), (SELECT id FROM USERS WHERE email = 'john.doe@example.com'), '2025-03-27'),
    ((SELECT id FROM MEAL_TYPE WHERE NAME = 'Обед'), (SELECT id FROM USERS WHERE email = 'jane.doe@example.com'), '2025-03-27'),
    ((SELECT id FROM MEAL_TYPE WHERE NAME = 'Ужин'), (SELECT id FROM USERS WHERE email = 'jacob.doe@example.com'), '2025-03-27');

INSERT INTO MEAL_DISHES (MEAL_ID, DISHES_ID)
VALUES
    ((SELECT id FROM MEALS WHERE USER_ID = (SELECT id FROM USERS WHERE email = 'john.doe@example.com') AND date = '2025-03-27'),
     (SELECT id FROM DISHES WHERE name = 'Овсянка с бананом')),

    ((SELECT id FROM MEALS WHERE USER_ID = (SELECT id FROM USERS WHERE email = 'jane.doe@example.com') AND date = '2025-03-27'),
     (SELECT id FROM DISHES WHERE name = 'Гречка с курицей')),

    ((SELECT id FROM MEALS WHERE USER_ID = (SELECT id FROM USERS WHERE email = 'jacob.doe@example.com') AND date = '2025-03-27'),
     (SELECT id FROM DISHES WHERE name = 'Суп гороховый'));

INSERT INTO REPORTS (TOTAL_CALORIES, DATE, USER_ID)
VALUES
    (350, '2025-03-27', (SELECT id FROM USERS WHERE email = 'john.doe@example.com')),
    (450, '2025-03-27', (SELECT id FROM USERS WHERE email = 'jane.doe@example.com')),
    (250, '2025-03-27', (SELECT id FROM USERS WHERE email = 'jacob.doe@example.com'));

