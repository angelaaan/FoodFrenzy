import java.io.Serializable;

public class EmployeeCard extends BoardSquare  implements Serializable {
    String name;
    String companyName;
    String job;
    int payRate;
    int earnings;
    boolean hired;
    EmployeeCard next;

    //empty constructor employee node
    public EmployeeCard(int a){
        super(a, "Employee");
        type = "Employee";
    }

    //filled employee node
    public EmployeeCard(int boardPosition, String position, String Name, String company, int pay, int daily){
        super(boardPosition, "Employee");
        job = position;
        name = Name;
        companyName = company;
        payRate = pay;
        earnings = daily;
        hired = false;
        next = null;
    }

    public EmployeeCard (int boardPosition, String n, String company, String Job, int Pay, int Earn, boolean Hire){
        super(boardPosition, "Employee");
        name = n;
        companyName = company;
        job = Job;
        payRate = Pay;
        earnings = Earn;
        hired = Hire;
    }

    public EmployeeCard (int boardPosition, String n, String company, String Job, int Pay, int Earn, boolean Hire, EmployeeCard newNext){
        super(boardPosition, "Employee");
        name = n;
        companyName = company;
        job = Job;
        payRate = Pay;
        earnings = Earn;
        hired = Hire;
        next = newNext;
    }

    public void changeDetails (int newPositon, String newName, String newCompany, String newJob, int newPay, int newEarning, boolean newHire){
        boardPosition = newPositon;
        name = newName;
        companyName = newCompany;
        job = newJob;
        payRate = newPay;
        earnings = newEarning;
        hired = newHire;
    }

    public void setHire(boolean a){
        hired = a;
    }

    public boolean getHired(){
        return hired;
    }
    
    public EmployeeCard getNext(){
        return next;
    }

    public String getName(){
        return name;
    }

    public int getEarnings(){
        return earnings;
    }

    public String getJob(){
        return job;
    }

    @Override
    public String toString() {
        return ("──────────────────\n\u001B[35mNAME : "+name+"\nJOB : "+job+"\nCOMPANY : "+companyName+"\nPAY RATE : "+payRate+"\nEARNINGS : "
            +earnings+"\nHIRED : "+hired+"\n\u001B[0m──────────────────");
    }

    @Override
    public String getType() {
        return type;
    }

    public void setNext(EmployeeCard node) {
        next = node;
    }

    public void setName(String name2) {
        name = name2;
    }

    public void setEarning(int earnings2) {
        earnings = earnings2;
    }

    public void setHired(boolean hired2) {
        hired = hired2;
    }

    public String getCompany() {
        return companyName;
    }

    public int getPayRate() {
        return payRate;
    }

    public int getPosition() {
        return boardPosition;
    }

    public String toWrite(){
        String info =boardPosition+","+name+","+companyName+","+job+","+payRate+","+earnings+","+hired+"/";
        
        return info;
    }


}
