## Abstraction

- Abstraction is a general concept that denotes the progress of modeling “real things” into programming language.
- Abstraction is showing the essential features of an object.
- Abstract classes are created to capture common characteristics of subclasses. 
- It cannot be instantiated, it can be only used as a super class by its subclasses.
- Abstract classes are used to create template for its subclasses down the hierarchy.
- Why different from inheritance? cause cannot be initialized and used to provide a template for its subclasses using abstract & concrete methods.

## Use When ?

- If you have lot of methods and want default implementation for some of them, then go with abstract class
- If you want to implement multiple inheritance then you have to use interface.As java does not support multiple inheritance, subclass can not extend more than one class but you can implement multiple interface so you can use interface for that.
- If your base contract keeps on changing then you should use an abstract class. Because if your base contract keeps on changing and you still use an interface, you would have to change all the classes which implements that interface every time the contract changes.

