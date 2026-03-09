import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Practice {
  public static List<String> listOfPeopleInDiscord(Integer count) {
    List<String> users = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      users.add("Users" + i);
    }

    List<String> totalUsers = new LinkedList<>(users);
    totalUsers.addFirst("admin");
    totalUsers.addLast("admin");
    System.out.println(totalUsers);

    return users;
  }

  public static Set<String> removeDuplicateUsers(List<String> users) {
    Set<String> updateUsers = new HashSet<>(users);
    updateUsers.add("Users1");
    updateUsers.add("Users7");
    return updateUsers;
  }

  public static Map<Integer, String> discordServer(Set<String> users) {
    Map<Integer, String> userMaping = new HashMap<>();
    Integer roll = 0;

    for (String user : users) {
      userMaping.put(roll, user);
      roll += 1;
    }

    return userMaping;
  }

  public static void main(String[] args) {
    System.out.println("Hello, World!");

    List<String> infoDiscord = listOfPeopleInDiscord(5);
    System.out.println(infoDiscord);
    infoDiscord.add("Users0");
    infoDiscord.add("Users6");

    Set<String> updateInfoDiscord = removeDuplicateUsers(infoDiscord);
    System.out.println(updateInfoDiscord);

    Map<Integer, String> mapUsers = discordServer(updateInfoDiscord);
    System.out.println(mapUsers);
  }
}
