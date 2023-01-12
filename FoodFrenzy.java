public class FoodFrenzy {

    String[][] board = {
        {"-GO!-"," -E- "," -E- "," -E- "," -?- "," -E- "," -?- "}, //ROW 0
        {" -E- ","     ","     ","     ","     ","     "," -E- "}, //ROW 1
        {" -E- ","     ","     ","     ","     ","     "," -E- "}, //ROW 2
        {" -E- ","     ","     ","JAIL!","     ","     "," -?- "}, //ROW 3
        {" -E- ","     ","     ","     ","     ","     "," -E- "}, //ROW 4
        {" -?- ","     ","     ","     ","     ","     "," -E- "}, //ROW 5
        {" -?- "," -E- "," -E- "," -?- "," -E- "," -E- "," -?- "}  //ROW 6
    };

    int position1r;
    int position1c;
    int position2r;
    int position2c;
    
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

        //old attempts
        // if(position<=7){
        //     return 0;
        // } else if (position==8 || position == 24){
        //     return 1;
        // } else if (position==9 || position == 23){
        //     return 2;
        // } else if (position == 10 || position == 22 || position == 25){
        //     return 3;
        // } else if (position == 11 || position == 21){
        //     return 4;
        // } else if (position == 12 || position == 20){
        //     return 5;
        // } else {
        //     return 6;
        // }

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

        // if ((position>=19 && position <=24) || position == 1){
        //     return 0;
        // } else if (position==2 || position == 18){
        //     return 1;
        // } else if (position==3 || position == 17){
        //     return 2;
        // } else if (position==4 || position == 16 || position == 25){
        //     return 3;
        // } else if (position == 5 || position ==15){
        //     return 4;
        // } else if (position == 6 || position == 14){
        //     return 5;
        // } else {
        //     return 6;
        // }


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

    


}
