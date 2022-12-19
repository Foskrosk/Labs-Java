package lab2.registration.ExeptionsLogs;

import lab2.registration.Main;
import lab2.registration.model.*;
import lab2.registration.serviceClasses.StudentServiceClass;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

import static lab2.registration.model.ActionStatus.NOK;
import static lab2.registration.model.ActionStatus.OK;

public class LoggerForStudService {
    private static final Logger logger = Logger.getLogger(LoggerForStudService.class.getName());
    List<Student> banchelors = null;
    List<CourseInfo> courses = null;
    List<Student> masters = null;
    List<CourseInstance> courseInstance = null;
    StudentServiceClass studentService = null;

    public LoggerForStudService(List<Student> banchelors, List<Student> masters, List<CourseInfo> courses, List<CourseInstance> courseInstance)
    {
        this.courseInstance = courseInstance;
        this.banchelors = banchelors;
        this.courses = courses;
        this.masters = masters;

        studentService = new StudentServiceClass(banchelors, masters, courses, courseInstance);
    }

    public ActionStatus subscribe(long studentId, long courseId)
    {
        try {
            studentService.subscribe(studentId, courseId);
        }
        catch (StudentServiceClass.NoStudentFindException e)
        {
            logger.log(Level.SEVERE, "Id doesn't match any student");
            return NOK;
        }
        catch (StudentServiceClass.NoCourseFindException e)
        {
            logger.log(Level.SEVERE, "Id doesn't match any course");
            return NOK;
        }
        catch (StudentServiceClass.ToLateToSubOrUnsubException e)
        {
            logger.log(Level.SEVERE, "It's to late to subscribe to course");
            return NOK;
        }
        catch (StudentServiceClass.NoMoreCapacityException e)
        {
            logger.log(Level.SEVERE, "No more free space in course to subscribe");
            return NOK;
        }
        catch (StudentServiceClass.WrongStudentCategoryException e)
        {
            logger.log(Level.SEVERE, "Student doesn't match categories of course");
            return NOK;
        }
        catch (StudentServiceClass.NotCompletePreviousCoursesException e)
        {
            logger.log(Level.SEVERE, "Student didn't complete required courses to start this course");
            return NOK;
        }

        return OK;
    }

    public ActionStatus unsubscribe(long studentId, long courseId)
    {
        try{
            studentService.unsubscribe(studentId, courseId);
        }
        catch (StudentServiceClass.NoStudentFindException e)
        {
            logger.log(Level.SEVERE, "Id doesn't match any student");
            return NOK;
        }
        catch (StudentServiceClass.NoCourseFindException e)
        {
            logger.log(Level.SEVERE, "Id doesn't match any course");
            return NOK;
        }
        catch (StudentServiceClass.ToLateToSubOrUnsubException e)
        {
            logger.log(Level.SEVERE, "It's to late to unsubscribe from course");
            return NOK;
        }
        catch (StudentServiceClass.StudentDidntSubscribedToCourseException e)
        {
            logger.log(Level.SEVERE, "Student didn't subscribe to course to be unsubscribed");
            return NOK;
        }

        return OK;
    }

}
