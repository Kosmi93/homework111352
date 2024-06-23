package bip.online.homework111352;

import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.model.Student;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TestConstant {
    public static final Long MOCK_FACULTY_ID = 1L;
    public static final String MOCK_FACULTY_NAME = "Faculty name";
    public static final String MOCK_FACULTY_COLOR = "Faculty color";
    public static final Faculty MOCK_FACULTY = new Faculty(
            MOCK_FACULTY_ID,
            MOCK_FACULTY_NAME,
            MOCK_FACULTY_COLOR);
    public static final List<Faculty> MOCK_FACULTIES = Collections.singletonList(MOCK_FACULTY);

    public static final Long MOCK_STYDENT_ID = 1L;
    public static final String MOCK_STYDENT_NAME = "Student name";
    public static final Integer MOCK_STYDENT_AGE = 20;
    public static final Student MOCK_STUDENT = new Student(
            MOCK_STYDENT_ID,
            MOCK_STYDENT_NAME,
            MOCK_STYDENT_AGE,
            MOCK_FACULTY
    );

    public static final List<Student> MOCK_STUDENTS = Collections.singletonList(MOCK_STUDENT);
}
