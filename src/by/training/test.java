package by.training;

import by.training.domain.User;
import by.training.enums.Roles;

public class test {
    public static void main(String[] args) throws Exception {
      /* ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.initConnections("by/training/resources/database", 10, 50, 2);
        try (ServiceCreator sc = new ServiceCreator()) {
            UserService userService = sc.getUserService();
            List<User> freeList = userService.findAllFreeUsers();
            freeList.forEach(System.out::println);
        } catch (ServiceCreationException | SecurityException e) {

        } */

        User user1 = new User();
        user1.setId(1L);
        user1.setLogin("LOL");
        user1.setPassword("1234");
        user1.setRole(Roles.ADMINISTRATOR);


        User user2 = new User();
        user2.setId(1L);
        user2.setLogin("LOL");
        user2.setPassword("1234");
        user2.setRole(Roles.ADMINISTRATOR);


        System.out.println(user1.equals(user2));


    }
}
