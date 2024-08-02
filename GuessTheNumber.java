import java.util.Scanner;
import java.util.Random;


public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean playAgain;
        do {
            playGame(sc);
            System.out.println("Do you want to play the game again? Enter Yes or No:");
            sc.nextLine(); 
            String st = sc.nextLine();
            playAgain = st.equalsIgnoreCase("Yes");
        } while (playAgain);

        System.out.println("Thank you for playing! ");
        sc.close();
    }

    
    public static void playGame(Scanner sc) {
        Random rm = new Random();
        System.out.println("How many maximum attempts you need to guess the number ");
        int n = sc.nextInt();
        int ansno = rm.nextInt(1, 100);
        int urguess;
        while (n > 0) {
            System.out.println("Gues the number:");
            urguess = sc.nextInt();
            if (urguess > ansno) {
                System.out.println("Wrong input, you have entered a big number.");
            } else if (ansno > urguess) {
                System.out.println("Wrong input, you have entered a smaller number.");
            } else {
                System.out.println("Congratulations! You guessed the number! The number is " + ansno);
                break;
            }
            n--;
            
            if (n > 0) {
                System.out.println("Only " + n + " chances are left.");
            } 
            else {
                System.out.println("Sorry, you have used all your attempts. The number was " + ansno);
            }

        }
    }
}

