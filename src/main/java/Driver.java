import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author FilthyHound
 * @id 18321516
 */

public class Driver {
    private final AtomicInteger printNumber = new AtomicInteger(1);

    private final Student[] students = new Student[16];
    private final Module[] modules = new Module[10];
    private final Course[] courses = new Course[4];

    public static void main(String[] args) throws UnknownDateOfBirthException {
        new Driver();
    }

    public Driver() throws UnknownDateOfBirthException {
        // initialise the class
        init();
    }

    public void init() throws UnknownDateOfBirthException {
        initialiseCourses();
        initialiseModules();
        initialiseStudents();
        handleEnrollments();
        printStudents();
        printModules();
        printCourses();
    }

    private void initialiseStudents() throws UnknownDateOfBirthException {
        int i, age;
        DateTime dob;
        String[] names = {"Billy May", "Lilly Banks", "Johnny Jacobson", "Ellie Roberts", "John Cena", "Gibson Park",
                "Marcus Smith", "Jessica Smith", "Chloe Mathers", "Felix Henderson", "Aoife Fitzgerald",
                "Sasha Phoenix", "Celeste Indigo", "Kerry FitzPatrick", "Max Payne", "Linus Einstein"};
        for(i = 0; i <= 15; i++){
            dob = computeDateOfBirth();
            age = computeAge(dob);
            students[i] = new Student(names[i], age, dob, generateRandomId());
        }
    }

    private void initialiseCourses() {
        DateTime academicStart = new DateTime(21, 9, 1, 0, 0);
        DateTime academicEnd = new DateTime(22,5,25,0,0);
        courses[0] = new Course("Mathematics", academicStart, academicEnd);
        courses[1] = new Course("Business Studies", academicStart, academicEnd);
        courses[2] = new Course("Computer Science", academicStart, academicEnd);
        courses[3] = new Course("General Science", academicStart, academicEnd);
    }

    private void initialiseModules() {
        modules[0] = new Module("Cryptography", "MA101");
        modules[0].addCourse(courses[0]);// Maths
        modules[0].addCourse(courses[2]);// Computer Science

        modules[1]  = new Module("Calculus", "MA102");
        modules[1].addCourse(courses[0]);

        modules[2]  = new Module("Physics", "SCI101");
        modules[2].addCourse(courses[3]);// General Science
        modules[2].addCourse(courses[2]);// Computer Science
        modules[0].addCourse(courses[0]);// Maths

        modules[3]  = new Module("Chemistry", "SCI102");
        modules[3].addCourse(courses[3]);

        modules[4]  = new Module("Programming", "CS101");
        modules[4].addCourse(courses[0]);
        modules[4].addCourse(courses[2]);
        modules[4].addCourse(courses[3]);

        modules[5]  = new Module("Cyber-Security", "CS102");
        modules[5].addCourse(courses[2]);

        modules[6]  = new Module("Data Analytics", "CS103");
        modules[6].addCourse(courses[1]);// Business Studies
        modules[6].addCourse(courses[2]);
        modules[6].addCourse(courses[3]);

        modules[7]  = new Module("Professional Skills", "BS101");
        modules[7].addCourse(courses[1]);
        modules[7].addCourse(courses[2]);

        modules[8]  = new Module("Internship Program", "BS102");
        modules[8].addCourse(courses[1]);
        modules[8].addCourse(courses[2]);
        modules[8].addCourse(courses[3]);

        modules[9] = new Module("Accounting", "BS103");
        modules[9].addCourse(courses[1]);
    }

