package Recap;

import java.util.Scanner;

class UserInfo{
    private String name;
    private String jobRole;
    private int age;

    public UserInfo(String name, String jobRole, int age){
        this.name = name;
        this.jobRole = jobRole;
        this.age = age;
    }

    public void showDetails(){
        System.out.println("----- User Information ------");
        System.out.println("Account Name : " + name);
        System.out.println("Job Position : " +  jobRole);
        System.out.println("Age : " + age);
    }

    public static UserInfo userInputForm(){
        Scanner scan = new Scanner(System.in);

        System.out.println("Enter your name :");
        String name = scan.nextLine();

        System.out.println("Enter your Job Position");
        String jobRole = scan.nextLine();

        System.out.println("Enter your Age");
        int age = scan.nextInt();

        return new UserInfo(name,jobRole,age);
    }
}

public class Basic1 {
    static void main() {
        System.out.println("Program Starting");
        UserInfo userInfo = UserInfo.userInputForm();
        userInfo.showDetails();
    }

}
