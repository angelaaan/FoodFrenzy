import java.util.*;
import java.io.*;
public class FoodFrenzy {
    private int position1r;
    private int position1c;
    private int position2r;
    private int position2c;
    private String[][] board = {
        {"-GO!-"," -E- "," -E- "," -E- "," -?- "," -E- "," -?- "}, //ROW 0
        {" -E- ","     ","     ","     ","     ","     "," -E- "}, //ROW 1
        {" -E- ","     ","     ","     ","     ","     "," -E- "}, //ROW 2
        {" -E- ","     ","     ","JAIL!","     ","     "," -?- "}, //ROW 3
        {" -?- ","     ","     ","     ","     ","     "," -E- "}, //ROW 4
        {" -E- ","     ","     ","     ","     ","     "," -E- "}, //ROW 5
        {" -?- "," -E- "," -E- "," -?- "," -E- "," -E- "," -?- "}  //ROW 6
    };
    BoardSquare[] playBoard = new BoardSquare[24];

    //move this method down later
    public void readFileToFill(){

        String file = "EmployeeBoard.txt";
        String line;
        String[] data = new String[6];

        //employee(boardPosition, JobName, EmployeeName, Brand, Payrate, Salary)
        try{

            FileReader stream = new FileReader (file);
            BufferedReader read = new BufferedReader (stream);
            int i = 0;

            while((line=read.readLine()) !=null){
                data = line.split(",");

                if (i==0 || i==4 || i==6 || i==9 || i==12 || i==15 || i==18 || i==20){
                    System.out.println("i is "+i+" and i just made a chancee card!");
                    playBoard[i] = new ChanceCard();
                    i++;
                }
                
                playBoard[i] = new Employee(Integer.parseInt(data[0]),data[1], data[2], data[3],Integer.parseInt(data[4]), Integer.parseInt(data[5]));
                System.out.println("i is "+i+" and i just made an employee card!");

                for (int j = 0 ; j > 6 ; j++){
                    data[j]=null;
                }
                i++;
            }    

        } catch (Exception e) {
            System.err.println("Error caught : " +e.getMessage());
        }

    }
    
    public FoodFrenzy(Chef P1, Chef P2){
        position1r = getRow(P1.getPosition());
        position1c = getColoumn(P1.getPosition());
        position2r = getRow(P2.getPosition());
        position2c= getColoumn(P2.getPosition());
        readFileToFill();
    }

    public int getRow(int position){

        int potentialPositionA = 8;
        int potentialPositionB = 24;

        for (int i = 1 ; i<6 ; i++){
            if (position == potentialPositionA || position == potentialPositionB){
                return i;
            } else {
                potentialPositionA++;
                potentialPositionB--;
            }
        }

        if(position<=7){
            return 0;
        } else if(position==25){
            return 3;
        } else {
            return 6;
        }

    }

    public int getColoumn(int position){

        int potentialPositionA = 2;
        int potentialPositionB = 18;

        for (int i = 1 ; i<6 ; i++){
            if (position == potentialPositionA || position == potentialPositionB){
                return i;
            } else {
                potentialPositionA++;
                potentialPositionB--;
            }
        }

        if ((position>=19 && position <=24) || position == 1){
            return 0;
        } else if (position==25){
            return 3;
        }else {
            return 6;
        }

    }

    public void printBoard(Chef P1, Chef P2){
        position1r = getRow(P1.getPosition());
        position1c = getColoumn(P1.getPosition());
        position2r = getRow(P2.getPosition());
        position2c= getColoumn(P2.getPosition());
        
        String red = "\u001B[41m";
        String blue = "\033[44m";
        String reset = "\033[0m";

        //method to make a copy of an array
        String[][] copyBoard = new String[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }

        //determine what to do based on whether or not players are on the same position
        if (samePosition()==true){
            String pt1 = copyBoard[position1r][position1c].substring(0, 2);
            String pt2 = copyBoard[position1r][position1c].substring(2, 4);
            copyBoard[position1r][position1c] = red+pt1+blue+pt2+reset;
        } else { //in the case they are in two different positions
            copyBoard[position1r][position1c] = red+copyBoard[position1r][position1c]+reset;
            copyBoard[position2r][position2c] = blue+copyBoard[position2r][position2c]+reset;
        }

        System.out.println("─────────────────────────────────────────");
        for (int i = 0; i<copyBoard.length; i++){
            for (int j = 0; j < copyBoard[i].length; j++){
                System.out.print(copyBoard[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("─────────────────────────────────────────");

    }

    //maybe delete later?
    public void positionUpdate(Chef P, int turn){
        if (turn%2==1){//if the number is odd, player 1's(red) turn
            position1r = getRow(P.getPosition());
            position1c = getColoumn(P.getPosition());
        } else {
            position2r = getRow(P.getPosition());
            position2c= getColoumn(P.getPosition());
        }
    }
    
    public boolean samePosition(){
        if ((position1r == position2r) && (position1c == position2c)){
            return true;
        } else {
            return false;
        }
    }

    public BoardSquare getSquare(Chef player){

        Scanner in = new Scanner (System.in);
        int boardPosition = (player.getPosition()-1);

        return playBoard[boardPosition];

    } 

    public void print2D(String arr[][]){
        // Loop through all rows
        for (int i = 0; i < arr.length; i++)
 
            // Loop through all elements of current row
            for (int j = 0; j < arr[i].length; j++)
                System.out.print(arr[i][j] + " ");
    }

    public void printArr(String arr[]){
        for (int i = 0 ; i<arr.length ; i++){
            System.out.print(arr[i] + " ");
        }
    }

    

}
