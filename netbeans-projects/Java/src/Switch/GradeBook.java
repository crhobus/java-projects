package Switch;

import java.util.Scanner;

public class GradeBook {

    private String courseName;
    private int total;
    private int gradeCounter;
    private int aCount;
    private int bCount;
    private int cCount;
    private int dCount;
    private int eCount;
    private int fCount;

    public GradeBook(String name) {
        this.courseName = name;
    }

    public void setCourseName(String name) {
        this.courseName = name;
    }

    public String getCourseName() {
        return courseName;
    }

    public void displayMessage() {
        System.out.printf("Welcome to the grade book for\n%s!\n\n", getCourseName());
    }

    public void inputGrades() {
        Scanner input = new Scanner(System.in);
        int grade;
        System.out.printf("%s\n%s\n  %s\n  %s\n", "Enter the integer grades in the range 0-100.",
                "Type the end-of-file indicator to terminate input:",
                "On UNIX/Linux/Mac OS X type <ctrl> d then press Enter",
                "On Windows type <ctrl> z then press Enter");
        while (input.hasNext()) {
            grade = input.nextInt();
            total += grade;
            ++gradeCounter;
            incrementLetterGradeCounter(grade);
        }
    }

    public void incrementLetterGradeCounter(int grade) {
        switch (grade / 10) {
            case 9:
            case 10:
                ++aCount;
                break;
            case 8:
                ++bCount;
                break;
            case 7:
                ++cCount;
                break;
            case 6:
                ++dCount;
                break;
            default:
                ++fCount;
                break;
        }
    }

    public void displayGradeReport() {
        System.out.println("\nGrade Report");
        if (gradeCounter != 0) {
            double average = (double) total / gradeCounter;
            System.out.printf("Total of the %d grades entered is %d\n", gradeCounter, total);
            System.out.printf("Class average in %.2f\n", average);
            System.out.printf("%s\n%s%d\n%s%d\n%s%d\n%s%d\n%s%d\n", "Number of students who received each grade:",
                    "A: ", aCount,
                    "B: ", bCount,
                    "C: ", cCount,
                    "D: ", dCount,
                    "F: ", fCount);
        } else {
            System.out.println("No grades were entered");
        }
    }
}
