public class JailSquare extends BoardSquare {

    public JailSquare( int boardPosition){
        super(boardPosition, "Jail");
    }

    public String getType(){
        return type;
    }

    @Override
    public String toString() {
        String info = "You are in JAIL! You gotta roll a even numer :/";
        return info;
    }

    

}
