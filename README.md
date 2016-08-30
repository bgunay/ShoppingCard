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

Screenshots

![alt tag](https://raw.githubusercontent.com/bgunay/ShoppingCard/master/addProduct.JPG)
![alt tag](https://raw.githubusercontent.com/bgunay/ShoppingCard/master/products.JPG)
