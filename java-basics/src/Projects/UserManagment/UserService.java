package Projects.UserManagment;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private List<User> userList = new ArrayList<>();

    public void addUser(User user){
        if(user == null){
            throw new IllegalArgumentException("User not found!");
        }
        userList.add(user);
        System.out.println("User Added");
        System.out.println(user);
    }

    public void removeUser(int id){
        boolean removed = userList.removeIf(user -> user.getId() == id);
        if(!removed){
            System.out.println("User Delete Failed for id :" + id);
        }
        else {
            System.out.println("User Deleted Succesful");
        }
    }

    public void listUsers(){
        if(userList.isEmpty()){
            System.out.println("No users");
        }
        userList.forEach(System.out::println);
    }

    public void findUserByName(String keyword){
        userList.stream()
                .filter(u -> u.getName().toLowerCase().contains(keyword.toLowerCase()))
                .forEach(System.out::println);
    }


}
