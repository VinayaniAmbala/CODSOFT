import java.util.Scanner;

public class StudentMarks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        
        System.out.println("STUDENT GRADE CALCULATOR\n");
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        
        int[] marks = new int[numberOfSubjects];
        int totalMarks = 0;

        
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();
            totalMarks += marks[i];
        }

        
        double averagePercentage = (double) totalMarks / numberOfSubjects;

        
        String grade;
        if (averagePercentage >= 90) {
            grade = "A";
        } else if (averagePercentage >= 80) {
            grade = "B";
        } else if (averagePercentage >= 70) {
            grade = "C";
        } else if (averagePercentage >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        // Display the results
        System.out.println("\nRESULTS:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Percentage: " + averagePercentage + "%");
        System.out.println("Grade you earned: " + grade);

        // Close the scanner
        scanner.close();
    }
}
