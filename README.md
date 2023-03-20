# spring-boo-jpa-demo

## How to start application? 


Firstly build with maven "mvn clean install -U", then run application with Profile "local-h2"

To view h2-Database:

link: 

http: localhost:8080/h2

username: sa

password: sa


## What tech-stacks are there this Repo? 

This repo creates entities with JPA and the database-schema are created with liquibase. 
This repo is a spring-boot-application with Java 17, Spring boot 3.0.4 and h2-database.  

## Some JPA Details

@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)

CascadeType.ALL means, every thing happens to the parent entity, it will also happen for child entity.

For example, if EmployeeEntity is deleted, then AddressEntity will be deleted automatically. 

fetch = FetchType.LAZY means, only if child entity not null, it will be loaded. 

And to avoid error, when child entity is null, this property is used spring.jackson.serialization.fail-on-empty-beans=false

## What for this Repo?

There are 2 Entities, EmployeeEntity and AddressEntity. OneToOne Relation. Search is uni-direction. 
By searching Employee, Address can be got, but not in other direction.

In Entity-Declaration, Child-Entity AddressEntity is added to Parent-Entity Employee. 

And in table employee, a column address_id is as foreign key created. 

Purpose of this repo, is to find out, if an index are needed or not, for the Foreign Key address_id in Parent Table employee.

is to find out happens behind the scene, what SELECT-Statement will Hibernate use, if a query for Employee is made to show all info of Employee and Addresse.


To active SELECT-Statement, this property is used in the application-property:
spring.jpa.show-sql=true

## Result 

### after calling /searchEmployee, result from console

Hibernate: select e1_0.id,e1_0.address_id,e1_0.first_name,e1_0.last_name from employee e1_0 where e1_0.last_name=?

Hibernate: select a1_0.id,a1_0.city from address a1_0 where a1_0.id=?


### /deleteEmployee, result from console

Hibernate: delete from employee where id=?

Hibernate: delete from address where id=?


### What does it mean?

By searching or deleting Entities, there is no SQL JOIN, so no such condition like WHERE employee.address_id = address.id

SO NO INDEX FOR FK SHOULD BE EXTRA CREATED! 

