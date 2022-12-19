package lab2.registration.model;

import lab2.registration.serviceClasses.StudentServiceClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Класс для базовой информации о курсе
 */
public class CourseInfo {

    /**
     * идентификатор курса
     */
    private long id;

    /**
     * название курса
     */
    private String name;

    /**
     * краткое описание курса
     */
    private String description;

    /**
     * Список идентификаторов курсов, которые нужно обязательно пройти до начала данного курса
     */
    private List<Long> prerequisites;

    /**
     * список категорий студентов, которые могут посещать курс
     */
//    @JsonIgnoreProperties
    private List<StudentCategory> studentCategories;

    private List<Student> assignedStudent = new ArrayList<Student>();

    public boolean AssignStudentToCourse(Student student) throws StudentServiceClass.WrongStudentCategoryException, StudentServiceClass.NotCompletePreviousCoursesException {
        if (studentCategories.contains(student.getStudentCategory()))
        {
            if (prerequisites == null) {
                assignedStudent.add(student);
                return true;
            }

            List<Long> completedCourses = student.getCompletedCourses();

            if ( prerequisites.stream().filter(prerequisite->completedCourses.contains(prerequisite))
                            .findAny().orElse(null) == null)
                throw new StudentServiceClass.NotCompletePreviousCoursesException();

            assignedStudent.add(student);
            return true;
        }
        else
        {
            throw new StudentServiceClass.WrongStudentCategoryException();
        }
    }

    public boolean unassignStudent(Student student)
    {
        int size = assignedStudent.size();
        assignedStudent.removeIf(stud -> stud.getId() == student.getId());

        if (size != assignedStudent.size())
            return true;

        return false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<Long> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public void setStudentCategories(List<StudentCategory> studentCategories) {
        this.studentCategories = studentCategories;
    }

    public List<StudentCategory> getStudentCategories() {
        return studentCategories;
    }

    public List<Student> getAssignedStudent() {
        return assignedStudent;
    }
}
