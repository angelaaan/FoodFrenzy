public class Chef {
    String name;
    int position;
    double balance;
    int rolls;
    double netWorth;
    
    public Chef(String Name){
        name = Name;
        position = 1;
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

}
