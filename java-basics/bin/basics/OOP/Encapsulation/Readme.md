# Encapsulation : [Here](https://www.codejava.net/java-core/the-java-language/what-is-encapsulation-in-java-the-what-why-and-how)

- Encapsulation is a technique that packages related data and behaviors into a single unit
- Encapsulation hides implementation details of the Person class from other objects.
- Encapsulation is a technique for protecting data from misuse by the outside world, which is referred as ‘information hiding’ or ‘data hiding’.
```
- Private fields like name and age are not accessible directly from other classes.
- Only the public methods (getInfo(), setAge()) are accessible from outside.
```
- **This allows the class to control how the data is accessed or modified—that's encapsulation.**
- The other objects cannot access the data directly. Instead, they have to invoke the getters which are designed to protect the data from misuse or unwanted changes.


---

## 🔑 Abstraction vs. Encapsulation – Key Points

✅ Abstraction

- Think of it as: "What an object does" (not how) modeling those things and hide unnecessary details.
- How in Java: Achieved using interfaces, abstract classes, classes, fields, and methods

✅ Encapsulation

- Think of it as: "How the object hides its internals."  Protect object data and behavior from unintended access or misuse.
- How in Java: Done via access modifiers (private, public, protected) and getters/setters.


🧩 Relationship

- Encapsulation is based on abstraction.
- **Abstraction defines what should be done; encapsulation defines how to hide the details.**
- Not comparable — they solve different problems but work together in OOP.
