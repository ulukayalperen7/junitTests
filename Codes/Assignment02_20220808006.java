import java.util.ArrayList;
import java.util.List;

public class Assignment02_20220808006 {
    // author: ALPEREN ULUKAYA
    // since: 01-04-2024
    public static void main(String[] args) {
        Department cse = new Department("CSE", "Computer Engineering");
        Teacher t = new Teacher("Joseph Ledet", "josephledet@akdeniz.edu.tr", 123L, cse, 1);
        Course c101 = new Course(cse, 101, "Programming 1", "Introduction to Programming", 6, t);
        Course c102 = new Course(cse, 102, "Programming 2", "Object Oriented Programming", 4, t);
        Student s = new Student("Test Student", "me@somewhere.com", 123L, cse);
        s.addCourse(c101, 80);
        s.addCourse(c102, 30);
        System.out.println(s.getAKTS());
        System.out.println(s.getAttemptedAKTS());
        System.out.println(s.getGPA());
        System.out.println(s);
        s = new GradStudent("Test GRADSTUDENT", "me@somewhere.com", 456L, cse, 1, "MDE");
        s.addCourse(c101, 80);
        s.addCourse(c102, 70);
        System.out.println(s.getAKTS());
        System.out.println(s.getAttemptedAKTS());
        System.out.println(s.getGPA());
        System.out.println(s);

        cse.setChair(t);
        Department math = new Department("MATH", "Mathematics");
        System.out.println(cse.getCode() + " Chair = " + cse.getChair());
        t.setDepartment(math);
        System.out.println(cse.getCode() + " Chair = " + cse.getChair());
        t.setDepartment(cse);
        System.out.println(cse.getCode() + " Chair = " + cse.getChair());

    }
}

class Department {

    private String code;
    private String name;
    private Teacher chair;

    public Department(String code, String name) {
        setCode(code);
        setName(name);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code.length() == 3 || code.length() == 4) {
            this.code = code;
        } else {
            throw new InvalidValue(code, "3 or 4", "Department");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Teacher getChair() {
        return chair;
    }

    public void setChair(Teacher chair) {

        if (chair != null) {
            if (hashCode() == chair.getDepartment().hashCode()) {
                this.chair = chair;
            } else {
                throw new DepartmentMismatchException(this, chair);
            }
        } else {
            this.chair = chair;
        }
    }
}

class Course {

    private Department department;
    private Teacher teacher;
    private String title;
    private String description;
    private int AKTS;
    private int courseNumber;

    public Course(Department department, int courseNumber, String title, String description, int AKTS,
            Teacher teacher) {

        if (teacher.getDepartment() == department) {
            setDepartment(department);
            setCourseNumber(courseNumber);
            setTeacher(teacher);
            setTitle(title);
            setDescription(description);
            setAKTS(AKTS);
        } else {
            throw new DepartmentMismatchException(department, teacher);
        }

    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        if (department == null || department.getChair() == null || department.getChair() == teacher) {
            this.department = department;
        } else {
            throw new DepartmentMismatchException(department, teacher);
        }
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        if (getDepartment() == department) {
            this.teacher = teacher;
        } else {
            throw new DepartmentMismatchException(department, teacher);
        }
    }

    public int getAKTS() {
        return AKTS;
    }

