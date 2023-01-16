public class Chef {
    String name;
    int position;
    double balance;
    int rolls;
    double netWorth;
    EmployeeList list = new EmployeeList();
    
    public Chef(String Name){
        name = Name;
        balance = 1000;
        position = 1;
    }

    public double getBalance(){
        return balance;
    }

    public EmployeeList getList(){
        return list;
    }


    public int getPosition(){
        return position;
    }

    public String getName(){
        return name;
    }

    public void setPosition(int num){
        position = num;
    }

    public void setBalance(int num){
        balance = (double)num;
    }

    public void setBalance (double num){
        balance = num;
    }

    public void setList(EmployeeList newList){
        list = newList;
    }

}
