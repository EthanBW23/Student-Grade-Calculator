package Task_1;
import java.util.*; // Imports all the util classes and methods
// Needed for Scanner, ArrayList and List

class Grade 
{
    private String studentID; // Unique ID for the student associated with the class
    private String courseCode; // Unique course codes to associate with the class
    private double gradeValue; // The grade value 

    // Constructor to initialise the Grade object
    public Grade(String studentID, String courseCode, double gradeValue) {
        this.studentID = studentID;
        this.courseCode = courseCode;
        this.gradeValue = gradeValue;
    }

    // Method for getting Student ID
    public String getStudentID() {
        return studentID;
    }
    
    // Method for getting the course code
    public String getCourseCode() {
        return courseCode;
    }

    // Method for getting the grade value
    public double getGradeValue() {
        return gradeValue;
    }
}

class Course 
{
    private String courseName; // Name of the course
    private String courseCode; // Unique course codes to associate with the class
    private List<Student> enrolledStudents; // List of students enrolled on the course
    private List<Grade> grades; // List of grades assigned to students on the course

    // Constructor to initialise the Course object
    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.enrolledStudents = new ArrayList<>(); 
        this.grades = new ArrayList<>();
		// ArrayLists are resizeable/dynamic arrays so you can add and remove elements without creating a new array
    }

    // Method for getting the course code
    public String getCourseCode() {
        return courseCode;
    }
    
    // Method to enrol a new student onto the course
    public void enrolStudent(Student student) {
        enrolledStudents.add(student);
    }

    // Method to assign a grade to a student on a course
    public void assignGrade(String studentID, double gradeValue) {
        grades.add(new Grade(studentID, this.courseCode, gradeValue));
    }

    // Method for getting all the grades of students on a course
    public List<Grade> getGrades() {
        return grades;
    }

    // Method for getting the list of students enrolled on a course
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
}

class Student 
{
    private String studentName; // Name of the student
    private String studentID; // Unique ID for the student associated with the class
    private List<Course> enrolledCourses; // List of courses a student has enrolled onto
    private List<Grade> grades; // List of grades received by the student

    // Constructor to initialise the Student object
    public Student(String studentName, String studentID) {
        this.studentName = studentName;
        this.studentID = studentID;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new ArrayList<>();
		// ArrayLists are resizeable/dynamic arrays so you can add and remove elements without creating a new array
    }

    // Method for getting the student name
    public String getStudentName() {
        return studentName;
    }

    
    // Method for getting the student ID
    public String getStudentID() {
        return studentID;
    }

    // Method for enrolling a student onto a course
    public void enrolInCourse(Course course) {
        enrolledCourses.add(course);
        course.enrolStudent(this);
    }

    // Method for assigning a new grade to a student on a course
    public void receiveGrade(Grade grade) {
        grades.add(grade);
    }

    // Method for calculating the average grade of the student
    public double calculateAverageGrade() {
        if (grades.isEmpty()) { // If the student has received no grades...
        	return 0; // Return the average as 0
        }
        double totalGrades = 0; 
        for (Grade grade : grades) { // Loops through every grade in the grades list for that student
            totalGrades += grade.getGradeValue(); // For every grade found in the list, add that grade to the total
        }
        double gradeAverage = (totalGrades / grades.size()); // Finds the grade average by dividing the grades total by the amount of grades given to the student
        return gradeAverage;
	}
}


public class StudentGradeCalculator { 

	// Function that searches for a student in the student list with a matching inputed studentID
	private static Student findStudent(List<Student> students, String studentID) { // Uses the list of students and the inputed student ID
	     for (Student student : students) { // For every student in the list...
	         if (student.getStudentID().equals(studentID)) { // Check if the student ID passed in matches the current entry
	             return student; // Return the name of the student with the matching student ID
	         }
	     }
	     return null; // Student ID was not already in the list
	 }
	 
	// Function that searches for a course in the courses list with a matching inputed course code
	 private static Course findCourse(List<Course> courses, String courseCode) { // Uses the list of courses and the inputed course code
	     for (Course course : courses) { // For every course in the list...
	         if (course.getCourseCode().equals(courseCode)) { // Check if the course code passed in matches the current entry
	             return course; // Return the name of the course with the matching course code
	         }
	     }
	     return null; // Course code was not already in the list
	 }

