public class EmptySquare extends BoardSquare {
    String info;

    public EmptySquare( int boardPosition, String information ){
        super(boardPosition, "Empty");
        info = information;
    }

    public String getType(){
        return type;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    

}
