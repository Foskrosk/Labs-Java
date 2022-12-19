package lab2.registration.serviceClasses;

import lab2.registration.model.ActionStatus;
import lab2.registration.model.CourseInfo;
import lab2.registration.model.CourseInstance;
import lab2.registration.model.Student;
import lab2.registration.reader.CourseDataReader;
import lab2.registration.reader.InstructorDataReader;
import lab2.registration.reader.StudentDataReader;
import lab2.registration.service.CourseInstructorService;
import lab2.registration.service.StudentService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static lab2.registration.model.ActionStatus.NOK;
import static lab2.registration.model.ActionStatus.OK;
import static lab2.registration.model.StudentCategory.BACHELOR;
import static lab2.registration.model.StudentCategory.MASTER;

public class StudentServiceClass implements StudentService {

    List<Student> banchelors = null;
    List<CourseInfo> courses = null;
    List<Student> masters = null;
    List<CourseInstance> courseInstance = null;

    public StudentServiceClass() throws IOException {
    }

    public StudentServiceClass(List<Student> banchelors, List<Student> masters, List<CourseInfo> courses, List<CourseInstance> courseInstance)
    {
        this.courseInstance = courseInstance;
        this.banchelors = banchelors;
        this.courses = courses;
        this.masters = masters;
    }

    private Student findStudentByID(long studentId)
    {
        Student student = null;
        boolean isStudentFinded = false;

        student = banchelors.stream().filter(banchelor-> (banchelor.getId() == studentId)).findFirst().orElse(null);
        if (student != null)
        {
            isStudentFinded = true;
            student.setStudentCategory(BACHELOR);
        }

        if (!isStudentFinded) {
            student = masters.stream().filter(master -> (master.getId() == studentId)).findFirst().orElse(null);
            if (student != null) {
                isStudentFinded = true;
                student.setStudentCategory(MASTER);
            }
        }

        return student;
    }


    @Override
    public ActionStatus subscribe(long studentId, long courseId) throws NoStudentFindException, NoCourseFindException, ToLateToSubOrUnsubException, NoMoreCapacityException, WrongStudentCategoryException, NotCompletePreviousCoursesException {
        ActionStatus result = NOK;

        Student studentToSubscribe = findStudentByID(studentId);
        CourseInfo course = null;

        if (studentToSubscribe != null) {
            course = courses.stream().filter(cour -> (cour.getId() == courseId)).findFirst().orElse(null);
        }
        else {
            throw new NoStudentFindException();
        }

        if (course != null)
        {
            CourseInfo finalCourse = course;
            CourseInstance courseInst = courseInstance.stream().filter(courseInstance1 -> finalCourse.getId() == courseInstance1.getInstructorId())
                    .findFirst().orElse(null);
            boolean isFindCourceInstance = courseInst != null;

            if (isFindCourceInstance && courseInst.getCapacity() != -1)
            {
                if (courseInst.getCapacity() - 1 < 0) {
                    throw new NoMoreCapacityException();
//                    return NOK;
                }
            }

            Date currentDate = new Date();

            if (!isFindCourceInstance || currentDate.before(courseInst.getStartDate()))
            {
                if (course.AssignStudentToCourse(studentToSubscribe))
                {
                    studentToSubscribe.addCourse(courseId);
                    result = OK;
                }
            }
            else
            {
                throw new ToLateToSubOrUnsubException();
            }
        }
        else {
            throw new NoCourseFindException();
        }

        return result;
    }

    @Override
    public ActionStatus unsubscribe(long studentId, long courseId) throws NoCourseFindException, NoStudentFindException, ToLateToSubOrUnsubException, StudentDidntSubscribedToCourseException {
        ActionStatus result = NOK;

        Student studentToUnsubscribe = findStudentByID(studentId);
        CourseInfo course = null;

        if (studentToUnsubscribe != null) {
            course = courses.stream().filter(cour -> (cour.getId() == courseId)).findFirst().orElse(null);
        }
        else
        {
            throw new NoStudentFindException();
        }

        if (course != null)
        {
            CourseInfo finalCourse = course;
            CourseInstance courseInst = courseInstance.stream().filter(courseInstance1 -> finalCourse.getId() == courseInstance1.getInstructorId())
                    .findFirst().orElse(null);
            boolean isFindCourceInstance = courseInst != null;

            Date currentDate = new Date();

            if (!isFindCourceInstance || currentDate.before(courseInst.getStartDate()))
            {
                if (course.unassignStudent(studentToUnsubscribe))
                {
                    studentToUnsubscribe.deleteCourse(courseId);
                    result = OK;
                }
                else
                {
                    throw new StudentDidntSubscribedToCourseException();
                }
            }
            else
            {
                throw new ToLateToSubOrUnsubException();
            }
        }
        else
        {
            throw new NoCourseFindException();
        }

        return result;
    }

    @Override
    public List<CourseInstance> findAllSubscriptionsByStudentId(long studentId) {
        List<CourseInstance> result = new ArrayList<CourseInstance>();
        Student student = findStudentByID(studentId);

        if (student != null)
        {
            List<Long> studentCourses = student.getAppliedCourses();
            result.addAll(
              courseInstance.stream().filter(courseInst -> studentCourses.stream().anyMatch(
                      studentCourse-> studentCourse == courseInst.getCourseId()
              )).collect(Collectors.toList()));
        }

        return result;
    }

    public class NoStudentFindException extends Exception {
    }

    public class NoCourseFindException extends Exception {
    }

    public static class WrongStudentCategoryException extends Exception {
    }

    public class NoMoreCapacityException extends Exception {
    }

    public class ToLateToSubOrUnsubException extends Exception {
    }

    public class StudentDidntSubscribedToCourseException extends Exception {
    }

    public static class NotCompletePreviousCoursesException extends Exception {
    }
}
