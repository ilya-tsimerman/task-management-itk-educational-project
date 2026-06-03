# Accounting Service

## Описание
Сервис управления данными аккаунта.  
Позволяет получить баланс пользователей и начисляет им средства за выполненный задачи.
* начисление средств пользователям происходит каждые 2 недели.

## API
Базовый URL: `http://localhost:8083/api/acounts`  

1. **Получение своего баланса**
    - URL: `/my/balance`
    - Метод: `GET`
    - Headers
    ```text
        user-id: uuid
   ```

2. **Получение баланса по ID**
    - URL: `/{id}}/balance`
    - Метод: `GET`
    - Headers
    ```text
        user-roles: [ADMIN]
   ```

## KAFKA
Сервис публикует события в Kafka при начислении средств за выпоенные задачи.  

| Topic     | Description                |
|-----------|----------------------------|
| task-flow | Событие начисления средств |

**Event**
- PaymentFlowEvent
   ```json
   {
      "userId": "550e8400-e29b-41d4-a716-446655440000",
      "amount": 300.00,
      "date": "2026-06-03T12:30:00Z",
      "tasksId": [1, 3]
   }
  ```

## Технологии
- Java 21, Spring Boot 3
- Spring Data JPA, Spring Kafka
- PostgreSQL
- Docker
- Gradle