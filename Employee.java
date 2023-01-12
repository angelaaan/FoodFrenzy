public class Employee extends BoardSquare {
    String name;
    String companyName;
    int payRate;
    int salary;
    boolean hired;
    Employee next;

    //empty constructor employee node
    public Employee(int a){
        boardPosition = a;
    }

    //filled employee node
    public Employee(int pos, String Name, String company, int pay, int dailySalary, boolean hire){
        boardPosition = pos;
        name = Name;
        companyName = company;
        payRate = pay;
        salary = dailySalary;
        hired = hire;
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
