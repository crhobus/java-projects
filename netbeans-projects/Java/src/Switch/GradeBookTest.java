package Switch;

public class GradeBookTest {

    public static void main(String[] args) {
        GradeBook myGradeBook = new GradeBook("CS101 Introduction to Java Programing");
        myGradeBook.displayMessage();
        myGradeBook.inputGrades();
        myGradeBook.displayGradeReport();
    }
}
