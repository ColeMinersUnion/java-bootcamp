package com.academy.student;

import java.util.Scanner;

public class StudentManager {

    private static final int MAX_STUDENTS = 20;

    private final Student[] students = new Student[MAX_STUDENTS];
    private int studentCount = 0;
    private final Scanner scanner;

    public StudentManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        System.out.println("====================================");
        System.out.println("Student Management System");
        System.out.println("====================================");
        System.out.println("1. Add Student");
        System.out.println("2. Display Students");
        System.out.println("3. Search Student");
        System.out.println("4. Average Marks");
        System.out.println("5. Exit");
        System.out.print("Enter Choice : ");
    }

    // Methods addStudent, displayStudents, searchStudent, calculateAverage
    // will be filled in later steps.
    public void addStudent(){
        if(studentCount >= MAX_STUDENTS){
            System.out.println("Academy is Full");
            return;
        }

        int student_ID;
        String name;
        String course;
        double marks; //grade


        System.out.print("Student ID:");
        boolean isNumber = true;


        //This is wrong, I shouldn't be running `isUniqueID()` as often
        while(true){
            System.out.print("Student ID:");
            try{
                student_ID = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ID must be a numebr");
                System.out.println("Please try again.");
                continue;
            }


            if (student_ID < 0) {
                System.out.println("Unable to add student.");
                System.out.println("Student ID was negative");
            } else if (!isUniqueID(student_ID)) {
                System.out.println("Unable to add student.");
                System.out.println("Duplicate Student ID found.");
            } else {
                break;
            }
            System.out.println("Please try again.");

        }

        System.out.print("\nName: ");
        name = scanner.nextLine();
        if (name.isEmpty()){
            System.out.println("No student name given.");
            return;
        }

        System.out.print("\nCourse: ");
        course = scanner.nextLine();

        System.out.print("\nMarks: ");
        marks = Double.parseDouble(scanner.nextLine());
        if (marks > 100 || marks < 0){ //Impossible Grade
            System.out.println("Unable to add student.");
            System.out.println("These marks were impossible to achieve");
            return;
        }

        //Created a new student, and adding it to the student array.
        students[studentCount] = new Student(student_ID, name, course, marks);

        studentCount++;

        System.out.println("Student Added Successfully");
        return;
    }

    //helper method, checks an ID against all other student IDs
    private boolean isUniqueID(int ID){
        if(studentCount == 0){
            return true;
        }
        for (int i = 0; i < studentCount; i++){
            if (ID == students[i].getStudentId()){
                return false;
            }
        }
        return true;
    }

    public void displayStudents(){

        System.out.println("----------------------------------------------------------");
        System.out.printf("%-8s %-20s %-15s %-8s%n",
                "ID",
                "Name",
                "Course",
                "Marks");
        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < studentCount; i++){
            System.out.printf("%-8d %-20s %-15s %-8.2f%n",
                    students[i].getStudentId(),
                    students[i].getName(),
                    students[i].getCourse(),
                    students[i].getMarks());
            System.out.println("----------------------------------------------------------");

        }
    }

    public void searchStudent(){
        if(studentCount == 0){
            System.out.println("No students to search");
            return;
        }

        System.out.print("Student ID: ");

        int search_ID = Integer.parseInt(scanner.nextLine());
        boolean isFound = false;


        for (int i = 0; i < studentCount; i++){
            if (students[i].getStudentId() == search_ID){
                students[i].display();
                isFound = true;
                break;
            }
        }

        if(!isFound){
            System.out.println("Student not found");
        }
        return;
    }

    public void calculateAverage(){

        if (studentCount == 0){
            System.out.println("There are no students to average.");
            return;
        }

        double runningTotal = 0.0;

        for(int i = 0; i < studentCount; i++){
            runningTotal += students[i].getMarks();
        }

        double total = runningTotal/studentCount;
        System.out.printf("Average Marks %.2f\n", total);
    }


}