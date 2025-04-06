# Система проверки правильного питания (https://docs.google.com/document/d/11yEfzp2Rj0v-PFfAOwnJPydv0AmIy0F6yY391sPSxn8/edit?tab=t.0)

## Описание проекта

REST API-сервис для учета дневной нормы калорий и отслеживания приёмов пищи пользователей. Система автоматически рассчитывает суточную калорийность по формуле Харриса-Бенедикта и позволяет добавлять блюда, составлять приёмы пищи и получать отчеты.

### Функциональные требования:

1. **Добавление пользователя в систему:**
    - Добавление пользователя с параметрами:Имя, Email, Возраст, Вес, Рост, Активность, Пол
    - Автоматический расчет дневной нормы калорий за день реализовано с помощью аннотации @Scheduled

2. **Добавления блюда в систему:**
    - Добавление блюд с характеристиками: Название, Калорийность на порцию, Белки, жиры, углеводы

3. **Приемы пищи:**
    - Пользователь может добавлять приёмы пищи, указывая список блюд

4. **Отчёты:**
    - Отчет за день: список всех приёмов пищи и суммарная калорийность
    - Проверка соблюдения дневной нормы калорий
    - История питания по дням

5. **Журналирование и логирование:**
    - Все создания записей записываются в журнал через **Slf4j** от Lombok. Логи сохраняются в файл.

6. **API:**
    - REST API реализовано для всех действий, с подробной документацией через Swagger.

7. **Оптимизация и производительность:**
    - Включена поддержка пагинации для больших объемов данных.

## Стек технологий:

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- JUnit & Mockito
- Docker(для базы данных)
- Lombok
- Liquibase (для миграций)
- Swagger (для документации)

## План выполненных задач:

### Миграции

- Созданы и заполнены таблицы для:
    -  users, meals, dishes, meal_dishes, reports.

### Реализация сущностей и репозиториев

- Созданы модели и репозитории для:
    -  users, meals, dishes, reports.

### Конфигурация

- **Swagger:** Документирует каждый DTO и эндпоинт.
- **Slf4j:** Логирование всех действий через Lombok Slf4j.
- **ScheduledConfig:** Для активации планировщика создании записей.
- **GlobalExceptionHandler:** Исключения обрабатываются централизованно для каждой сущности.

### Валидация данных

- Все DTO, принимаемые через POST и PUT запросы, проходят валидацию на корректность. Дополнительно для классов UserDto & MealDto реализованы кастомные аннотации для проверки некоторых константных значений.

### Не до паттерн "Синглтон" & "фабричный" для проверки константных значений

- Реализована логика на основе паттернов "Синглтон" & "фабричный" для проверки различных данных (пол, активность и цель пользователя и др...).

### Функциональность

- Регистрация пользователей.
- Управление ресурсами (CRUD).
- Создание блюд и према пищи.
- Автоматическое добавление записей у пользователя.
- Чтение и проверка нормы пользователя.

## Особенности проекта:

- **DTO на основе Record:** Использованы для передачи данных между клиентом и сервером.
- **Тестирование:** Добавлены юнит-тесты с использованием JUnit и Mockito. Методы тестируются изолированно — без зависимости от БД и других сервисов.
- **Плановое добавление данных:** Автоматическое добавление и расчет данных в БД с помощью аннотации _@Scheduled_.
- **Кастомные валидации:** Реализованы отдельные валидаторы, чтобы вынести бизнес-валидации за пределы сервисного слоя.
- **Начальные данные:** Система заполняется начальными данными через Liquibase для тестирования.

### Запуск проекта:

1. Клонируйте репозиторий.
2. Настройте базу данных PostgreSQL. (Если есть докер пропишите команду: `docker run -d --name eat_smart -p 1111:5432 -e POSTGRES_DB=postgres -e POSTGRES_USER=qwe -e POSTGRES_PASSWORD=qwe postgres:17`)
3. Запустите приложение через Maven/Gradle.
4. Используйте Postman для тестирования API.

### Postman Collection:

Коллекция для тестирования API доступна в проекте(в папке _resources_) и может быть импортирована в Postman для демонстрации функциональности.