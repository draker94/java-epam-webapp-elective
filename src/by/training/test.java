package by.training;

import by.training.connection.ConnectionPool;
import by.training.dao.sql.UserDaoImpl;
import by.training.di.ServiceCreationException;
import by.training.di.ServiceCreator;
import by.training.domain.User;
import by.training.enums.Roles;
import by.training.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) throws Exception {
 ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.initConnections("by/training/resources/database", 10, 50, 2);
        try (ServiceCreator sc = new ServiceCreator()) {
            UserService userService = sc.getUserService();
            UserDaoImpl userDao = new UserDaoImpl();
            userDao.setConnection(connectionPool.getConnection());
            Map<String, String> map = new HashMap<>();

            map.put("login", "admin");
            map.put("password", "12345");

            //List<User> freeList = userDao.searchUsersByCriteria(map);
            //freeList.forEach(System.out::println);
        } catch (ServiceCreationException | SecurityException e) {

        }

        /* User user1 = new User();
        user1.setId(1L);
        user1.setLogin("LOL");
        user1.setPassword("1234");
        user1.setRole(Roles.ADMINISTRATOR);


        User user2 = new User();
        user2.setId(1L);
        user2.setLogin("LOL");
        user2.setPassword("1234");
        user2.setRole(Roles.ADMINISTRATOR);


        System.out.println(user1.equals(user2)); */


    }
}
