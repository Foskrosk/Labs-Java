package lab2.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;

import lab2.registration.model.CourseInfo;
import lab2.registration.model.CourseInstance;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Класс для чтения информации о студентах из файлов
 */
public class CourseDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();

    public List<CourseInfo> readCourseInfoData() throws IOException {
        return List.of(objectMapper.readValue(new File("src/main/resources/courseInfos.json"), CourseInfo[].class));
    }

    public List<CourseInstance> readCourseInstanceData() throws IOException {
        return List.of(objectMapper.readValue(new File("src/main/resources/courseInstances.json"), CourseInstance[].class));
    }
}
