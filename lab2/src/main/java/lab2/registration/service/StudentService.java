package lab2.registration.service;

import lab2.registration.model.ActionStatus;
import lab2.registration.model.CourseInstance;
import lab2.registration.serviceClasses.StudentServiceClass;

import java.util.List;

/**
 * Интерфейс сервиса для студентов
 */
public interface StudentService {
    /**
     * Регистрация студента на курс. Регистрация возможна при следующих условиях:
     * - курс еще не начался;
     * - курс предназначен для категории данного студента (магистра/бакалавра);
     * - студент прошел все обязательные курсы, необходимые для посещения данного курса;
     * - в курсе есть свободные места.
     *
     * @param studentId идентификатор студента
     * @param courseId идентификатор курса, соответствующий CourseInstance.id
     * @return результат выполнения регистрации
     */
    ActionStatus subscribe(long studentId, long courseId) throws StudentServiceClass.NoStudentFindException, StudentServiceClass.NoCourseFindException, StudentServiceClass.ToLateToSubOrUnsubException, StudentServiceClass.NoMoreCapacityException, StudentServiceClass.WrongStudentCategoryException, StudentServiceClass.NotCompletePreviousCoursesException;

    /**
     * Отмена регистрации студента на курс, которая возможна только в том случае, когда
     * курс еще не начался.
     *
     * @param studentId идентификатор студента
     * @param courseId идентификатор курса, соответствующий CourseInstance.id
     * @return результат выполнения отмены регистрации
     */
    ActionStatus unsubscribe(long studentId, long courseId) throws StudentServiceClass.NoCourseFindException, StudentServiceClass.NoStudentFindException, StudentServiceClass.ToLateToSubOrUnsubException, StudentServiceClass.StudentDidntSubscribedToCourseException;

    /**
     * @param studentId идентификатор студента
     * @return список всех курсов, на которые записан студент
     */
    List<CourseInstance> findAllSubscriptionsByStudentId(long studentId);
    
}