    private void handleEnrollments() {
        int i;
        // School of maths
        for (i = 0; i <= 3; i++){
            students[i].addModule(modules[0]);// Module: Cryptography
            students[i].addModule(modules[1]);// Module: Calculus
            students[i].addCourse(courses[0]);// Course: Maths
            if(i == 0){
                students[i].addModule(modules[4]);// Module: Programming
                students[i].addCourse(courses[2]);// Course: Computer Science
            } else if(i == 3){
                students[i].addModule(modules[2]);// Module Physics
                students[i].addCourse(courses[3]);// Course General Science
            }
        }

        // School of business
        for(i = 4; i <= 7; i++){
            students[i].addModule(modules[7]);// Module: Professional Skills
            students[i].addModule(modules[8]);// Module: Internship Program
            students[i].addModule(modules[9]);// Module: Accounting
            students[i].addCourse(courses[1]);// Course: Business Studies

            if(i >= 6) {
                students[i].addModule(modules[6]);// Module: Data Analytics
                students[i].addCourse(courses[2]);// Course: Computer Science
            }
        }

        // School of Computer Science
        for(i = 8; i <= 11; i++){
            students[i].addModule(modules[4]);// Module: Programming
            students[i].addModule(modules[5]);// Module: Cyber-Security
            students[i].addModule(modules[6]);// Module: Data Analytics
            students[i].addModule(modules[7]);// Module: Professional Skills
            students[i].addModule(modules[8]);// Module: Internship Program
            students[i].addCourse(courses[2]);// Course: Computer Science
            if(i == 8){
                students[i].addModule(modules[0]);// Module: Cryptography
                students[i].addCourse(courses[0]);// Course: Maths
            }
        }

        // School of Science
        for(i = 12; i <= 15; i++){
            students[i].addModule(modules[2]);// Module: Physics
            students[i].addModule(modules[3]);// Module: Chemistry
            students[i].addModule(modules[8]);// Module: Internship Program
            students[i].addCourse(courses[3]);// Course: General Science
            if(i == 14){
                students[i].addModule(modules[4]);// Module: Programming
                students[i].addModule(modules[6]);// Module: Data Analytics
                students[i].addCourse(courses[2]);// Course: Computer Science
            }

            if(i == 15){
                students[i].addModule(modules[1]);// Module: Calculus
                students[i].addCourse(courses[0]);// Course: Maths
            }
        }
    }

    private void printStudents(){
        Arrays.stream(students).forEach(s -> {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("STUDENT: " + printNumber);
            System.out.println(s.toString());
            System.out.println(s.getStudentModules().toString());
            System.out.println(s.getStudentCourses().toString());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            printNumber.getAndIncrement();
        });
        printNumber.set(1);
    }

    private void printModules(){
        Arrays.stream(modules).forEach(m -> {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("MODULE: " + printNumber);
            System.out.println(m.toString());
            System.out.println(m.getModuleStudents().toString());
            System.out.println(m.getModuleCourses().toString());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            printNumber.getAndIncrement();
        });
        printNumber.set(1);
    }

    private void printCourses(){
        Arrays.stream(courses).forEach(c -> {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("COURSE: " + printNumber);
            System.out.println(c.toString());
            System.out.println(c.getCourseStudents().toString());
            System.out.println(c.getCourseModules().toString());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            printNumber.getAndIncrement();
        });
        printNumber.set(1);
    }

    public long generateRandomId(){
        return (long) Math.floor(Math.random() * 90_000_000L) + 10_000_000L;
    }

    /**
     * Creates the birthday of a student, using the gregorian calendar and a minimum and maximum birthdate year range
     * that reflects newly enrolling students for college this year.
     */
    public DateTime computeDateOfBirth(){
        GregorianCalendar gregCal = new GregorianCalendar();
        int maxBirthYear = 2004;
        int minBirthYear = 2000;
        int year = randIntInRange(minBirthYear, maxBirthYear);

        gregCal.set(Calendar.YEAR, year);

        int dayOfYear = randIntInRange(1, gregCal.getActualMaximum(Calendar.DAY_OF_YEAR));

        gregCal.set(Calendar.DAY_OF_YEAR, dayOfYear);
        int offset = 1; // months returned as 0-11, offset to fit the DateTime object
        return new DateTime(gregCal.get(Calendar.YEAR), gregCal.get(Calendar.MONTH) + offset,
                gregCal.get(Calendar.DAY_OF_MONTH), 0, 0);
    }

    /**
     * Takes a DateTime object of the students' birthday, and calculates the age of the student.
     * Throws an exception if the BirthDate is null
     */
    public int computeAge(DateTime birthDate) throws UnknownDateOfBirthException{
        if(birthDate != null) {
            LocalDate dob = birthDate.toLocalDate();
            LocalDate now = LocalDate.now();
            Years age = Years.yearsBetween(dob, now);
            return age.getYears();
        }else {
            throw new UnknownDateOfBirthException(null);
        }
    }

    public int randIntInRange(int min, int max) {
        return min + (int)Math.round(Math.random() * (max - min));
    }
}
