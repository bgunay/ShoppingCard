WebStore
=========
WebStore v1.0 is a full fledged online shopping system built in Spring-MVC. It uses JSP for view templating and MongoDB is at the database end.


How to import and build project?
===========
1. Clone the project 
2. Import in Eclipse as maven project
3. Go to Project Properties > Java Compiler > JDK Compliance and  adjust JDK Compliance accoriding to your JDK version.
4. Go to Project Properties > Java Build Path > Libraries and adjust JRE system library
5. Go to Run As > Maven clean > Maven Install.


MongoDB schema name: shoppingcard.

For create MongoDB sample categories, uncomment lines in CategoryRepositoryImpl getAllObjects() method.

Screenshots:

https://cloud.githubusercontent.com/assets/3852424/18110302/d0597ddc-6f1f-11e6-9841-130548d47060.JPG
https://cloud.githubusercontent.com/assets/3852424/18110303/d05b60c0-6f1f-11e6-9460-12ffa10089e6.JPG
