package Projects.UserManagment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserService service = new UserService();

        while (true) {
            System.out.println("\n=== User Management CLI ===");
            System.out.println("1️⃣ Add User");
            System.out.println("2️⃣ List Users");
            System.out.println("3️⃣ Remove User");
            System.out.println("4️⃣ Search User by Name");
            System.out.println("0️⃣ Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        service.addUser(new User(id, name, email));
                        break;

                    case 2:
                        service.listUsers();
                        break;

                    case 3:
                        System.out.print("Enter ID to remove: ");
                        int removeId = scanner.nextInt();
                        service.removeUser(removeId);
                        break;

//                    case 4:
//                        System.out.print("Enter name keyword: ");
//                        String keyword = scanner.nextLine();
//                        service.findUserByName(keyword);
//                        break;

                    case 0:
                        System.out.println("👋 Exiting... Bye!");
                        return;

                    default:
                        System.out.println("❌ Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }
        }
    }
}

