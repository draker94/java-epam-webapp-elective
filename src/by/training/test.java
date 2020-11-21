package by.training;

import by.training.connection.ConnectionPoolException;
import by.training.dao.DaoException;
import by.training.dao.sql.ResultDaoImpl;
import by.training.domain.Assignment;
import by.training.domain.Result;
import by.training.connection.ConnectionPool;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class test {
    public static void main(String[] args) throws DaoException, ConnectionPoolException {
        /* ConnectionData.initConnections();
        Connection connection = ConnectionData.getInstance().getConnection();
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(connection);

        List<User> list = userDao.getUsersList();
        list.forEach(System.out::println);

        User user = userDao.getByLogin("teacher1");
        System.out.println(user.getRole());

        User user1 = new User();
        user1.setLogin("hors88");
        user1.setPassword("tesmaster88");
        user1.setMail("dreadno@gmail.com");
        user1.setRole(Roles.ADMINISTRATOR);
        userDao.create(user1);

        User user3 = userDao.read(1L);
        System.out.println(user3.getMail());

        user3.setMail("dreadnought1125@gmail.com");
        userDao.update(user3);
        System.out.println(user3.getMail());

        userDao.delete(7L, "users"); */

        /* CourseDaoImpl courseDao = new CourseDaoImpl();
        courseDao.setConnection(connection);

        List<Course> list = courseDao.getCoursesList();
        list.forEach(System.out::println);

        Course course = new Course();
        course.setName("суперКурс");
        course.setHours(228);
        course.setDescription("Офигенный курс, чувак!!");
        course.setInstructor(new Instructor());
        course.getInstructor().setId(2L);
        courseDao.create(course);

        System.out.println();
        List<Course> list1 = courseDao.getInstructorCoursesList(2L);
        list1.forEach(System.out::println);

        Course course1 = courseDao.read(1L);
        System.out.println(course1.getName());

        course1.setName("Java Collections1");
        courseDao.update(course1);

        courseDao.delete(3L, "courses"); */

         /*AssignmentDaoImpl assignmentDao = new AssignmentDaoImpl();
        assignmentDao.setConnection(connection);

        List<Assignment> list = assignmentDao.getAssignmentsList();
        list.forEach(System.out::println);


        Assignment assignment = new Assignment();
        assignment.setStudent(new Student());
        assignment.getStudent().setId(4L);
        assignment.setCourse(new Course());
        assignment.getCourse().setId(2L);
        assignment.setBeginDate(LocalDate.of(2021, 10, 10));
        assignment.setEndDate(LocalDate.of(2021, 11, 30));
        assignmentDao.create(assignment);


        System.out.println();
        List<Assignment> list1 = assignmentDao.getAssignmentsByStartDate(LocalDate.of(2021, 3, 31),
                LocalDate.of(2021, 12, 31));
        list1.forEach(System.out::println);

        Assignment assignment1 = assignmentDao.read(1L);
        System.out.println(assignment1);

        assignment1.setEndDate(LocalDate.of(2021, 04, 20));
        assignmentDao.update(assignment1);

        assignmentDao.delete(4L, "assignments"); */

         ConnectionPool connectionPool = ConnectionPool.getInstance();
         connectionPool.initConnections("by.training.resources.database", 10, 50, 1);
         Connection connection = connectionPool.getConnection();

        ResultDaoImpl resultDao = new ResultDaoImpl();
        resultDao.setConnection(connection);

        List<Result> list = resultDao.getResultsList();
        list.forEach(System.out::println);


        Result result = new Result();
        result.setAssignment(new Assignment());
        result.getAssignment().setId(1L);
        result.setMark(4);
        result.setDate(LocalDate.of(2021, 4, 11));
        result.setReview("Тож ниасилил");
        resultDao.create(result);

        System.out.println();
        List<Result> list1 = resultDao.getListByMark(7, 10);
        list1.forEach(System.out::println);

        Result result1 = resultDao.read(1L);
        System.out.println(result1);

        result1.setMark(10);
        resultDao.update(result1);

        //resultDao.delete(4L, "results");




    }

}
