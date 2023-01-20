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
