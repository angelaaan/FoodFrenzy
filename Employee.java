public class Employee extends BoardSquare {
    String name;
    String companyName;
    String job;
    int payRate;
    int earnings;
    boolean hired;
    Employee next;

    //empty constructor employee node
    public Employee(int a){
        super(a);
    }

    //filled employee node
    public Employee(int boardPosition, String position, String Name, String company, int pay, int daily){
        super(boardPosition);
        job = position;
        name = Name;
        companyName = company;
        payRate = pay;
        earnings = daily;
        hired = false;
        next = null;
    }

    public void setHire(boolean a){
        hired = a;
    }
    
    public Employee getNext(){
        return next;
    }


    public String getStats(){
        return ("NAME : "+name+"\nCOMPANY : "+companyName+"\nPAY RATE : "+payRate+"\nSALARY: "
            +salary+"\nHIRED : "+hired);
    }


}
