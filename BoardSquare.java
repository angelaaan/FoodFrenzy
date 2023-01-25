/* Angela Nguyen
 * ICS4U Final Project
 * Semester 1 Jan 2023
 * This code is the abstract parent class to hold all the boardSquares on the gameBoard
 */
public abstract class BoardSquare {
    int boardPosition;
    String type;

    //constructor
    public BoardSquare(int position, String type1){
        boardPosition = position;
        type = type1;
    }

    //abstract getType()
    public abstract String getType();

    //abstract toString()
    public abstract String toString();
    
}
