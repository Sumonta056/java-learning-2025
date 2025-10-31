```java
@PostMapping("/api/register")
public String registerUser(@RequestParam String name, @RequestParam String email) {
    name = name.trim();  // ✅ remove extra space
    if (!email.contains("@")) {
        return "Invalid email!";
    }
    return "User " + name + " registered with email " + email;
}
```