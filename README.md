# Room-Clean Architecture-Inventory-System

This is an app for taking inventory which can later on be sold and produces records of sales.
It is implemented as an offline system as of now.

## Tools and Concepts used:
1. Room Persistence library - An ORM library used for data persistence
2. Coroutines & Flow API
3. MVVM Clean Architecture - A way to structure a project to support scalability and testability
4. SOLID principles - Design concepts to maintain clean code.
5. Dependency Injection with Dagger Hilt

### Clean Architecture
The figure below depicts the moving parts of a clean architecture system
<img src= "https://user-images.githubusercontent.com/59829833/136694696-121ee750-cc4e-4d37-9d0b-1b0bf28a9726.jpeg"
 width = "500" height="400"/>
+ **Presentation :** This is the layer that bundles the UI related components.
  In our app, all our fragments, activities and their activities are packaged into this layer.
+ **Data :** Whatever source of data our application is going to use will all be abstracted in this layer.
+ **Domain :** This layer maintains the business rules and logic for our application.

#### Benefits of clean architecture
+ Greater decoupling of code which in turn enhances testing and reusability.
+ The project is more understandable even to visitors.

### SOLID Principles
1. **Single Responsibility Principle (SRP)**
   Every software artifact should have only one single responsibility - reason to change. 
   Code that is depended upon by other artifacts should be separated.
   
2. **Open-Closed Principle**
A software artifact should be open for extension but should be closed for modification.
   
3. **Liskov Substitution**
If you have class B which inherits class A, wherever class A is being used, you should be able to use class B without breaking the flow.
   
4. **Interface Segregation**
To prevent a class from implementing methods it does not even need, it is best to many small small interfaces that the class can implement according to its functionality.
   
5. **Dependency Inversion**
Artifacts should depend on abstractions rather than concrete implementations.


