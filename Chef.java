public class Chef {
    String name;
    int position;
    double balance;
    int rolls;
    int lapCount;
    double netWorth;
    EmployeeList list = new EmployeeList();
    
    public Chef(String Name){
        name = Name;
        balance = 1000;
        position = 1;
        lapCount = 0;
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

    public void lapCompleted(){
        lapCount++;
    }

    public String toString(){
        String info = "Name:" + name + "\nPosition on Board: Square #"+position + "\nBalance: "+ balance + "\nNet Worth: "+netWorth
        + "\nEmployee Count: "+list.size();

        return info;
    }

}
