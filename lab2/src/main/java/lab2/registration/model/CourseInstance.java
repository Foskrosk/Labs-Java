package lab2.registration.model;

//import java.time.LocalDate;
import java.util.Date;
/**
 * Класс с данными о проведении курса. Один курс (например, дискретная математика) может быть проведен несколько раз.
 */
public class CourseInstance {
    
    /**
     * идентификатор проведения курса
     */
    private long id;

    /**
     * идентификатор курса, соответствующий CourseInfo.id
     */
    private long courseId;

    /**
     * идентификатор преподавателя
     */
    private long instructorId;

    /**
     * дата начала курса
     */
    private Date startDate;

    /**
     * ограничение на число студентов курса
     */
    private int capacity = -1;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public long getCourseId()
    {
        return courseId;
    }

    public void setCourseId(long courseId)
    {
        this.courseId = courseId;
    }

    public long getInstructorId()
    {
        return instructorId;
    }

    public void setInstructorId(long instructorId)
    {
        this.instructorId = instructorId;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }
}
