package lab2.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.registration.model.Instructor;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Класс для чтения информации о студентах из файлов
 */
public class InstructorDataReader {
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Instructor> readCourseInstrucorData() throws IOException {
        return List.of(objectMapper.readValue(new File("src/main/resources/instructors.json"), Instructor[].class));
    }
}
