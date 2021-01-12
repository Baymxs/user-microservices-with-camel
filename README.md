# Microservices for working with users + Apache Camel
Microservice architecture project that contains a web service and processing service. 
Spring(web, data), RabbitMQ, Apache Camel.
# REST API Reference
## Response Codes

```
200: Success
400: Bad request
404: Cannot be found
```
## Create user
---
#### Request URL:
`POST /api/v1/users`
#### Request Body:

```json
{
    "name": "Ivan",
    "surname": "Ivanov", 
    "patronymic": "Ivanovich", 
    "email": "ivanov_ivan777@gmail.com"
 }
```
#### Successful Response:
**Status:** `200`
```json
{   "id": 1,
    "name": "Ivan",
    "surname": "Ivanov", 
    "patronymic": "Ivanovich", 
    "email": "ivanov_ivan777@gmail.com"
 }
```
#### Failed Response:
**Status:** `400`
```json
{
    "message": "User with email [ivanov_ivan777@gmail.com] has already been created"
}
```

## Update user
---
#### Request URL:
`PUT /api/v1/users/{id}`
#### Request Body:
```json
{
    "name": "Nikita",
    "surname": "Ivanov", 
    "patronymic": "Ivanovich", 
    "email": "ivanov_nikita383@gmail.com"
 }
```
#### Successful Response:
**Status:** `200`
```json
{   "id": 1,
    "name": "Nikita",
    "surname": "Ivanov", 
    "patronymic": "Ivanovich", 
    "email": "ivanov_nikita383@gmail.com"
 }
```
#### Failed Response:
**Status:** `404`
```json
{
    "message": "User with id [2] not found"
}
```

## Get user
---
#### Request URL:
`GET /api/v1/users/{id}`

#### Successful Response:
**Status:** `200`
```json
{
    "id": 1,
    "name": "Nikita",
    "surname": "Ivanov", 
    "patronymic": "Ivanovich", 
    "email": "ivanov_nikita383@gmail.com"
}
```
#### Failed Response:
**Status:** `404`
```json
{
    "message": "User with id [2] not found"
}
```

## Delete user
---
#### Request URL:
`DELETE /api/v1/users/{id}`

#### Successful Response:
**Status:** `200`

#### Failed Response:
**Status:** `404`
```json
{
    "message": "User with id [2] not found"
}
```

# License
MIT

----
> GitHub: [@baymxs](https://github.com/Baymxs) 
> VK: [@baymxs](https://vk.com/baymxs)
