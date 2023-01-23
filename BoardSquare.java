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
