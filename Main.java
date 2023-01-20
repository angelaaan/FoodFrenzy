import java.util.*;

import javax.sql.rowset.spi.SyncResolver;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        int turn = 1;

        // introductions and setting up player personas
        System.out.print("Welcome Stranger..!\nWould you like to read the rules?");
        int seeRules = yesOrNo();
        if (seeRules == 1) {
            System.out.println("The Four*10 Food Frenzy is a two player restaurant simulation game "
                    + "\nwhere Chef players compete against each other to get the most money after 40 rolls of the dice."
                    + "\nChefs earn money by hiring employees from third-party companies to temporarly work for them"
                    + "\n--> you can do this by landing on an employee square and by paying their PayRate, you can hire them!"
                    + "\n--> The \"earnings\" in their statistics is how much that employee will make you per board lap"
                    + "\nThe only way you can make your money is employees so be sure to have employees at all times!");
        }

        Chef red = new Chef(inputString("\nEnter Player 1 Red Chef's Name : "), "\u001B[31m");

        Chef blue = new Chef(inputString("\nEnter Player 2 Blue Chef's Name : "), "\u001B[34m");

        // getting the board set up with players on it
        FoodFrenzy board = new FoodFrenzy(red, blue);

        // introductions
        System.out.println("\n\nWELCOME TO FOUR*10 FOOD FRENZY"
                + "\nE - Employee"
                + "\n? - Chance Card"
                + "\nThis is what the board looks like! The path starts at 1 ends at 25."
                + "\nYour position will be marked by your colours. After 40 rolls, chef with the most networth WINS! MAY THE BEST BUSINESS MAN WIN!!");
        board.printBoard(red, blue);

        // entire game loop
        while (turn < 21) {
            int choice = 0;
            boolean turnCompletion = false;

            String currentName = "";
            Chef current;
            if (turn % 2 == 1) { // if turn number is odd, it is player 1's turn
                current = red;
                currentName = red.getName();
            } else {
                current = blue;
                currentName = blue.getName();
            }

            // player turn loop
            while (choice != -1) {
                gameFiller("continue");

                // printing the menu
                System.out.println("Chef " + current.getColour()+ currentName + "\u001B[0m's turn");
                choice = menuChoice(turnCompletion);

                if (choice == -1) {
                    turn++;
                } else if (choice == 1) {
                    turnCompletion = true;
                    // randomize a roll
                    // int roll = rand.nextInt(6) + 1;
                    int roll = rollDice();

                    // print out the board
                    changePlayerPosition(current, roll);
                    board.printBoard(red, blue);

                    // get the board square and determine
                    BoardSquare square = board.getSquare(current);
                    String type = square.getType();
                    EmployeeList currentEmployeeList = current.getList();
                    int option;

                    // what to return if its an employee square
                    if (type.equalsIgnoreCase("Employee")) {
                        employeeCard(current, square, currentEmployeeList);

                    } else { // in the case that it is a chance card
                        System.out.println("You've landed on a chance card!");
                        gameFiller("view Chance card");

                        option = rand.nextInt(10) + 1;

                        // if the player doesnt have many employees, move them back a square
                        // this prompts the users to get more employees to really get the game going
                        if (currentEmployeeList.size() < 1) {
                            System.out.println("────────────────────────────"
                                    + "\nMove " + 1 + " squares back."
                                    + "\n────────────────────────────");

                            gameFiller("see the board now that you've moved");
                            current.setPosition(current.getPosition() - 1);
                            board.printBoard(red, blue);

                            gameFiller("see the square you landed on!");

                            if ((board.getSquare(current).getType()).equalsIgnoreCase("Employee")) {
                                employeeCard(current, board.getSquare(current), currentEmployeeList);
                            } else {
                                System.out.println(
                                        "You landed on a chance card...\nHere is 5 dollars you found on the floor");
                                current.setBalance(current.getBalance() + 5);
                                System.out.println("Your new balance is " + current.getBalance());
                            }
                        } else {
                            option = 5;
                            chanceCard(current, option, currentEmployeeList);
                        }
                    }
                    System.out.print("Here are your chef stats\n"+current);
                    
                } else if (choice == 2) {
                    board.printBoard(red, blue);
                } else if (choice == 3) { // view Red Chef Stats
                    System.out.println(red);
                } else if (choice == 4) { // view Blue Chef Stats
                    System.out.println(blue);
                } else if (choice == 5) { // Employees Lists
                    System.out.println(current.getList());
                }
            }
        }

    }

    public static int rollDice(){

        Random rand = new Random();

        int roll = rand.nextInt(6)+1;

        System.out.println(" _______");

        if (roll==1){
            System.out.println("|       |"
            +"\n|   .   |"
            + "\n|       |");
        } else if (roll==2){
            System.out.println("|       |"
            + "\n|   .   |"
            + "\n|   .   |");
        } else if (roll==3){
            System.out.println("|   .   |"
            +"\n|   .   |"
            +"\n|   .   |");
        } else if (roll==4){
            System.out.println("| .   . |"
            +"\n|       |"
            +"\n| .   . |");
        } else if (roll == 5){
            System.out.println("| .   . |"
            +"\n|   .   |"
            +"\n| .   . |");
        } else if (roll == 6){
            System.out.println("| .   . |"
            +"\n| .   . |"
            +"\n| .   . |");
        }
        
        System.out.println("|_______|\n\nYou rolled a "+roll+"!");

        return roll;
    }

    public static int yesOrNo() {
        Scanner in = new Scanner(System.in);
        boolean error = false;
        int choice = 0;

        do {
            error = false;
            try {
                System.out.println("\n[1] - yes\n[2] - no\nCHOICE: ");
                choice = in.nextInt();

                if (choice > 2 || choice < 1) {
                    throw new InputMismatchException();
                }
            } catch (Exception e) {
                System.out.println("Please input a valid answer");
                error = true;
                in.nextLine();
            }

        } while (error);

        return choice;

    }

    public static String inputString(String prompt) {
        Scanner in = new Scanner(System.in);
        boolean error = false;
        String info = "";

        do {
            error = false;
            try {
                System.out.print(prompt);
                info = in.nextLine();

                if (info.equalsIgnoreCase("")) {
                    throw new Exception();
                }

            } catch (Exception e) {
                System.out.println("uh oh.. looks like you entered an enter valid answer\n[Hit ENTER to try again]");
                error = true;
                in.nextLine();
            }

        } while (error);

        return info;
    }

    // takes in user decision
    public static int menuChoice(boolean turnCompletion) {
        Scanner in = new Scanner(System.in);
        String[] MainMenu = { "View Board", "Red Chef Stats", "Blue Chef Stats", "Employees List",
                "Save Data and Leave", "Wipe Data and Leave" };

        System.out.print("\u001B[33m" + "------------------"
                + "\n[ M A I N   M E N U ]"
                + "\n[1] - ");

        // prints the menu
        for (int i = -1; i < MainMenu.length; i++) {
            if (turnCompletion == false && i == -1) { // allow player to roll the dice because they have not yet
                System.out.print("Roll Dice");
            } else if (turnCompletion == true && i == -1) {// in the case that they are already done their turn, display
                                                           // the option to finish their turn instead
                System.out.print("Finish Turn");
            } else {
                System.out.print("\n[" + (i + 2) + "] - " + MainMenu[i]);
            }
        }

        System.out.println("\n------------------\u001B[0m");

        int choice=0;
        do {

            try{
                System.out.print("Choice : ");
                choice = in.nextInt();

                if (choice > 7 || choice < 1) {
                    choice = 0;
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e){
                System.out.println("Invalid input please try again ...");
                choice = 0;
                in.nextLine();
            }

        } while (choice ==0);
        

        // in the case that they had completed their turn
        // the option "1" that they pick is actually option -1
        // which is to let the next player begin their turn
        if (turnCompletion == true && choice == 1) {
            choice = -1;
        }
        return choice;
    }

    public static void changePlayerPosition(Chef player, int num) {
        int newPosition = player.getPosition() + num;

        if (newPosition > 24) { // if they finish a lap
            // make sure they have the lap counted
            player.lapCompleted();

            // set their position to the correct one
            newPosition -= 24;

            // pay the player
            payPlayer(player, player.getList());
        }

        player.setPosition(newPosition);
    }

    public static void payEmployees(Chef player, EmployeeList list) {
        player.setBalance((player.getBalance()) - list.calculatePayRoll());
    }

    public static void payPlayer(Chef player, EmployeeList list) {
        player.setBalance(player.getBalance() + list.calculateEarnings());
    }

    // chance card that occurs only if the player has less than 1 employees
    public static void chanceCard(Chef player, int option, EmployeeList list) {
        Random rand = new Random();

        System.out.println("───────────────────────────────");

        if (option == 1) {
            System.out.println("You commited tax fraud! Go To JAIL! You must roll an even number to get out!");
            player.setPosition(25);
        } else if (option == 2 || option == 3) {
            System.out.println("You have to fire your oldest employee :(");
            try {
                list.fire();
                player.setList(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (option == 4 || option == 5 || option == 6 || option == 7 || option == 8) {
            System.out.println("It's time to pay your employees!");

            // set the balance as the iniitial balance minus the payroll you have to pay all
            // the employees
            payEmployees(player, list);

        } else if (option == 9) {

            payPlayer(player, list);
            System.out.println("EXTRA PAY DAY! YOU TOOK YOUR EMPLOYEES EARNINGS TODAY!!");

        } else {

            // check if the player is in debt
            double tempBalance;
            if (player.checkDebt() == true) {
                tempBalance = 0 + player.getBalance();
                System.out.print("EVEN IF YOU ARE IN DEBT, ");
            } else {
                tempBalance = player.getBalance();
            }

            double donation = rand.nextDouble(tempBalance / 2) + 1;
            player.setBalance(player.getBalance() - donation);
            System.out.println("YOU DECIDED TO DONATE TO SICK KIDS WOWWW! THANK YOU FOR YOUR DONATION OF $" + donation
                    + "!Thank you for your kindness <3");
        }
        System.out.println("───────────────────────────────");

    }

    public static void employeeCard(Chef current, BoardSquare square, EmployeeList currentEmployeeList) {

        Scanner in = new Scanner(System.in);
        int option;

        System.out.println("You have landed on an EMPLOYEE SQUARE!"
                + "\nHere are the stats of the employee you have landed on!");
        gameFiller("view");

        System.out.println("----------------\n" + square + "\n----------------");

        // check if employee is already hired or not
        if (((Employee) square).getHired() == true) {
            System.out
                    .println("Looks like this employee is already hired! It's okay surely you can hire another one..");

            // check if employee's pay rate is not in the chef's balance range
        } else if ((((Employee) square).getPayRate()) > current.getBalance()) {
            System.out.println(
                    "Looks like this employee is availabe for hire but you do not have the funds to hire this employee");

            // if able to, allow chef player the option to hire
        } else {
            System.out.print("Looks like this employee is available for you to hire! Would you like to hire them?"
                + "\nYou pay this employee "+ ((Employee) square).getPayRate()+ " but they make you "+ ((Employee)square).getEarnings());
            option = yesOrNo();

            // change the hire status for that employee and add them to the Employee list of
            // employees
            if (option == 1) {

                // change the state of hire
                ((Employee) square).setHire(true);

                // charge the player for hiring
                current.setBalance(current.getBalance() - (((Employee) square).getPayRate()));

                // add the employee to the linked list
                currentEmployeeList.hire((Employee) square);
                current.setList(currentEmployeeList);

                System.out.println("Player hired! Welcome " + ((Employee) square).getName() + " to your team as a "
                + ((Employee) square).getJob() + "!");
                
            } else {
                System.out.println("What a passed opportunity..");
            }
        }
    }

    public static void gameFiller(String action) {
        Scanner in = new Scanner(System.in);
        System.out.println("\n[ Hit ENTER to " + action + " ]");
        in.nextLine();
    }

}
