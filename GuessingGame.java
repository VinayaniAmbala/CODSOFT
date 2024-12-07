import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    private static final int MAX_ATTEMPTS = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int score = 0;
        boolean playAgain;

        System.out.println("Test Your Luck! Can You Guess the Right Number in Just a Few Tries?");
        
        do {
            int attempts = 0;
            int numberToGuess = random.nextInt(100) + 1; 
            boolean isCorrect = false;

            System.out.println("\nA number between 1 and 100 has been chosen.");
            System.out.println("You have " + MAX_ATTEMPTS + " chances to guess it.");

            while (attempts < MAX_ATTEMPTS && !isCorrect) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == numberToGuess) {
                    System.out.println("Jackpot! Your luck is on fire!");
                    score += (MAX_ATTEMPTS - attempts); 
                    isCorrect = true;
                } else if (userGuess > numberToGuess) {
                    System.out.println("Too high! Try a lower number.");
                } else {
                    System.out.println("Too low! Try a higher number.");
                }

                if (attempts == MAX_ATTEMPTS && !isCorrect) {
                    System.out.println("You’ve run out of attempts! The correct number was " + numberToGuess);
                }
            }

            System.out.println("Your current score: " + score);
            System.out.print("Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("Thanks for playing! Your final score is: " + score);
        scanner.close();
    }
}
