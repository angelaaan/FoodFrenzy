public class Chef {
    String name;
    int position;
    double balance;
    int lapCount;
    double netWorth;
    boolean debt;
    boolean jail;
    String colourCode;
    EmployeeList list = new EmployeeList();
    
    //constructor
    public Chef(String Name, String color){
        name = Name;
        balance = 2000;
        position = 1;
        lapCount = 0;
        colourCode = color;
        debt = false;
        jail = false;
    }

    //constructor that can set up all the instance variables excluding the next variable
    public Chef (String Name, int Position, double Balance, int LapCount, double NetWorth, boolean Debt, boolean Jail, String Color){
        name = Name;
        position = Position;
        balance = Balance;
        lapCount = LapCount;
        netWorth = NetWorth;
        debt = Debt;
        jail = Jail;
        colourCode = Color;
    }

    //empty constructor
    public Chef() {
        name = "";
    }

    //accessor methods
    public double getBalance(){
        return balance;
    }

    public EmployeeList getList(){
        return list;
    }

    public int getLapCount(){
        return lapCount;
    }

    public int getPosition(){
        return position;
    }

    public String getName(){
        return name;
    }

    public String getColour(){
        return colourCode;
    }

    public boolean getJail(){
        return jail;
    }

    //mutator methods
    public void setJail(boolean status){
        jail = status;
    }

    public void setPosition(int num){
        position = num;
    }

    public void setBalance(int num){
        balance = (double)num;
        if (balance < 0){
            debt = true;
        }
    }

    public void setBalance (double num){
        balance = num;

        if (balance < 0){
            debt = true;
        }
    }

    public void setList(EmployeeList newList){
        list = newList;
    }

    //increasing the amount of laps for that player
    public void lapCompleted(){
        lapCount++;
    }

    //checking for debt
    public boolean checkDebt(){
        return debt;
    }

    //override the original string method
    public String toString(){

        String info = "──────────────────\n"+colourCode+"Name: " + name 
        + "\nPosition: Square #"+position + "\nBalance: "+ balance + "\nNet Worth: "+netWorth
        + "\nEmployee Count: "+list.size()+"\nLaps: "+lapCount+"\n\u001B[0m──────────────────";

        return info;
    }

    //method to return a comma-seperated format version of the information
    public String toWrite(){
        String info =name+","+position+","+balance+","+lapCount+","+netWorth+","
            +debt+","+jail+","+colourCode;

        return info;
    }

}
