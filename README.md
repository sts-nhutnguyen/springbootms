# springbootms
The project for learning micro services with springboot fw

## Department Service
```
POST: http://localhost:9001/departments
GET: http://localhost:9001/departments/{{department-di}}
GET: http://localhost:9001/departments
```
## Customer Service
```
POST: http://localhost:9002/users
GET: http://localhost:9002/users{{department-di}}
GET: http://localhost:9002/users
```
## Notification Service
```
GET: http://localhost:9003/notification/{{target-id}}/?target-type={{target-type}}
```