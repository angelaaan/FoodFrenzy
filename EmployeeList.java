/* Name : Angela Nguyen
 * ICS4U
 * 2023/01/06
 * This program is the linked queue for the sushi node object
 */
public class EmployeeList {
    //instance variables
    private Employee head;
    private Employee rear;
    private int size;

    // Creates an empty queue.
    public EmployeeList() {
        head = rear = null;
        size = 0;
    }

    // Adds the specified data to the rear of the queue. The enQueue method
    public void hire(Employee node) {

        if (isEmpty()) {
            head = node;
        } else {
            rear.setNext(node);
        }
        rear = node;
        size++;
    }

    /*
     * Removes the data at the front of the queue and 
     * throws an Exception if the queue is empty.
     * the deQueue method
     */
    public void fire() throws Exception {

        //throw if thereare 0 items
        if (isEmpty()){
            throw new Exception("queue");
        }

        //erase the head and decerase size
        head = head.getNext();
        size--;

        //if it is now empty, set the rear back to null
        if (isEmpty()){
            rear = null;
        }

    }

    /*
     * Sorting the list by prices using bubble sort
     * may just be the worst sort list uve ever seen in ur entire life who knows...
     */
    public void sortList()
    {
        // Sushi current will point to head
        Employee current = head;
        Employee index = null; 
 
        if (head == null) {
            return;
        }
        else {
            while (current != null) {
                // Employee index will point to node next to current
                index = current.getNext();
 
    //             String name;
    // String companyName;
    // String job;
    // int payRate;
    // int earnings;
    // boolean hired;
    
                while (index != null) {
                    // If current Employees earnings is greater than the next, swap
                    if (current.getEarnings() > index.getEarnings()) {
                        Employee temp = new Employee(current.getPosition(), current.getJob(), current.getName(), current.getCompany(), current.getPayRate(), current.getEarnings());
                        // String tempName = current.getName();
                        // String tempCompany = current.getCompany();
                        // int tempPay = current.getPayRate();
                        // int tempEarning = current.getEarnings();
                        // boolean tempHired = current.getHired();

                        current = new Employee(index.getPosition(), index.getJob(), index.getName(), index.getCompany(), index.getPayRate(), index.getEarnings());
                        // current.setName(index.getName());
                        // current.setCompany(index.getCompany());
                        // current.setPay(index.getPayRate());
                        // current.setEarning(index.getEarnings());
                        // current.setHired(index.getHired());

                        index = new Employee(temp.getPosition(), temp.getJob(), temp.getName(), temp.getCompany(), temp.getPayRate(), temp.getEarnings());
                        // index.setName(tempName);
                        // index.setImage(tempImage);
                        // index.setPrice(tempPrice);
                    }
                    
                    index = index.getNext();
                }
                current = current.getNext();
            }

            System.out.println(this.toString());
        }
    }

    /*
     * Returns the head of the list in the Employee list
     */
    public Employee getHead(){
        return head;
    }

    /*
     * Returns true if this queue is empty and false otherwise.
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /*
     * Returns the number of elements in this queue.
     */
    public int size() {
        return size;
    }

    /*
     * Empties the queue.
     */
    public void makeEmpty(){
        head = rear = null;
        size = 0;
    }

    /*
     * returns a toString reprsentation of the entire queue of sushi
     * without the images and only with the sushi informations
     */
    public String toString() {
        if (isEmpty()){
            System.out.println("this is awkward...you forgot to hired employees..");
        }

        //Declare and initialize variables
        String info = "";
        Employee current = head;
        int i = 1;

        //go through the linked lists and fills a string with information
        while (current != null) {
            info += "[" + i +"] - "+current.toString() + "\n";
            current = current.getNext();
            i++;
        }

        return info;
    }

}