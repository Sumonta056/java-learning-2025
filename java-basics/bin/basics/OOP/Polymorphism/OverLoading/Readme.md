## Overloading (Compile Time Polymorphism)

overloading refers to the ability of a class to have multiple constructors or multiple methods with the same name but different signatures, i.e. arguments list.

1. Allows overloading constructors
2. Overloading a method inherited from a superclass
3. Overloading a method by varying the number of arguments
4. Overloading a method by varying the types of arguments
5. Overloading in interfaces and abstract classes: Here child class has to implement total 5 methods
```java
public interface Animal {
    void eat();
    void eat(String food);
    void eat(String food, int quantity);
    void move();
}

public abstract class AbstractCat implements Animal {
    abstract void move(int distance);
}
```
