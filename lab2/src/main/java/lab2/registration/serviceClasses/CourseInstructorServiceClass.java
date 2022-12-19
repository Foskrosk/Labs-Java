package lab2.registration.serviceClasses;

import lab2.registration.model.CourseInfo;
import lab2.registration.model.CourseInstance;
import lab2.registration.model.Instructor;
import lab2.registration.model.Student;
import lab2.registration.service.CourseInstructorService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseInstructorServiceClass implements CourseInstructorService {

    List<Student> banchelors = null;
    List<CourseInfo> courses = null;
    List<Student> masters = null;
    List<CourseInstance> courseInstance = null;
    List<Instructor> instructors;

    public CourseInstructorServiceClass() throws IOException {
    }

    public CourseInstructorServiceClass(List<Student> banchelors, List<Student> masters, List<CourseInfo> courses, List<CourseInstance> courseInstance, List<Instructor> instructors)
    {
        this.courseInstance = courseInstance;
        this.banchelors = banchelors;
        this.courses = courses;
        this.masters = masters;
        this.instructors = instructors;
    }

    @Override
    public List<Student> findStudentsByCourseId(long courseId) {
        CourseInfo courseInfo = courses.stream().filter(course-> course.getId() == courseId).findFirst().orElse(null);
        return courseInfo != null ? courseInfo.getAssignedStudent() : new ArrayList<Student>();
    }

    @Override
    public List<Student> findStudentsByInstructorId(long instructorId) {
        List<Student> result = new ArrayList<Student>();
        courses.stream().filter(course -> courseInstance.stream().anyMatch(
                courseInstance1-> (course.getId() == courseInstance1.getCourseId() && courseInstance1.getInstructorId() == instructorId)
        )).collect(Collectors.toList()).stream().forEach(course-> {
            result.addAll(course.getAssignedStudent());
        });

        return result;
    }

    @Override
    public List<Instructor> findReplacement(long instructorId, long courseId) {
        List<Instructor> result = new ArrayList<Instructor>();
        result.addAll(instructors.stream().filter(instructor -> instructor.getCanTeach().stream().anyMatch(
                canT -> canT == courseId && instructor.getId() != instructorId
        )).collect(Collectors.toList()));

        return result;
    }
}
