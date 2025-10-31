## Overriding (Run-time Polymorphism)

Overriding refers to the ability of a subclass to re-implement an instance method inherited from a superclass.

1. Only inherited methods can be overridden.
   - Suppose Class A has a method default void move()
   - Class B extends A: Class B is in different package
   - Due to package access modifier, Class B will not inherit the method move() from Class A.
   - Class B can implement its own method move() but it will not override the method from Class A.
2. Final and static methods cannot be overridden.
3. The overriding method must have same argument list.
4. The overriding method must have same return type (or subtype).
5. The overriding method must not have more restrictive access modifier.
    - If the overridden method is has default access, then the overriding one must be default, protected or public.
    - If the overridden method is protected, then the overriding one must be protected or public.
    - If the overridden method is public, then the overriding one must be only public.
6. Constructors cannot be overridden.
7. 