# Spring Batch Demo

This is a Spring Batch implementation demo. The purpose of this demo is to
consume an .csv file containing the amount of hours worked by a **User** and
create a **Payment** row with the amount of salary based on the user's **Role**.

The CSV is in the format of:

| userId        | hoursWorked   
| ------------- |:-------------:
| 1             | 24
| 2             | 32      
| 3             | 28

## How To Run

Like any normal Spring Boot application. The only thing that may change is that the
`spring.datasource.initialization-mode` property should be changed to `always` in order
to build the Database table.

## Built With
* Spring Boot
* Spring Batch
* H2 DataBase