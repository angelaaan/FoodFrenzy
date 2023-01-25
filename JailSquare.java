/* Angela Nguyen
 * ICS4U Final Project
 * Semester 1 Jan 2023
 * This code is the class that inherits BoardSquare
 *  for the jail and go space on the board
 */
public class JailSquare extends BoardSquare {

    public JailSquare( int boardPosition){
        super(boardPosition, "Jail");
    }

    public String getType(){
        return type;
    }

    @Override
    public String toString() {
        String info = "You are in JAIL! You gotta roll a even number :/";
        return info;
    }

    

}
