public class Chef {
    String name;
    int position;
    double balance;
    int rolls;
    int lapCount;
    double netWorth;
    boolean debt;
    boolean jail;
    String colourCode;
    EmployeeList list = new EmployeeList();
    
    public Chef(String Name, String color){
        name = Name;
        balance = 1000;
        position = 1;
        lapCount = 0;
        colourCode = color;
        debt = false;
        jail = false;
    }

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

    public void lapCompleted(){
        lapCount++;
    }

    public boolean checkDebt(){
        return debt;
    }

    public String toString(){
        String info = "──────────────────\n"+colourCode+"Name: " + name + "\nPosition on Board: Square #"+position + "\nBalance: "+ balance + "\nNet Worth: "+netWorth
        + "\nEmployee Count: "+list.size()+"\n\u001B[0m──────────────────";

        return info;
    }

}
