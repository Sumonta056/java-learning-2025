# Inheritance

- Achieve/Inherit properties/data and behaviors of a class into another class.
- Main purpose is to promote code reusability.
- It’s easier to extend a class and add new features than writing a new class from scratch.
- promotes the maintainability of the code. Imagine you have a superclass and 5 subclasses. When you want to update a common feature of all these classes, you just update the parent class in one place

# Rules 

- When a class implements an interface, it has to provide implementation details for all the methods of that interface (overriding).
- When an abstract class implements an interface, it’s not required to override methods of the super interface.
- Interface > Abstract class > Non-abstract class. Non-abstract class must have to implement methods that haven’t been implemented in the abstract class.
- the subclass inherits all the public and protected members of the super class. But it cannot access the private members of the super class.
- Constructors are not inherited 
- An interface can extend another interface.

# Resources 

- https://www.codejava.net/java-core/the-java-language/what-is-inheritance-in-java-the-what-why-and-how
- 