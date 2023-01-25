/* Angela Nguyen
 * ICS4U Final Project
 * Semester 1 Jan 2023
 * This code is the main class for the FoodFrenzy Program
 */
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        int turn = 1;

        File foodFrenzyFile = new File("foodFrenzy.txt");

        Chef red = new Chef();
        Chef blue = new Chef();
        String line = "";

        printTitle();
        printIntroduction();

        // introductions and setting up player personas
        System.out.print("WELCOME TO FOOD FRENZY..!\nWould you like to read the rules?");
        int seeRules = yesOrNo();

        if (seeRules == 1) {
            System.out.println("The Food Frenzy is a two player business simulation game "
                    + "\nWhere Chef players compete against each other to get the higher networth after 20 rolls of the dice."
                    + "\nChefs increase their net worth by hiring employees from third-party companies to temporarly work for them"
                    + "\n--> you can do this by landing on an employee square and by paying their PayRate, you can hire them!"
                    + "\n--> the \"earnings\" in their statistics is how much that employee will make you per board lap"
                    + "\nThe only way you can make your money is employees so be sure to have employees at all times!"
                    + "\nBut BEWARE! For more employees mean when you have to pay all of them, it'll suck at ur wallet ><");
        }

        // if the file exists, ask user if they want to load up the data
        FileReader read = new FileReader(foodFrenzyFile);
        BufferedReader reader = new BufferedReader(read);

        line = reader.readLine();

        if (foodFrenzyFile.exists() && line!= null) {
            System.out.println("\nGame Data For Players \u001B[31m" + line + "\u001B[0m and \u001B[34m"
                    + reader.readLine() + "\u001B[0m available!");

            line = inputString("Would you like to load up the game data? (Y/N)\n");

            // if the user chooses to load game data
            if (line.startsWith("y") || line.startsWith("Y")) {
                
                try {

                    line = reader.readLine();
                    red = readPlayer(line, red);

                    while ((line = reader.readLine()).endsWith("/")) {
                        readEmployee(line, red);
                    }

                    blue = readPlayer(line, blue);
                    while ((line = reader.readLine()).endsWith("/")) {
                        readEmployee(line, blue);
                    }

                    turn = Integer.parseInt(reader.readLine());

                    reader.close();
                    read.close();

                } catch (IOException e) {
                    System.out.println("Problem with the input and output");
                    System.err.println("FileNotFoundException: " + e.getMessage());
                    
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            } else {
                FileWriter write = new FileWriter(foodFrenzyFile);
                BufferedWriter writer = new BufferedWriter(write);
                write.close();
                writer.close();
                red = new Chef(inputString("\nEnter Player 1 Red Chef's Name : "), "\u001B[31m");
                blue = new Chef(inputString("\nEnter Player 2 Blue Chef's Name : "), "\u001B[34m");
            }

        } else { // in the case they dont want to load the data
            red = new Chef(inputString("\nEnter Player 1 Red Chef's Name : "), "\u001B[31m");
            blue = new Chef(inputString("\nEnter Player 2 Blue Chef's Name : "), "\u001B[34m");
            System.out.println();
        }

        System.out.println("[ Every player starts out with 2000 dollars ]");
        // getting the board set up with players on it
        FoodFrenzy board = new FoodFrenzy(red, blue);

        // introductions
        System.out.println("\n"+"\u001b[35m"+"---KEY---"
                + "\nE - Employee Card"
                + "\n? - Chance Card\u001b[0m"
                + "\nThis is what the board looks like! The path starts at 1 ends at 25."
                + "\nYour position will be marked by your colours. After 20 rolls, \nchef with the most networth WINS! MAY THE BEST BUSINESS MAN WIN!!");
        board.printBoard(red, blue);

        // entire game loop
        while (turn < 20) {
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

            System.out.println("The game is on turn #" + turn + " and there are " + (20 - turn) + " turns left.");

            // player turn loop
            while (choice != -1) {
                gameFiller("continue");

                // printing the menu
                System.out.println("Chef " + current.getColour() + currentName + "\u001B[0m's turn");
                choice = menuChoice(turnCompletion);

                if (choice == -1) {
                    turn++;
                } else if (choice == 1) {
                    turnCompletion = true;
                    // randomize a roll
                    // int roll = rand.nextInt(6) + 1;
                    int roll = rollDice();

                    if (current.getJail() == true) {

                        if (roll % 2 == 0) {// even roll
                            System.out.println("YOU ROLLED AN EVEN NUMBER! YOU ARE FREE FROM JAIL!!!");
                            current.setPosition(1);
                            current.setJail(false);
                        } else {
                            System.out.println(
                                    "You need to roll an even number to free yourself...better luck next time");
                        }

                    } else {
                        changePlayerPosition(current, roll);
                    }

                    // print out the board
                    board.printBoard(red, blue);

                    // get the board square and determine
                    BoardSquare square = board.getSquare(current);
                    String type = square.getType();
                    EmployeeList currentEmployeeList = current.getList();
                    int option;

                    // what to return if its an employee square
                    if (type.equalsIgnoreCase("Employee")) {
                        employeeCard(current, square, currentEmployeeList);

                        // in the case that it is a chance card
                    } else if (type.equalsIgnoreCase("Chance")) {
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
                            chanceCard(current, option, currentEmployeeList);
                        }
                    } else {
                        if (current.getPosition() == 1) {
                            System.out.println("You are on the go square !");
                        }
                    }

                    gameFiller("view chef status");
                    System.out.print(current + "\n");

                } else if (choice == 2) {
                    board.printBoard(red, blue);

                } else if (choice == 3) { // view Red Chef Stats
                    System.out.println(red);

                } else if (choice == 4) { // view Blue Chef Stats
                    System.out.println(blue);

                } else if (choice == 5) { // Employees Lists
                    System.out.println(current.getList());

                } else if (choice == 6) {

                    (current.getList()).sortList();
                    System.out.println(
                            "Your list is now sorted by the employee with the most earnings to least earnings\n"
                                    + "──────────────────\n" + current.getList());

                } else if (choice == 7) { // Save Data and Leave

                    FileWriter write = new FileWriter(foodFrenzyFile);
                    BufferedWriter writer = new BufferedWriter(write);

                    // save player names to display again later
                    writer.write(red.getName());
                    writer.newLine();
                    writer.write(blue.getName());
                    writer.newLine();

                    // save the chef object
                    // check if the file exists to load things into
                    savePlayer(foodFrenzyFile, red, write, writer);
                    savePlayer(foodFrenzyFile, blue, write, writer);
                    writer.newLine();
                    writer.write("" + turn);

                    writer.close();
                    write.close();

                    System.out.println("\nsaving successfuly");
                    choice = -1;
                    turn = 23;

                } else if (choice == 8) { // Wipe Data and Leave
                    turn = 23;
                    choice = -1;
                }
            }
        }

        // in the case the game is over and we look for a winner
        if (turn > 20 && turn < 22) {

            Chef winner;
            Chef loser;

            if (red.getNetWorth() > blue.getNetWorth()) {
                winner = red;
                loser = blue;
            } else {
                winner = blue;
                loser = red;
            }

            System.out
                    .println("THE WINNER IS " + (winner.getName()).toUpperCase() + " WITH " + (winner.getBalance() - loser.getBalance())
                            + "$ MORE THAN " + (loser.getName()).toUpperCase() + "\nHERE IS YOUR TROPHY!! CONGRATULATIONS!");

            System.out.println("     ___________"
                    + "\n    '._==_==_=_.'"
                    + "\n    .-\\:      /-."
                    + "\n   | (|:.     |) |"
                    + "\n    '-|:. " + (winner.getName()).substring(0, 1) + "   |-'"
                    + "\n      \\::.    /"
                    + "\n       '::. .'"
                    + "\n         ) ("
                    + "\n       _.' '._"
                    + "\n      `\"\"\"\"\"\"\"`");

        }


        System.out.println("...\ngame over\n...");
        gameFiller("say goodbye");
        System.out.println("\ngoodbye see you soon player\n");
        gameFiller("let game go to sleep");
        System.out.println("...(_ _ \")zzz\n...game going to sleep\n...\nzzzzzzz");
    }

    public static void printTitle(){
        System.out.println("\n         _______  _______  _______  ______     _______  ______    _______  __    _  _______  __   __ "
        +"\n        |       ||       ||       ||      |   |       ||    _ |  |       ||  |  | ||       ||  | |  |"
        +"\n        |    ___||   _   ||   _   ||  _    |  |    ___||   | ||  |    ___||   |_| ||____   ||  |_|  |"
        +"\n        |   |___ |  | |  ||  | |  || | |   |  |   |___ |   |_||_ |   |___ |       | ____|  ||       |"
        +"\n        |    ___||  |_|  ||  |_|  || |_|   |  |    ___||    __  ||    ___||  _    || ______||_     _|"
        +"\n        |   |    |       ||       ||       |  |   |    |   |  | ||   |___ | | |   || |_____   |   |  "
        +"\n        |___|    |_______||_______||______|   |___|    |___|  |_||_______||_|  |__||_______|  |___|\n\n");
        gameFiller(" start!");
    }


    /*
     * Just print outs an introduction
     */
    public static void printIntroduction() {
        Scanner in = new Scanner(System.in);

        System.out.println("YOUR A CHEF!"
                + "\n    .--,--."
                + "\n    `.  ,.'"
                + "\n     |___|"
                + "\n     :o o:"
                + "\n    _`~^~'"
                + "\n  /'   ^   `\\");
        gameFiller("continue when you see the three dots");

        System.out.println("who wants to make a restaurant..."
                + "\n        ("
                + "\n            )"
                + "\n       __..---..__"
                + "\n   ,-='  /  |  \\  `=-."
                + "\n  :--..___________..--;"
                + "\n   \\.,_____________,./");
        in.nextLine();

        System.out.println("you decided that with the help of third party, growing food-tech companies..."
        +"\n     ___"
+"\n    |[_]|"
+"\n    |+ ;|"
+"\n    `---'");
        in.nextLine();

        System.out.println("hiring their employees to work for you as well could make you alot of money!!. . ."
        + "\n\n       \\`\\/\\/\\/`/"
        +"\n        )======("
        +"\n      .'        '."
        +"\n     /    _||__   \\"
        +"\n    /    (_||_     \\"
        +"\n   |     __||_)     |"
        +"\n   |       ||       |"
        +"\n   '.              .'"
        +"\n     '------------'"   );

     
        in.nextLine();

        System.out.println("and with that ..");
        in.nextLine();

    }

    //reads from a String line and stores it in a player
    public static Chef readPlayer(String line, Chef player) {
        String[] chefStats = line.split(",");
        int position = Integer.parseInt(chefStats[1]);
        double balance = Double.parseDouble(chefStats[2]);
        int lapCount = Integer.parseInt(chefStats[3]);
        double netWorth = Double.parseDouble(chefStats[4]);
        boolean debt = Boolean.parseBoolean(chefStats[5]);
        boolean jail = Boolean.parseBoolean(chefStats[6]);

        player = new Chef(chefStats[0], position, balance, lapCount, netWorth, debt, jail, chefStats[7]);
        return player;
    }

    //reads from a String line and stores it in an Employee
    public static void readEmployee(String line, Chef player) {
        String[] EmployeeStats = line.split(",");

        //parses everything into the objects
        int position = Integer.parseInt(EmployeeStats[0]);
        int payRate = Integer.parseInt(EmployeeStats[4]);
        int earnings = Integer.parseInt(EmployeeStats[5]);
        EmployeeStats[6] = EmployeeStats[6].substring(0, EmployeeStats[6].length() - 1);
        boolean hired = Boolean.parseBoolean(EmployeeStats[6]);
        EmployeeCard employee = new EmployeeCard(position, EmployeeStats[1], EmployeeStats[2], EmployeeStats[3], payRate,
                earnings, hired);
        (player.getList()).hire(employee);
    }

    //saves the player data including the linked list
    public static void savePlayer(File file, Chef player, FileWriter write, BufferedWriter writer) {

        // making the file because if it does not exist yet
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error : " + e.getMessage());
            }
        }

        try {

            // get the toWrite variable stream for the Chef player
            writer.write(player.toWrite());
            writer.newLine();

            EmployeeList list = player.getList();
            EmployeeCard current = list.getHead();

            // writes the variables into the file
            while (current != null) {
                writer.write(current.toWrite());
                writer.newLine();
                current = current.getNext();
            }

        } catch (IOException e) {
            System.err.println("Error is : " + e.getMessage());
        }

    }

    //rolls and prints out the dice. also returns the number
    public static int rollDice() {

        Random rand = new Random();

        int roll = rand.nextInt(6) + 1;

        System.out.println(" _______");

        if (roll == 1) {
            System.out.println("|       |"
                    + "\n|   .   |"
                    + "\n|       |");
        } else if (roll == 2) {
            System.out.println("|       |"
                    + "\n|   .   |"
                    + "\n|   .   |");
        } else if (roll == 3) {
            System.out.println("|   .   |"
                    + "\n|   .   |"
                    + "\n|   .   |");
        } else if (roll == 4) {
            System.out.println("| .   . |"
                    + "\n|       |"
                    + "\n| .   . |");
        } else if (roll == 5) {
            System.out.println("| .   . |"
                    + "\n|   .   |"
                    + "\n| .   . |");
        } else if (roll == 6) {
            System.out.println("| .   . |"
                    + "\n| .   . |"
                    + "\n| .   . |");
        }

        System.out.println("|_______|\n\nYou rolled a " + roll + "!");

        return roll;
    }

    //intakes number input
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

    //intakes a String and returns it
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
                "Sort Employees By Earnings",
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

        int choice = 0;
        do {

            try {
                System.out.print("Choice : ");
                choice = in.nextInt();

                if (choice > 8 || choice < 1) {
                    choice = 0;
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input please try again ...");
                choice = 0;
                in.nextLine();
            }

        } while (choice == 0);

        // in the case that they had completed their turn
        // the option "1" that they pick is actually option -1
        // which is to let the next player begin their turn
        if (turnCompletion == true && choice == 1) {
            choice = -1;
        }
        return choice;
    }

    //changes player positions and keeps track of the lap count
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

    //calculates and pays Employees
    public static void payEmployees(Chef player, EmployeeList list) {
        player.setBalance((player.getBalance()) - list.calculatePayRoll());
        System.out.println("$" + list.calculatePayRoll() + " was taken out of \nChef " + player.getName() + "'s balance!");
    }

    //pays the Player using the method from the object
    public static void payPlayer(Chef player, EmployeeList list) {
        player.setBalance(player.getBalance() + list.calculateEarnings());
        System.out.println("$"+list.calculateEarnings() + " was added to Chef " + player.getName() + "'s balance!");
        player.addToNetWorth(list.calculateEarnings());
    }

    // chance card that occurs only if the player has less than 1 employees
    public static void chanceCard(Chef player, int option, EmployeeList list) {
        Random rand = new Random();
        option = rand.nextInt(11)+1;

        System.out.println("───────────────────────────────────");

        if (option == 1) {
            System.out.println("You commited tax fraud! Go To JAIL!\nYou must roll an even number to get out!");
            player.setPosition(25);
            player.setJail(true);
        } else if (option == 2) {
            System.out.println("You have to fire your oldest \nemployee :(");
            try {
                list.fire();
                player.setList(list);
            } catch (Exception e) {
            }
        } else if (option == 4 || option == 5 || option == 6 || option == 7 ) {
            System.out.println("It's time to pay your employees!");

            // set the balance as the iniitial balance minus the payroll you have to pay all
            // the employees
            payEmployees(player, list);

        } else if (option==8 || option == 9 || option == 3) {

            payPlayer(player, list);
            System.out.println("EXTRA PAY DAY! YOU TOOK YOUR EMPLOYEES EARNINGS EARLY TODAY!!");

        } else {

            // check if the player is in debt
            double tempBalance;
            if (player.checkDebt() == true) {
                tempBalance = 0 - player.getBalance();
                System.out.print("EVEN IF YOU ARE IN DEBT,\n");
            } else {
                tempBalance = player.getBalance();
            }

            int donation = rand.nextInt((int)tempBalance / 2) + 1;
            player.setBalance(player.getBalance() - donation);
            System.out.println("YOU DECIDED TO DONATE TO SICK KIDS \nWOWWW! \nTHANK YOU FOR YOUR DONATION OF $" + donation
                    + "\n!Thank you for your kindness <3");
        }
        System.out.println("───────────────────────────────────");

    }

    //Views the employee card and runs the actions for if they landed on an Employee square
    public static void employeeCard(Chef current, BoardSquare square, EmployeeList currentEmployeeList) {

        Scanner in = new Scanner(System.in);
        int option;

        System.out.println("You have landed on an EMPLOYEE SQUARE!"
                + "\nHere are the stats of the employee you have landed on!");
        gameFiller("view");

        System.out.println("----------------\n" + square + "\n----------------");

        // check if employee is already hired or not
        if (((EmployeeCard) square).getHired() == true) {

            if (currentEmployeeList.search(currentEmployeeList.getHead(), ((EmployeeCard) square).getName())) {
                System.out.println("Looks like this employee is already on your team! Hehe");
            } else {
                System.out.println(
                        "Looks like this employee is already hired! It's okay surely you can hire another one..");
            }

            // check if employee's pay rate is not in the chef's balance range
        } else if ((((EmployeeCard) square).getPayRate()) > current.getBalance()) {
            System.out.println(
                    "Looks like this employee is availabe for hire but you do not have the funds to hire this employee");

            // if able to, allow chef player the option to hire
        } else {
            System.out.print("Looks like this employee is available for you to hire! Would you like to hire them?"
                    + "\nYou pay this employee $" + ((EmployeeCard) square).getPayRate() + " but they make you $"
                    + ((EmployeeCard) square).getEarnings());
            option = yesOrNo();

            // change the hire status for that employee and add them to the Employee list of
            // employees
            if (option == 1) {

                // change the state of hire
                ((EmployeeCard) square).setHire(true);

                // charge the player for hiring
                current.setBalance(current.getBalance() - (((EmployeeCard) square).getPayRate()));

                // add the employee to the linked list
                currentEmployeeList.hire((EmployeeCard) square);
                current.setList(currentEmployeeList);

                System.out.println("Player hired! Welcome " + ((EmployeeCard) square).getName() + " to your team as a "
                        + ((EmployeeCard) square).getJob() + "!");

            } else {
                System.out.println("What a passed opportunity..");
            }
        }
    }

    //A game filler to avoid long chunky blocks of text
    public static void gameFiller(String action) {
        Scanner in = new Scanner(System.in);
        System.out.println("\n[ Hit ENTER to " + action + " ]");
        in.nextLine();
    }

}