	// Main Method
	public static void main(String[] args) 
	{
		System.out.println("Welcome User.");
		
		boolean loop = true;
		
		Scanner userInput = new Scanner(System.in); // Sets up scanner for user inputs
		
		List<Student> students = new ArrayList<>(); // List to store students
        List<Course> courses = new ArrayList<>(); // List to store courses
		// ArrayLists are resizeable/dynamic arrays so you can add and remove elements without creating a new array
		
		while (loop == true) // Loops through the program until the user exits the program
		{
			// Asks user for what functions they'd like to perform
			System.out.println("\n"
						+ "What function would you like to perform?\n"
						+ "1. Add new student\n"
						+ "2. Add new course\n"
						+ "3. Enrol student in course\n"
						+ "4. Assign a grade to a student in a course\n"
						+ "5. Receive average grade of student\n"
						+ "6. List of students in course, with their grades\n"
						+ "7. Exit\n"
						+ "");
			int userChoice = userInput.nextInt(); // scans and stores the user's input
		
			switch(userChoice) // Switch for choosing the specific function the user chose
			{
				case 1: // Add new student
					System.out.println("State the name of the student.");
					String studentName = userInput.next(); // Stores inputed name of student
					
					System.out.println("State the ID of the student.");
					String studentID = userInput.next(); // Stores inputed ID of student
					
					if (findStudent(students, studentID) != null) { // Checks if the inputted studentID is in the list already
                        System.out.println("Student ID already exists."); // Doesn't add new entry
                    } 
					else {
                        students.add(new Student(studentName, studentID)); // Adds a new student entry to the list
                        System.out.println("Student added.");
                        break;
                    }
                    break;
					
				case 2: // Add new course
					System.out.println("State the name of the course.");
					String courseName = userInput.next(); // Stores inputed name of course
					
					System.out.println("State the code of the course.");
					String courseCode = userInput.next(); // Stores inputed code of course
					
					if (findCourse(courses, courseCode) != null) { // Checks if the inputted course code is in the list already
                        System.out.println("Course code already exists."); // Doesn't add new entry
                    } 
					else {
                        courses.add(new Course(courseName, courseCode)); // Adds new course entry to the list
                        System.out.println("Course added.");
                        break;
                    }
                    break;
					
				case 3: // Enrol student in course				
					System.out.print("Enter student ID: ");
                    studentID = userInput.next(); // Stores inputed ID of student
                    
                    System.out.print("Enter course code: ");
                    courseCode = userInput.next(); // Stores inputed code of course
                    
                    Student student = findStudent(students, studentID); // Uses pre-made function to check if the student is in the list from the inputed ID
                    Course course = findCourse(courses, courseCode); // Uses pre-made function to check if the course is in the list from the inputed code
                    if (student != null && course != null) { // If the student ID and course code inputed are in their lists...
                        student.enrolInCourse(course); // Enrols the student (corresponding to the ID) onto the course (corresponding to the code)
                        System.out.println("Student enrolled in course.");
                    } else {
                        System.out.println("Invalid student ID or course code."); // Either the student ID or course code were not in their lists
                    }
                    break;
					
				case 4: // Assign a grade to a student in a course
					System.out.print("Enter student ID: ");
                    studentID = userInput.next(); // Stores inputed ID of student
                    
                    System.out.print("Enter course code: ");
                    courseCode = userInput.next(); // Stores inputed code of course
                    
                    System.out.print("Enter grade value (out of 100): ");
                    double gradeValue = userInput.nextDouble(); // Stores inputed value of grade
                    
                    student = findStudent(students, studentID); // Uses pre-made function to check if the student is in the list from the inputed ID
                    course = findCourse(courses, courseCode); // Uses pre-made function to check if the course is in the list from the inputed code
                    if (student != null && course != null) { // If the student ID and course code inputed are in their lists...
                        course.assignGrade(studentID, gradeValue); // Assigns grade to the student found in the course class
                        student.receiveGrade(new Grade(studentID, courseCode, gradeValue)); // Adds a new grade entry in the student class
                        System.out.println("Grade assigned.");
                    } else {
                        System.out.println("Invalid student ID or course code."); // Either the student ID or course code were not in their lists
                    }
                    break;
					
				case 5: // Receive average grade of student
					System.out.print("Enter student ID: "); 
                    studentID = userInput.next(); // Stores inputed ID of student
                    student = findStudent(students, studentID); // Uses pre-made function to check if the student is in the list from the inputed ID
                    if (student != null) { // If the student ID inputed is in the list...
                        System.out.println("Average grade: " + student.calculateAverageGrade()); // Returns the calculated average grade of the student
                    } else {
                        System.out.println("Student not found."); // Student was not in the list
                    }
                    break;
				
				case 6: // List of student in course
					 System.out.print("Enter course code: ");
	                    courseCode = userInput.next(); // Stores inputed code of course
	                    course = findCourse(courses, courseCode); // Uses pre-made function to check if the course is in the list from the inputed code
	                    if (course != null) { // If the course code inputed is in the list...
	                        System.out.println("Students in " + courseCode + ":");
	                        for (Student students2 : course.getEnrolledStudents()) { // Loops through all the entries in the list
	                            System.out.println(students2.getStudentName()); // Returns all the students in the chosen course
	                        }
	                    } else {
	                        System.out.println("Course not found."); // Course was not in the list
	                    }
	                    break;
					
				case 7: // Exit
					System.out.println("Exiting.");
					userInput.close();
					loop = false; // Stops loop
					break;
				
				default: // Invalid input
					System.out.println("Invalid input. Please try again.");
					break;
			}
		}
	}
}
