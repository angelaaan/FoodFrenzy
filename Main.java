import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        int turn = 1;
        
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
            + "\nYour position will be marked by your colours. After 40 rolls, chef with the most networth WINS! MAY THE BEST BUSINESS MAN WIN!!");
        board.printBoard(red,blue);

        //game loop
        while (turn<21){
            int choice = 0;
            boolean turnCompletion = false;
            
            String currentName = "";
            Chef current;
            if (turn%2==1){ //if turn number is odd, it is player 1's turn
                current = red;
                currentName = red.getName();
            } else {
                current = blue;
                currentName = blue.getName();
            }

            while (choice!=6){
            
            System.out.println("[ press ENTER to continue ]");
            in.nextLine();

            //printing the menu
            System.out.println("Chef "+currentName+"'s turn");
            choice = menuChoice(turnCompletion);

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
                turnCompletion = true;

            //set the position for the current chef playing
            current.setPosition(current.getPosition()+roll);

            //print the board out
            System.out.println(current.getName()+"'s' position is now "+current.getPosition());
            board.printBoard(red, blue);

            //get the board square and determine 
            BoardSquare square = board.getSquare(current);
            String type = square.getType();
            EmployeeList currentEmployeeList = current.getList();
            int option;
            
        //what to return if its an employee square
        if (type.equalsIgnoreCase("Employee")){
            employeeCard(current, square, currentEmployeeList);
            
        } else { //in the case that it is a chance card
            option = rand.nextInt(10)+1; 

            if (currentEmployeeList.size()<2){
                System.out.println("Move "+1+" squares back.");
                current.setPosition(current.getPosition()-1);
                board.printBoard(red, blue);

                if ((board.getSquare(current).getType()).equalsIgnoreCase("Employee")){
                    employeeCard(current, board.getSquare(current), currentEmployeeList);
                }
            } else {
                chanceCard1(current, option, currentEmployeeList);
            }


        }
            
            }
            
            else if (choice ==2){
                board.printBoard(red, blue);
            } else if (choice == 3){ //view Red Chef Stats
                System.out.println(red);
            } else if (choice ==4){ //view Blue Chef Stats
                System.out.println(blue);
            } else if (choice == 5){ //Employees Lists
                System.out.println(current.getList());
            } else if (choice == 6){ //Finish Turn
                turn ++;
            }
        }
        }
        
    }

    //takes in user decision
    public static int menuChoice(boolean turnCompletion) {
        Scanner in = new Scanner(System.in);
        String[] MainMenu = {"Roll Dice", "View Board", "Red Chef Stats", "Blue Chef Stats", "Employees List", "Finish Turn", "Save Data and Leave", "Wipe Data and Leave"};

        System.out.print("\u001B[33m"+"------------------"
        + "\n[ M A I N   M E N U ]");

        int num=0;
        if (turnCompletion==true){
            num=1;
        }
        int j = 1;
        for (int i = num;i<MainMenu.length ; i++){
            System.out.print("\n["+j+"] - "+MainMenu[i]);
            j++;
        }
        System.out.print("\n------------------\u001B[0m"
        + "\n\nOption Choice : ");

        int choice = 0;
        while (choice==0){
            choice = in.nextInt();
            
            if(num!=0){
                choice++;
            }

            //check to make sure it's valid
            if (choice >7 || choice < 1){
                choice = 0;
                System.out.println("Invalid Input..thats not an option please try again :(");
            }
        }
        
        return choice;
    }

    public static void chanceCard1(Chef current, int option, EmployeeList list){
        Random rand = new Random();

        if (option == 1){
            System.out.println("You commited tax fraud! Go To JAIL! You must roll an even number to get out!");
            current.setPosition(25);
    } else if (option ==2 || option== 3){
            System.out.println("You have to fire your oldest employee :(");
        //check if their employee list is empty
        if ((current.getList()).isEmpty()){
            System.out.println("EXCEPT YOU DONT HAVE TO BECAUSE YOU DONT HAVE EMPLOYEES TO FIRE ANYWAYS!");
        } else {
        //try catch statement to make a linkedlist, dequeue an employee, and then reset that linked list for the player
        try {
            list.fire();
            current.setList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    } else if (option ==4 || option == 5 || option ==6 || option ==7 || option ==8 || option ==9){
        System.out.println("It's time to pay your employees!");
        //set the balance as the iniitial balance minus the payroll you have to pay all the employees
        current.setBalance((current.getBalance())-list.calculatePayRoll());

    } else{
        double donation = rand.nextDouble(current.getBalance()/2)+1;
        current.setBalance(current.getBalance()-donation);
        System.out.println("WOWWW YOU DECIDED TO DONATE TO SICK KIDS THANK YOU FOR YOUR DONATION OF $"+donation
        + "\nYour balance is now $"+ current.getBalance()+ "! Thank you for your kindness <3");
    }
    }

    public static void employeeCard(Chef current, BoardSquare square, EmployeeList currentEmployeeList){

        Scanner in = new Scanner (System.in);
        int option;
        
        System.out.println("You have landed on an EMPLOYEE SQUARE!"
        + "\nHere are the stats of the employee you have landed on!");

        System.out.println("--------------\n"+square+"\n--------------");

        //check if employee is already hired or not
        if (((Employee)square).getHired()==true){
            System.out.println("Looks like this employee is already hired! It's okay surely you can hire another one..");

        //check if employee's pay rate is not in the chef's balance range
        } else if ((((Employee) square).getPayRate())>current.getBalance()){
            System.out.println("Looks like you do not have the funds to hire this employee");

        //if able to, allow chef player the option to hire
        } else { 
            System.out.println("Looks like this employee is available for you to hire! Would you like to hire them?"
            + "\n[1] - yes"
            + "\n[2] - no");
            option = in.nextInt();

            //change the hire status for that employee and add them to the Employee list of employees
            if (option == 1){

                //change the state of hire
                ((Employee)square).setHire(true);

                //make an employee list to add the employee into and then set the employee list to that currentEmployeeListorary one
                //this needs to be done because the linked list is inside the Chef player object
                currentEmployeeList.hire((Employee) square);
                current.setList(currentEmployeeList);

                System.out.println("Player hired! Welcome "+ ((Employee)square).getName()+" to your team as a "+((Employee)square).getJob()+"!");
            } else {
                System.out.println("What a passed opportunity..");
            }
        }
    }


    

}