    public void setAKTS(int aKTS) {
        if (aKTS > 0)
            AKTS = aKTS;
        else
            throw new InvalidValue("AKTS", " must be positive", "class");
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        if (courseNumber >= 100 && courseNumber <= 999) {
            this.courseNumber = courseNumber;
        } else if (courseNumber >= 5000 && courseNumber <= 5999) {
            this.courseNumber = courseNumber;
        } else if (courseNumber >= 7000 && courseNumber <= 7999) {
            this.courseNumber = courseNumber;
        } else {
            throw new InvalidValue("course number", "is not ", "valid");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String courseCode() {
        return getDepartment() + "" + getCourseNumber();
    }

    @Override
    public String toString() {
        return String.format("%s - %s(%d)", courseCode(), getTitle(), AKTS);
    }

}

abstract class Person {

    private Department department;
    private String name;
    private String email;
    private Long ID;

    public Person(String name, String email, Long ID, Department department) {
        setName(name);
        setEmail(email);
        setID(ID);
        setDepartment(department);
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (isValidEmailAddress(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException(" invalid email!");
        }
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]"
                + "+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}"
                + "\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long iD) {
        this.ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {

        return name + " (" + ID + ")" + " - " + email;
    }

}

class Teacher extends Person {

    private int rank;

    public Teacher(String name, String email, Long ID, Department department, int rank) {
        super(name, email, ID, department);
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void promote() {
        if (this.rank < 5) {
            rank++;
        } else {
            throw new InvalidRankException(rank);
        }
    }

    public void demote() {
        if (1 < this.rank) {
            rank--;
        } else {
            throw new InvalidRankException(rank);
        }
    }

    @Override
    public void setDepartment(Department department) {
        if (getDepartment() != null) {
            if (getDepartment().getChair() != null) {
                if (getDepartment().getChair().hashCode() == hashCode()) {
                    getDepartment().setChair(null);
                    setDepartment(department);
                }
            }
        } else
            super.setDepartment(department);
    }

    public String getTitle() {

        switch (rank) {
            case 1:
                return "Teaching Assistant";
            case 2:
                return "Lecturer";
            case 3:
                return "Assistant Professor";
            case 4:
                return "Associate Professor";
            case 5:
                return "Professor";
            default:
                throw new InvalidValue("Title", "must be", "between 1-5");
        }
    }
}

class Student extends Person {

    private List<Course> coursesTaken = new ArrayList<>(); // taken lessons
    private List<Double> gradesTaken = new ArrayList<>(); // taken grades

    public Student(String name, String email, Long ID, Department department) {
        super(name, email, ID, department);
    }

    public int getAKTS() {
        int passedAKTS = 0;
        for (int i = 0; i < coursesTaken.size(); i++) {
            if (gradesTaken.get(i) > 60) {
                passedAKTS += (coursesTaken.get(i)).getAKTS();
            }
        }
        return passedAKTS;
    }

    public int getAttemptedAKTS() {
        int totalAKTS = 0;
        for (int i = 0; i < gradesTaken.size(); i++) {
            totalAKTS += (coursesTaken.get(i)).getAKTS();
        }
        return totalAKTS;
    }

    public void addCourse(Course course, double grade) {

        if (grade < 0.0 || grade > 100.0) {
            throw new InvalidGradeException(grade);
        } else {
            int index = -1; // for invalids
            for (int i = 0; i < coursesTaken.size(); i++) {
                if (coursesTaken.get(i) == course) {
                    index = i;
                }
            }
            if (index == -1) {
                coursesTaken.add(course);
                gradesTaken.add(grade);
            } else {
                gradesTaken.set(index, grade);
            }
        }

    }

    public double getGPA() {
        double sum = 0;
        for (int i = 0; i < coursesTaken.size(); i++) {
            sum += (coursesTaken.get(i).getAKTS()) * gpaPoints(gradesTaken.get(i));
        }
        return sum / getAttemptedAKTS();
    }

    public static double gpaPoints(double grade) {
        if (grade >= 88.0) {
            return 4.0;
        } else if (grade >= 81.0) {
            return 3.5;
        } else if (grade >= 74.0) {
            return 3.0;
        } else if (grade >= 67.0) {
            return 2.5;
        } else if (grade >= 60.0) {
            return 2.0;
        } else if (grade >= 53.0) {
            return 1.5;
        } else if (grade >= 46.0) {
            return 1.0;
        } else if (grade >= 35.0) {
            return 0.5;
        } else {
            return 0.0;
        }
    }

    public double courseGPAPoints(Course course) {

        int indexOfCourse = coursesTaken.indexOf(course);
        if (indexOfCourse == -1) {
            throw new CourseNotFoundException(this, course);
        }
        double grade = gradesTaken.get(indexOfCourse);

        if (grade >= 88 || grade <= 100) {
            return 4.0;
        } else if (grade >= 81) {
            return 3.5;
        } else if (grade >= 74) {
            return 3.0;
        } else if (grade >= 67) {
            return 2.5;
        } else if (grade >= 60) {
            return 2.0;
        } else if (grade >= 53) {
            return 1.5;
        } else if (grade >= 46) {
            return 1.0;
        } else if (grade >= 35) {
            return 0.5;
        } else {
            return 0.0;
        }
    }

    public String courseGradeLetter(Course course) {
        int indexOfCourse = coursesTaken.indexOf(course);
        if (indexOfCourse == -1) {
            throw new CourseNotFoundException(this, course);
        }
        double grade = gradesTaken.get(indexOfCourse);
        if (grade >= 88 || grade <= 100) {
            return "AA";
        } else if (grade >= 81) {
            return "BA";
        } else if (grade >= 74) {
            return "BB";
        } else if (grade >= 67) {
            return "CB";
        } else if (grade >= 60) {
            return "CC";
        } else if (grade >= 53) {
            return "DC";
        } else if (grade >= 46) {
            return "DD";
        } else if (grade >= 35) {
            return "FD";
        } else {
            return "FF";
        }
    }

    public String courseResult(Course course) {
        int indexOfCourse = coursesTaken.indexOf(course);
        if (indexOfCourse == -1) {
            throw new CourseNotFoundException(this, course);
        }
        double grade = gradesTaken.get(indexOfCourse);
        if (grade >= 88 || grade <= 100) {
            return "passed";
        } else if (grade >= 81) {
            return "passed";
        } else if (grade >= 74) {
            return "passed";
        } else if (grade >= 67) {
            return "passed";
        } else if (grade >= 60) {
            return "passed";
        } else if (grade >= 53) {
            return "Conditionally Passed";
        } else if (grade >= 46) {
            return "Conditionally Passed";
        } else if (grade >= 35) {
            return "failed";
        } else {
            return "failed";
        }
    }

    public void setCoursesTaken(List<Course> coursesTaken) {
        this.coursesTaken = coursesTaken;
    }

    public List<Course> getCoursesTaken() {
        return coursesTaken;
    }

    public void setGradesTaken(List<Double> gradesTaken) {
        this.gradesTaken = gradesTaken;
    }

    public List<Double> getGradesTaken() {
        return gradesTaken;
    }

    @Override
    public String toString() {

        return super.toString() + " -GPA: " + getGPA();
    }
}

class GradStudent extends Student {

    private int rank;
    private String thesisTopic;
    private List<Course> coursesTaken = new ArrayList<>(); // taken lessons
    private List<Double> gradesTaken = new ArrayList<>(); // taken grades

    public GradStudent(String name, String email,
            long ID, Department department, int rank, String thesisTopic) {
        super(name, email, ID, department);
        setRank(rank);
        setThesisTopic(thesisTopic);
    }

    public void setCoursesTaken(List<Course> coursesTaken) {
        super.setCoursesTaken(coursesTaken);
    }

    public List<Course> getCoursesTaken() {
        return super.getCoursesTaken();
    }

    public void setGradesTaken(List<Double> gradesTaken) {
        super.setGradesTaken(gradesTaken);
    }

    public List<Double> getGradesTaken() {
        return super.getGradesTaken();
    }

    public void setRank(int rank) {
        if (rank == 1 || rank == 2 || rank == 3) {
            this.rank = rank;
        } else {
            throw new InvalidRankException(rank);
        }
    }

    public int getRank() {
        return rank;
    }

    public void setThesisTopic(String thesisTopic) {
        this.thesisTopic = thesisTopic;
    }

    public String getThesisTopic() {
        return thesisTopic;
    }

    @Override
    public double courseGPAPoints(Course course) {

        int indexOfCourse = coursesTaken.indexOf(course);
        if (indexOfCourse == -1) {
            throw new CourseNotFoundException(this, course);
        }
        double grade = gradesTaken.get(indexOfCourse);

        if (grade >= 90 || grade <= 100) {
            return 4.0;
        } else if (grade >= 85) {
            return 3.5;
        } else if (grade >= 80) {
            return 3.0;
        } else if (grade >= 75) {
            return 2.5;
        } else if (grade >= 70) {
            return 2.0;
        } else {
            return 0.0;
        }
    }

    @Override
    public String courseGradeLetter(Course course) {

        int indexOfCourse = coursesTaken.indexOf(course);
        if (indexOfCourse == -1) {
            throw new CourseNotFoundException(this, course);
        }
        double grade = gradesTaken.get(indexOfCourse);

        if (grade >= 90 || grade <= 100) {
            return "AA";
        } else if (grade >= 85) {
            return "BA";
        } else if (grade >= 80) {
            return "BB";
        } else if (grade >= 75) {
            return "CB";
        } else if (grade >= 70) {
            return "CC";
        } else {
            return "FF";
        }
    }

    @Override
    public String courseResult(Course course) {

        int indexOfCourse = coursesTaken.indexOf(course);
        if (indexOfCourse == -1) {
            throw new CourseNotFoundException(this, course);
        }
        double grade = gradesTaken.get(indexOfCourse);

        if (grade >= 90 || grade <= 100) {
            return "passed";
        } else if (grade >= 85) {
            return "passed";
        } else if (grade >= 80) {
            return "passed";
        } else if (grade >= 75) {
            return "passed";
        } else if (grade >= 70) {
            return "passed";
        } else {
            return "failed";
        }
    }

    @Override
    public double getGPA() {
        double sum = 0;
        for (int i = 0; i < getCoursesTaken().size(); i++) {
            sum += (getCoursesTaken().get(i).getAKTS()) * gpaPoints(getGradesTaken().get(i));
        }
        return sum / getAttemptedAKTS();
    }

    public static double gpaPoints(double grade) {
        if (grade >= 88.0) {
            return 4.0;
        } else if (grade >= 81.0) {
            return 3.5;
        } else if (grade >= 74.0) {
            return 3.0;
        } else if (grade >= 67.0) {
            return 2.5;
        } else if (grade >= 60.0) {
            return 2.0;
        } else if (grade >= 53.0) {
            return 1.5;
        } else if (grade >= 46.0) {
            return 1.0;
        } else if (grade >= 35.0) {
            return 0.5;
        } else {
            return 0.0;
        }
    }

    public String getLevel() {

        switch (rank) {
            case 1:
                return "Master's Student";
            case 2:
                return "Doctoral Student";
            case 3:
                return "Doctoral Candidate";
            default:
                throw new InvalidValue("value of level", "1-2-3", "valid");
        }
    }

}

class CourseNotFoundException extends RuntimeException {

    private Student student;
    private Course course;

    public CourseNotFoundException(Student student, Course course) {
        this.student = student;
        this.course = course;

    }

    @Override
    public String toString() {

        return "CourseNotFoundException: " + student.getID() + " has not yet taken" + course.courseCode();
    }

}

class DepartmentMismatchException extends RuntimeException {

    private Department department;
    private Teacher person;
    private Course course;

    public DepartmentMismatchException(Course course, Teacher person) {
        this.course = course;
        this.person = person;
        this.department = null;
    }

    public DepartmentMismatchException(Department department, Teacher person) {
        this.department = department;
        this.person = person;
        this.course = null;
    }

    @Override
    public String toString() {
        if (course == null) {
            return "DepartmentMismatchException: " + person.getName() + "(" + person.getID() + ") cannot be chair of "
                    + department.getCode() + " because he/she is currently assigned to "
                    + person.getDepartment().getName();
        } else {
            return "DepartmentMismatchException: " + person.getName() + "(" + person.getID() + ") cannot teach "
                    + course.courseCode() + " because he/she is currently assigned to "
                    + person.getDepartment().getName();
        }
    }
}

class InvalidGradeException extends RuntimeException {

    private double grade;

    public InvalidGradeException(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "InvalidGradeException: " + grade;
    }

}

class InvalidRankException extends RuntimeException {

    private int rank;

    public InvalidRankException(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "InvalidRankException: " + rank;
    }
}

class InvalidValue extends RuntimeException {

    private String invalid;
    private String valids;
    private String classAtt;

    public InvalidValue(String invalid, String valids, String classAtt) {

        this.invalid = invalid;
        this.valids = valids;
        this.classAtt = classAtt;
    }

    @Override
    public String toString() {
        return "InvalidValueException: " + classAtt + ", " + invalid + ", valid values: " + valids;
    }
}
