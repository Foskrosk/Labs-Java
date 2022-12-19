package lab2.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.registration.model.Student;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Класс для чтения информации о студентах из файлов
 */
public class StudentDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @return список студентов-бакалавров
     */
    public List<Student> readBachelorStudentData() throws IOException {
        return List.of(objectMapper.readValue(new File("src/main/resources/bachelorStudents.json"), Student[].class));
    }

    /**
     * @return список студентов-магистров
     */
    public List<Student> readMasterStudentData() throws IOException {
        return List.of(objectMapper.readValue(new File("src/main/resources/masterStudents.json"), Student[].class));
    }
}
