package lab2.registration.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для информации о студенте
 */
public class Student extends Person {

    /**
     * список идентификаторов курсов (CourseInstance.id), пройденных студентом
     */
    private List<Long> completedCourses;

    private StudentCategory studentCategory;

    private List<Long> appliedCourses = new ArrayList<Long>();

    public void setStudentCategory(StudentCategory studentCategory) {
        this.studentCategory = studentCategory;
    }

    public StudentCategory getStudentCategory() {
        return studentCategory;
    }

    public List<Long> getCompletedCourses() {
        return completedCourses;
    }

    public void setCompletedCourses(List<Long> completedCourses) {
        this.completedCourses = completedCourses;
    }

    public void addCourse(long courseId)
    {
        appliedCourses.add(courseId);
    }

    public void deleteCourse(long courseId)
    {
        appliedCourses.remove(courseId);
    }

    public List<Long> getAppliedCourses() {
        return appliedCourses;
    }
}
