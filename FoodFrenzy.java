public class FoodFrenzy {
    private int[][] arr;
    private int position1r;
    private int position1c;
    private int position2r;
    private int position2c;
    private String[][] board = new String [3][3];
        // {"-GO!-"," -E- "," -E- "," -E- "," -?- "," -E- "," -?- "}, //ROW 0
        // {" -E- ","     ","     ","     ","     ","     "," -E- "}, //ROW 1
        // {" -E- ","     ","     ","     ","     ","     "," -E- "}, //ROW 2
        // {" -E- ","     ","     ","JAIL!","     ","     "," -?- "}, //ROW 3
        // {" -E- ","     ","     ","     ","     ","     "," -E- "}, //ROW 4
        // {" -?- ","     ","     ","     ","     ","     "," -E- "}, //ROW 5
        // {" -?- "," -E- "," -E- "," -?- "," -E- "," -E- "," -?- "}  //ROW 6
    // };

   // BoardSquare[] playBoard = new BoardSquare[24];
    // playBoard[0] = new EmptySquare(0, "YOURE ON THE GO SQUARE HEHE! YOUR EMPLOYEES MADE YOU MONEY");
    // playBoard[1] = new Employee(1, "Data Scientist","Harley Quinn", "FoodChainID", "300", "150");
    // playBoard[2] = new Employee(2, "Software Engineer","Joshua Triffo", "FoodChainID", "210","100");
    // playBoard[3] = new Employee(3, "Web Developer", "An Ha", "UberEats", "270", "120");
    // playBoard[4] = new ChanceCard();
    // playBoard[5] = new Employee(5, "Data Scientist", "Edwin Ngui", "UberEats", "420", "200");
    // playBoard[6] = new ChanceCard();

    // playBoard[7] = new Employee(7, "API Management", "Sonny Ebora", "UberEats", "360", "195");
    // playBoard[8] = new Employee(8, "CAD Designer", "Tolga Selcuk", "UberEats", "420", "210");
    // playBoard[9] = new ChanceCard();
    // playBoard[10] = new Employee(10, "Service Technician", "Hammad Khalil", "AutoMistTM", "500", "300");
    // playBoard[11] = new Employee(11, "Mechnical Engineer", "Luke Granados", "AutoMistTM", "540", "320");
    // playBoard[12] = new ChanceCard();
    // playBoard[13] = new Employee(13, "Mechanical Engineer", "Parantap Bhatt", "Beyond Meat", "430", "240");
    // playBoard[14] = new Employee(14, "Software Engineer", "Angela Nguyen", "Beyond Meat", "420", "230");
    // playBoard[15] = new ChanceCard();
    // playBoard[16] = new Employee(16, "Data Scientist", "Eric Doucet", "Beyond Meat", "390", "210");
    // playBoard[17] = new Employee(17, "Biological Engineer", "Nini Polad", "Turtle Tree", "500", "340");
    // playBoard[18] = new ChanceCard();
    // playBoard[19] = new ChanceCard();
    // playBoard[20] = new Employee(20, "Maintanence Technician", "Dillon Hu", "Turtle Tree", "300", "190");
    // playBoard[21] = new Employee(21, "Software Engineer", "Nearhos Lotus", "Turtle Tree", "340", "500");
    // playBoard[22] = new Employee(22, "Analytical Chemist", "Justin Tran", "FoodChainID","400", "230");
    // playBoard[23] = new Employee(23, "Full Stack Web Developer", "Vincent Tran", "FoodChainID","294", "260");


    
    public FoodFrenzy(Chef P1, Chef P2){
        position1r = getRow(P1.getPosition());
        position1c = getColoumn(P1.getPosition());
        position2r = getRow(P2.getPosition());
        position2c= getColoumn(P2.getPosition());
    }

    public int getRow(int position){

        int potentialPositionA = 8;
        int potentialPositionB = 24;

        for (int i = 1 ; i<5 ; i++){
            if (position == potentialPositionA || position == potentialPositionB){
                return i;
            } else {
                potentialPositionA++;
                potentialPositionB--;
            }
        }

        if(position<=7){
            return 0;
        } else {
            return 6;
        }

    }

    public int getColoumn(int position){

        int potentialPositionA = 2;
        int potentialPositionB = 18;

        for (int i = 1 ; i<5 ; i++){
            if (position == potentialPositionA || position == potentialPositionB){
                return i;
            } else {
                potentialPositionA++;
                potentialPositionB--;
            }
        }

        if ((position>=19 && position <=24) || position == 1){
            return 0;
        } else {
            return 6;
        }

    }

    public void printBoard(){
        String red = "\u001B[41m";
        String blue = "\033[44m";
        String reset = "\033[0m";

        String[][] copyBoard = board;

        if (samePosition()==true){
            String pt1 = copyBoard[position1r][position1c].substring(0, 2);
            String pt2 = copyBoard[position1r][position1c].substring(2, 4);
            copyBoard[position1r][position1c] = red+pt1+blue+pt2+reset;
        } else {
            copyBoard[position1r][position1c] = red+copyBoard[position1r][position1c]+reset;
            copyBoard[position2r][position2c] = blue+copyBoard[position2r][position2c]+reset;
        }

        for (int i = 0; i<copyBoard.length; i++){
            for (int j = 0; j < copyBoard[i].length; j++){
                System.out.print(copyBoard[i][j] + " ");
            }
            System.out.println();
        }

    }

    public boolean samePosition(){
        if ((position1r == position2r) && (position1c == position2c)){
            return true;
        } else {
            return false;
        }
    }

    public void play(Chef player){

    }

    

}
