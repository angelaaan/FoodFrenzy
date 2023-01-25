/* Angela Nguyen
 * ICS4U Final Project
 * Semester 1 Jan 2023
 * This code is the ChanceCard that inherits from BoardSquare
 */
import java.util.*;
public class ChanceCard extends BoardSquare {
    
    public ChanceCard(){
        super(3, "Chance");
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "THIS IS A CHANCE CARD";
    }
}
