package by.training;

import by.training.connection.ConnectionPool;
import by.training.di.ServiceCreationException;
import by.training.di.ServiceCreator;
import by.training.domain.Student;
import by.training.service.InstructorService;
import by.training.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

public class test {
    public static void main(String[] args) throws Exception {
      ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.initConnections("by/training/resources/database", 10, 50, 2);
        try (ServiceCreator sc = new ServiceCreator()) {
            StudentService studentService = sc.getStudentService();
            List<Student> students = studentService.findAll();
            students.forEach(System.out::println);
            InstructorService instructorService = sc.getInstructorService();
            instructorService.delete(6L);
        } catch (ServiceCreationException | SecurityException e) {

        }



    }
}
