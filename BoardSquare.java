public abstract class BoardSquare {
    int boardPosition;
    String type;

    public BoardSquare(int position, String type1){
        boardPosition = position;
        type = type1;
    }

    public abstract String getType();

    public abstract String toString();
    
}
