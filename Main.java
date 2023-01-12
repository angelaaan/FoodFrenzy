import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Welcome! You start with 1000 dollars\n\nEnter Player 1 Red Chef's Name : ");
        Chef red = new Chef(in.nextLine());

        System.out.print("Enter Player 2 Blue Chef's Name : ");
        Chef blue = new Chef(in.nextLine());

        FoodFrenzy board = new FoodFrenzy(red, blue);

        System.out.println("\n\nWELCOME TO FOUR*10 FOOD FRENZY"
            + "\nE - Employee"
            + "\n? - Chance Card");

        board.printBoard();
        
    }

    //method to print out board everytime
    public static void name(Chef P1, Chef P2) {
        
    }
}
