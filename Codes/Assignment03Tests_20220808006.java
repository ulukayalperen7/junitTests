import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.Before;

import static org.junit.Assert.*;
import org.junit.Test;

public class Assignment03Tests_20220808006 {

    // department class tests
    @Test
    public void DepartmentCodeNameTests() {
        Department department = new Department("cse", "COMP");
        assertEquals("COMP", department.getName());
        assertEquals("cse", department.getCode());
    }

    @Test
    public void DepartmentChairTests() {
        Department department = new Department("cse", "COMP");
        Teacher teacher = new Teacher("Alperen U", "alperenulukaya07@gmail.com", 2022L, department, 2);
        department.setChair(teacher);
        assertEquals(teacher, department.getChair());
    }

    @Test
    public void DepartmentSetCodeValidTest() {
        Department department = new Department("PNG", "JAVA");

        department.setCode("ABC");
        assertEquals("ABC", department.getCode());

        department.setCode("DEFG");
        assertEquals("DEFG", department.getCode());
    }

    @Test
    public void DepartmentConstructorCodeTest() {
        Department department = new Department("123", "MATH");
        assertEquals("123", department.getCode());
    }

    @Test
    public void DepartmentConstructorCodeBiggerInvalidExceptionTest() {
        Department department = new Department("123", "MATH");
        assertThrowsExactly(InvalidValue.class, () -> {
            department.setCode("123456");
        });
    }

    @Test
    public void DepartmentConstructorCodeSmallerInvalidExceptionTest() {
        Department department = new Department("123", "MATH");
        assertThrowsExactly(InvalidValue.class, () -> {
            department.setCode("12");
        });
    }

    private Department department;
    private Course course;
    private Teacher teacher;

    @Test
    public void DepartmentSetCodeThrowInvalidValueBiggerThanTest() {
        Department department = new Department("PNG", "JAVA");
        assertThrowsExactly(InvalidValue.class, () -> {
            department.setCode("TESTJAVA");
        });
    }

    @Test
    public void DepartmentSetCodeThrowInvalidValueLessThanTest() {
        Department department = new Department("PNG", "JAVA");
        assertThrowsExactly(InvalidValue.class, () -> {
            department.setCode("TE");
        });
    }

    @Test
    public void DepartmentGetNameTest() {
        Department department = new Department("PNG", "JAVA");
        assertEquals("JAVA", department.getName());
    }

    @Test
    public void DepartmentSetNameTest() {
        Department department = new Department("PNG", "JAVA");
        assertEquals("JAVA", department.getName());
    }

    @Test
    public void DepartmentGetChairTest() {
        Department department = new Department("PNG", "JAVA");
        Teacher chair = new Teacher("alperen", "alperenulukaya07@gmail.com", 123L,
                department, 1);
        chair.setDepartment(department);
        department.setChair(chair);
        assertEquals(chair, department.getChair());
    }

    @Test
    public void DepartmentChairThrowExceptionTest() {

        Department department = new Department("cse", "computer");
        Department department2 = new Department("sfw", "software");
        Teacher teacher = new Teacher("admin", "alperenulukaya07@gmail.com", 123L,
                department2, 1);
        teacher.setDepartment(department);
        department2.setChair(teacher);
        assertThrowsExactly(DepartmentMismatchException.class, () -> {
            department.setChair(teacher);
        });
    }

