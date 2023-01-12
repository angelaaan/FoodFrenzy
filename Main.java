import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean turn = true;
        int choice = 0;

        //introductions and setting up player personas
        System.out.print("Welcome! You start with 1000 dollars\n\nEnter Player 1 Red Chef's Name : ");
        Chef red = new Chef(in.nextLine());

        System.out.print("Enter Player 2 Blue Chef's Name : ");
        Chef blue = new Chef(in.nextLine());

        //getting the board set up with players on it
        FoodFrenzy board = new FoodFrenzy(red, blue);

        //introductions
        System.out.println("\n\nWELCOME TO FOUR*10 FOOD FRENZY"
            + "\nE - Employee"
            + "\n? - Chance Card"
            + "\nThis is what the board looks like! The path starts at 1 ends at 25."
            + "\nYour position will be marked by your colours. After 40 rolls, chef with the most"
            + "\nnetworth WINS! MAY THE BEST BUSINESS MAN WIN!!");
        board.printBoard();

        //game loop
        while (choice==0){
            choice = menuChoice();

            //if statement for user choice and decision making  
            if (choice==1){
                
            }

        }
        
    }

    //takes in user decision
    public static int menuChoice() {
        Scanner in = new Scanner(System.in);
        int choice = 0;

        System.out.print("------------------" + "\u001B[33m"
                    + "\n[ M A I N   M E N U ]"
                    + "\n[1] - Roll Dice"
                    + "\n[2] - View Board"
                    + "\n[3] - Red Chef Stats"
                    + "\n[4] - Blue Chef Stats"
                    + "\n[5] - Employees List"
                    + "\n[6] - Save Data and Leave"
                    + "\n[7] - Wipe Data and Leave"+ "\u001B[0m"
                    + "\n\nOption Choice : ");
        while (choice==0){
            choice = in.nextInt();

            //check to make sure it's valid
            if (choice >7 || choice < 1){
                choice = 0;
                System.out.println("Invalid Input..thats not an option please try again :(");
            }
        }
        
        return choice;
    }

}
