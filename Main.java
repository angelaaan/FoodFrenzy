import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        int turn = 1;
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
        board.printBoard(red,blue);

        //game loop
        while (choice==0){
            
            String currentName = "";
            Chef current;
            if (turn%2==1){ //if turn number is odd, it is player 1's turn
                current = red;
                currentName = red.getName();
            } else {
                current = blue;
                currentName = blue.getName();
            }

            //printing the menu
            System.out.println("Chef "+currentName+"'s turn");
            choice = menuChoice();

            //if statement for user choice and decision making  
            if (choice==1){

                //randomize a roll
                int roll = rand.nextInt(6)+1;

                System.out.println("      ______"
                +"\n    /O     /\\"
                +"\n   /   O  /O \\"
                +"\n((/_____O/    \\"
                +"\n  \\O    O\\    /"
                +"\n   \\O    O\\ O/ "
                +"\n    \\O____O\\/ ))"
                +"\n  ((\n\nYou rolled a "+roll);

            //set the position for the current chef playing
            current.setPosition(current.getPosition()+roll);

            //print the board out
            System.out.println(current.getName()+"'s' position is now "+current.getPosition());
            board.printBoard(red, blue);

            //get the board square and determine 
            BoardSquare square = board.getSquare(current);
            String type = square.getType();
            EmployeeList temp = current.getList();
            int option;
            
        //what to return if its an employee square
        if (type.equalsIgnoreCase("Employee")){
            System.out.println("You have landed on an EMPLOYEE SQUARE!"
            + "\nHere are the stats of the employee you have landed on!");

            System.out.println("--------------\n"+square);

            //check if Employee is yet hired
            if (((Employee)square).getHired()==true){
                System.out.println("Looks like this employee is already hired! It's okay surely you can hire another one..");
            }
            else { //allow player option to hire
                System.out.println("Looks like this employee is available for hire! Would you like to hire them?"
                + "\n[1] - yes"
                + "\n[2] - no");
                option = in.nextInt();

                //change the hire status for that employee and add them to the Employee list of employees
                if (option == 1){
                    //change the state of hire
                    ((Employee)square).setHire(true);
                    //add the employee to the lists

                    //make an employee list to add the employee into and then set the employee list to that temporary one
                    //this needs to be done because the linked list is inside the Chef player object
                    temp.hire((Employee) square);
                    current.setList(temp);

                    System.out.println("Player hired! Welcome "+ ((Employee)square).getName()+" to your team"
                    + "\nas a "+((Employee)square).getJob()+"!");
                } else {
                    System.out.println("What a passed opportunity..");
                }
            }
            
        } else { //in the case that it is a chance card
            option = rand.nextInt(10)+1; 

            if (option == 1){
                System.out.println("You commited tax fraud! Go To JAIL! You must roll an even number to get out!");
                current.setPosition(25);
            } else if (option ==2 || option== 3){
                System.out.println("You have to fire your oldest employee :(");

                //check if their employee list is empty
                if ((current.getList()).isEmpty()){
                    System.out.println("EXCEPT YOU DONT HAVE TO BECAUSE YOU DONT HAVE EMPLOYEES TO FIRE ANYWAYS!");
                }
                //try catch statement to make a linkedlist, dequeue an employee, and then reset that linked list for the player
                try {
                    temp.fire();
                    current.setList(temp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (option ==4 || option == 5 || option ==6 || option ==7 || option ==8 || option ==9){
                System.out.println("It's time to pay your employees!");
            } else{
                double donation = rand.nextDouble(current.getBalance()/2)+1;
                current.setBalance(current.getBalance()-donation);
                System.out.println("WOWWW YOU DECIDED TO DONATE TO SICK KIDS THANK YOU FOR YOUR DONATION OF $"+donation
                + "\nYour balance is now $"+ current.getBalance()+ "! Thank you for your kindness <3");
            }
        }
            
                
            }

            choice=0;
            turn++;
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
