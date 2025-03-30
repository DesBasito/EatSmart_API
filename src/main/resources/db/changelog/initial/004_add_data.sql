--liquibase formatted sql
--changeset Abu:add_initial_data

-- Добавляем пользователей с ENUM goal_type
INSERT INTO USERS(name, age, height, weight, email, gender, activity_level, goal_type)
VALUES
    ('John Doe', 21, 175.3, 55, 'john.doe@example.com', 'MALE', 'MODERATE', 'GAIN_MUSCLE'),
    ('Jane Doe', 19, 164.3, 76, 'jane.doe@example.com', 'FEMALE', 'LIGHT', 'LOSE_WEIGHT'),
    ('Jacob Doe', 21, 175.3, 76, 'jacob.doe@example.com', 'MALE', 'ACTIVE', 'MAINTAIN');

-- Добавляем блюда
INSERT INTO DISHES (name, calories, protein, fats, carbohydrates)
VALUES
    ('Овсянка с бананом', 350, 10, 5, 60),
    ('Гречка с курицей', 450, 35, 10, 50),
    ('Салат Цезарь', 300, 20, 15, 25),
    ('Суп гороховый', 250, 15, 8, 30),
    ('Омлет с сыром', 400, 25, 20, 30),
    ('Йогурт натуральный', 150, 10, 5, 15);

-- Добавляем приемы пищи с ENUM meal_type
INSERT INTO MEALS (meal_type, USER_ID, date)
VALUES
    ('BREAKFAST', (SELECT id FROM USERS WHERE email = 'john.doe@example.com'), '2025-03-27'),
    ('LUNCH', (SELECT id FROM USERS WHERE email = 'jane.doe@example.com'), '2025-03-27'),
    ('DINNER', (SELECT id FROM USERS WHERE email = 'jacob.doe@example.com'), '2025-03-27');

-- Добавляем связи блюд с приемами пищи
INSERT INTO MEAL_DISHES (MEAL_ID, DISHES_ID)
VALUES
    ((SELECT id FROM MEALS WHERE USER_ID = (SELECT id FROM USERS WHERE email = 'john.doe@example.com') AND date = '2025-03-27'),
     (SELECT id FROM DISHES WHERE name = 'Овсянка с бананом')),

    ((SELECT id FROM MEALS WHERE USER_ID = (SELECT id FROM USERS WHERE email = 'jane.doe@example.com') AND date = '2025-03-27'),
     (SELECT id FROM DISHES WHERE name = 'Гречка с курицей')),

    ((SELECT id FROM MEALS WHERE USER_ID = (SELECT id FROM USERS WHERE email = 'jacob.doe@example.com') AND date = '2025-03-27'),
     (SELECT id FROM DISHES WHERE name = 'Суп гороховый'));

-- Добавляем отчеты
INSERT INTO REPORTS (TOTAL_CALORIES, DATE, USER_ID)
VALUES
    (350, '2025-03-27', (SELECT id FROM USERS WHERE email = 'john.doe@example.com')),
    (450, '2025-03-27', (SELECT id FROM USERS WHERE email = 'jane.doe@example.com')),
    (250, '2025-03-27', (SELECT id FROM USERS WHERE email = 'jacob.doe@example.com'));
