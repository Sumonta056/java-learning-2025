package com.codewithmridul;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Collection {

  public static void main(String[] args) {
    // Using ArrayList - demonstrating more operations
    List<String> dnPublications = new ArrayList<>();
    dnPublications.add("dn");
    dnPublications.add("d2");
    dnPublications.add("d2");
    dnPublications.add("d2");
    dnPublications.add("tradewind");
    dnPublications.add("abd");

    // Iterate with lambda
    dnPublications.forEach(pub -> System.out.println("Publication: " + pub));

    // Access by index
    System.out.println("First publication (by index): " + dnPublications.get(0));
    // Remove an item
    dnPublications.remove("d2");
    // Check if a specific value exists
    System.out.println("Contains 'dn': " + dnPublications.contains("dn"));
    // Replace an item
    dnPublications.set(1, "new-publication"); // replace at index 1
    // Get the size
    System.out.println("Total publications: " + dnPublications.size());
    // Iterate with lambda
    dnPublications.forEach(pub -> System.out.println("Publication: " + pub));

    // Now, let's see the same thing with LinkedList
    System.out.println("\n--- Using LinkedList ---");
    List<String> linkedListPubs = new java.util.LinkedList<>();
    linkedListPubs.add("dn");
    linkedListPubs.add("d2");
    linkedListPubs.add("tradewind");
    linkedListPubs.add("abd");

    // LinkedList allows the same List operations
    linkedListPubs.remove("tradewind");
    linkedListPubs.add(1, "inserted-publication"); // insert at index 1
    linkedListPubs.forEach(pub -> System.out.println("Publication (LinkedList): " + pub));

    // Difference: ArrayList is backed by a dynamic array (fast random access),
    // LinkedList is backed by nodes with pointers (fast insertions/removals in the
    // middle, slower random access).

    Set<String> setDnPublication = new TreeSet<>();
    setDnPublication.add("D2");
    setDnPublication.add("D2");
    setDnPublication.add("A2");
    setDnPublication.add("E2");
    setDnPublication.add("B2");

    setDnPublication.forEach(pub -> System.out.println(pub));

    /*
     * B2
     * A2
     * E2
     * D2
     */

    Map<String, Integer> mapDnPublication = new HashMap<>();
    mapDnPublication.put("D2", 1);
    mapDnPublication.put("D2", 2);
    mapDnPublication.put("A2", 3);
    mapDnPublication.put("B2", 3);

    // Print the map in a simple way
    System.out.println(mapDnPublication);

    // Example Employee class for demonstration
    class Employee {
      private String name;
      private double salary;

      public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
      }

      public String getName() {
        return name;
      }

      public double getSalary() {
        return salary;
      }
    }

    // Create a list of employees
    List<Employee> employees = new ArrayList<>();
    employees.add(new Employee("Alice", 60000));
    employees.add(new Employee("Bob", 45000));
    employees.add(new Employee("Carol", 80000));
    employees.add(new Employee("Dave", 52000));
    employees.add(new Employee("Eve", 49000));
    employees.add(new Employee("Alice", 70000)); // duplicate name

    // Stream operations example: filter high earners, map to names, collect to list
    List<String> highEarners = employees.stream()
        .filter(e -> e.getSalary() > 50000) // keep only those
        .map(Employee::getName) // transform to names
        .distinct() // remove duplicate names
        .sorted() // sort names alphabetically
        .limit(3) // keep first 3 names
        .collect(java.util.stream.Collectors.toList());

    // Print the resulting list
    System.out.println("High earners: " + highEarners);

    // Example of forEach to print each employee's info
    employees.forEach(emp -> System.out.println(emp.getName() + ": " + emp.getSalary()));

    // Some common Stream operations (as comments):
    // employees.stream().filter(...); // keep some items
    // employees.stream().map(...); // transform items
    // employees.stream().sorted(...); // sort
    // employees.stream().distinct(); // remove duplicates
    // employees.stream().limit(N); // first N items
    // employees.stream().collect(...); // make it back to list/set/map
    // employees.stream().forEach(...); // just loop

  }

}
