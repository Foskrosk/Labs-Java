package lab2.registration;

import lab2.registration.ExeptionsLogs.LoggerForStudService;
import lab2.registration.model.CourseInfo;
import lab2.registration.model.CourseInstance;
import lab2.registration.model.Instructor;
import lab2.registration.model.Student;
import lab2.registration.reader.CourseDataReader;
import lab2.registration.reader.InstructorDataReader;
import lab2.registration.reader.StudentDataReader;
import lab2.registration.serviceClasses.CourseInstructorServiceClass;
import lab2.registration.serviceClasses.StudentServiceClass;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        StudentDataReader studentDataReader = new StudentDataReader();
        CourseDataReader courseDataReader = new CourseDataReader();
        InstructorDataReader instructorDataReader = new InstructorDataReader();

        List<Instructor> instructors = instructorDataReader.readCourseInstrucorData();
        List<Student> banchelors = studentDataReader.readBachelorStudentData();
        List<Student> masters = studentDataReader.readMasterStudentData();
        List<CourseInfo> courses = courseDataReader.readCourseInfoData();
        List<CourseInstance> courseInstance = courseDataReader.readCourseInstanceData();

        CourseInstructorServiceClass instructorService = new CourseInstructorServiceClass(banchelors, masters, courses, courseInstance, instructors);
        LoggerForStudService logger = new LoggerForStudService(banchelors, masters, courses, courseInstance);

        // Тесты для сабскрайба
        // курс не для всех
//        for (int i = 0; i < banchelors.size(); i++)
//        {
//            System.out.println(logger.subscribe(banchelors.get(i).getId(), 10124));
//        }
//
//        for (int i = 0; i < masters.size(); i++)
//        {
//            System.out.println(logger.subscribe(masters.get(i).getId(), 10124));
//        }

//        for (int i = 0; i < banchelors.size(); i++)
//        {
//            System.out.println(studentService.unsubscribe(banchelors.get(i).getId(), 10124));
//        }
//
//        for (int i = 0; i < masters.size(); i++)
//        {
//            System.out.println(studentService.unsubscribe(masters.get(i).getId(), 10124));
//        }
//
        // курс для всех
//        for (int i = 0; i < banchelors.size(); i++)
//        {
//            System.out.println(studentService.subscribe(banchelors.get(i).getId(), 19888));
//        }
//
//        for (int i = 0; i < masters.size(); i++)
//        {
//            System.out.println(studentService.subscribe(masters.get(i).getId(), 19888));
//        }
//
//        for (int i = 0; i < banchelors.length; i++)
//        {
//            System.out.println(studentService.unsubscribe(banchelors[i].getId(), 19888));
//        }
//
//        for (int i = 0; i < masters.length; i++)
//        {
//            System.out.println(studentService.unsubscribe(masters[i].getId(), 19888));
//        }
//
//         Курс только для баков
//        for (int i = 0; i < banchelors.size(); i++)
//        {
//            System.out.println(logger.subscribe(banchelors.get(i).getId(), 10123));
//        }
////
//        System.out.println(instructorService.findReplacement(9001, 20935).get(0).getName());
//        System.out.println(instructorService.findStudentsByInstructorId(9002).get(0).getName());
//        System.out.println(instructorService.findStudentsByCourseId(10123).get(1).getName());
////
//        for (int i = 0; i < masters.size(); i++)
//        {
//            System.out.println(logger.subscribe(masters.get(i).getId(), 10123));
//        }
//
//        for (int i = 0; i < banchelors.size(); i++)
//        {
//            System.out.println(studentService.unsubscribe(banchelors.get(i).getId(), 10123));
//        }
//
//        for (int i = 0; i < masters.size(); i++)
//        {
//            System.out.println(studentService.unsubscribe(masters.get(i).getId(), 10123));
//        }
//
//        // Курс только для магов
//        for (int i = 0; i < banchelors.size(); i++)
//        {
//            System.out.println(studentService.subscribe(banchelors.get(i).getId(), 21356));
//        }
//
//        for (int i = 0; i < masters.size(); i++)
//        {
//            System.out.println(studentService.subscribe(masters.get(i).getId(), 21356));
//        }
    }
}