    // Course clsas tests
    @Test
    public void CourseGetSetMethodsTests() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertEquals(department, course.getDepartment());
        assertEquals(teacher, course.getTeacher());
        assertEquals("abc", department.getCode());
    }

    @Test
    public void CourseDepartmentConstrcutorTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertEquals(department, course.getDepartment());
    }

    @Test
    public void CourseConstrcutorTeacherTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertEquals(teacher, course.getTeacher());
    }

    @Test
    public void CourseConstrcutorNumberTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertEquals(102, course.getCourseNumber());
    }

    @Test
    public void CourseConstructorTitleTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertEquals("xx2", course.getTitle());
    }

    @Test
    public void CourseConstructorDescriptionTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertEquals("csecse", course.getDescription());
    }

    @Test
    public void CourseConstructorAKTSTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertEquals(2, course.getAKTS());
    }

    @Test
    public void CourseSetAKTSTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);

        assertThrowsExactly(InvalidValue.class, () -> {
            course.setAKTS(-1);
        });
        assertThrowsExactly(InvalidValue.class, () -> {
            course.setAKTS(0);
        });
    }

    @Test
    public void CourseGetAktsTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        course.setAKTS(4);
        assertEquals(4, course.getAKTS());
    }

    @Test
    public void CourseAktsValidTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        course.setAKTS(2);
        assertEquals(2, course.getAKTS());
    }

    @Test
    public void CourseNumberTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        course.setCourseNumber(100);

        assertEquals(100, course.getCourseNumber());
        course.setCourseNumber(999);
        assertEquals(999, course.getCourseNumber());
        course.setCourseNumber(555);
        assertEquals(555, course.getCourseNumber());

        course.setCourseNumber(5000);
        assertEquals(5000, course.getCourseNumber());
        course.setCourseNumber(5999);
        assertEquals(5999, course.getCourseNumber());
        course.setCourseNumber(5555);
        assertEquals(5555, course.getCourseNumber());

        course.setCourseNumber(7000);
        assertEquals(7000, course.getCourseNumber());
        course.setCourseNumber(7555);
        assertEquals(7555, course.getCourseNumber());
        course.setCourseNumber(7999);
        assertEquals(7999, course.getCourseNumber());
    }

    @Test
    public void CourseNumberThrowTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertThrowsExactly(InvalidValue.class, () -> {
            course.setCourseNumber(1);
        });
    }

    @Test
    public void CourseGetDescriptionTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        course.setDescription("explain");
        assertEquals("explain", course.getDescription());
    }

    @Test
    public void CourseSetDescriptionTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        course.setDescription("explain");
        assertEquals("explain", course.getDescription());
    }

    @Test
    public void CourseGetTitleTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        course.setTitle("xxx");
        assertEquals("xxx", course.getTitle());
    }

    @Test
    public void CourseSetTitleTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        course.setTitle("xxx");
        assertEquals("xxx", course.getTitle());
    }

    @Test
    public void CourseCodeTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        course.setDepartment(department);
        course.setCourseNumber(123);
        assertEquals(department + "" + course.getCourseNumber(), course.courseCode());
    }

    @Test
    public void CourseToStringTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertEquals(String.format("%s - %s(%d)", course.courseCode(), course.getTitle(), course.getAKTS()),
                course.toString());
    }

    // Teacher class tests
    @Test
    public void TeacherPromoteLessThanFiveTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 4);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        teacher.promote();
        assertEquals(5, teacher.getRank());
    }

    @Test
    public void TeacherPromoteBiggerThanFiveThrowExceptionTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 6);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertThrowsExactly(InvalidRankException.class, () -> {
            teacher.promote();
        });
    }

    @Test
    public void Teacher2PromoteBiggerThanFiveThrowExceptionTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 5);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertThrowsExactly(InvalidRankException.class, () -> {
            teacher.promote();
        });
    }

    @Test
    public void TeacherDemoteLessThanOneTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 1);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertThrowsExactly(InvalidRankException.class, () -> {
            teacher.demote();
        });
    }

    @Test
    public void TeacherDemoteLessThanOneTest2() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 0);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertThrowsExactly(InvalidRankException.class, () -> {
            teacher.demote();
        });
    }

    @Test
    public void TeacherDemoteBiggerThanOneTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 2);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        teacher.demote();
        assertEquals(1, teacher.getRank());
    }

    @Test
    public void TeacherSetDepartmentTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 2);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        department.setChair(teacher);
        teacher.setDepartment(department);
        assertEquals(department, teacher.getDepartment());
    }

    Person person;

    @Test
    public void TeacherGetTitleTest1() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 1);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertEquals("Teaching Assistant", teacher.getTitle());
    }

    @Test
    public void TeacherGetTitleTest2() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 2);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertEquals("Lecturer", teacher.getTitle());
    }

    @Test
    public void TeacherGetTitleTest3() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 3);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertEquals("Assistant Professor", teacher.getTitle());
    }

    @Test
    public void TeacherGetTitleTest4() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 4);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertEquals("Associate Professor", teacher.getTitle());
    }

    @Test
    public void TeacherGetTitleTest5() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 5);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertEquals("Professor", teacher.getTitle());
    }

    @Test
    public void TeacherGetTitleThrowExceptionTest() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 6);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertThrowsExactly(InvalidValue.class, () -> {
            teacher.getTitle();
        });
    }

    @Test
    public void TeacherGetTitleThrowExceptionTest2() {
        Department department = new Department("abc", "CE");
        Teacher teacher = new Teacher("berk", "abwbrb7@gmail.com", 123L, department, 0);
        Course course = new Course(department, 102, "xx2", "csecse", 2, teacher);
        assertThrowsExactly(InvalidValue.class, () -> {
            teacher.getTitle();
        });
    }
}