# Vacation Pay Calculator App

Spring Boot + Java 11 microservice with a single API:
```
GET "/calculate"
```
The project is a test task for [Neoflex educational center](https://edu.neoflex.ru/).
## How to Install and Run the Project
You need **Docker** to be installed. In project repository, run following command in console:
```
docker-compose -f docker-compose.yml up
```
To stop container with application, run 
```
docker-compose -f docker-compose.yml down
```
## Project description
The project allows to calculate vacation pay. There are two option how to calculate it.
1. By average salary (averageSalary) and number of vacation days (vacationDays):
```
http://localhost:8080/calculate?averageSalary=100&vacationDays=10
```
2. By average salary (averageSalary), start date of vacation (startDate) and end date of vacation (endDate):
```
http://localhost:8080/calculate?averageSalary=100&startDate=2024-02-02&endDate=2024-02-12
```
Parameters restrictions:
* **averageSalary** should be positive (natural or real number). It is expected to be your salary for the last **12** months;
* **vacationDays** should be less or equal to **28**;
* Both **startDate** and **endDate** are expected in a **yyyy-MM-dd** format, where the year must be **2024**. The total period of days (both dates inclusive) should be also less or equal to **28** (excluding holidays). 

In case of incorrect data, the application returns a message with problem description.
## Used Technologies
Java 11, Spring Boot, JUnit, Docker
