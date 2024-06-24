package bip.online.homework111352;

import bip.online.homework111352.model.Faculty;
import bip.online.homework111352.model.Student;

import java.util.Collections;
import java.util.List;

public class TestConstant {
    public static final Long MOCK_FACULTY_ID = 1L;
    public static final String MOCK_FACULTY_NAME = "Faculty name";
    public static final String MOCK_FACULTY_COLOR = "Faculty color";
    public static final String MOCK_FACULTY_NEW_NAME = "Faculty new name";
    public static final Faculty MOCK_FACULTY = new Faculty(
            MOCK_FACULTY_ID,
            MOCK_FACULTY_NAME,
            MOCK_FACULTY_COLOR);
    public static final List<Faculty> MOCK_FACULTIES = Collections.singletonList(MOCK_FACULTY);

    public static final Long MOCK_STUDENT_ID = 1L;
    public static final String MOCK_STUDENT_NAME = "Student name";
    public static final String MOCK_STUDENT_NEW_NAME = "Student new name";
    public static final Integer MOCK_STUDENT_AGE = 20;
    public static final Student MOCK_STUDENT_AND_FACULTY = new Student(
            MOCK_STUDENT_ID,
            MOCK_STUDENT_NAME,
            MOCK_STUDENT_AGE,
            MOCK_FACULTY
    );
    public static final Student MOCK_STUDENT = new Student(
            MOCK_STUDENT_ID,
            MOCK_STUDENT_NAME,
            MOCK_STUDENT_AGE
    );

    public static final List<Student> MOCK_STUDENTS = Collections.singletonList(MOCK_STUDENT_AND_FACULTY);
}
