import java.io.Serializable;

/* Name : Angela Nguyen
 * ICS4U
 * 2023/01/06
 * This program is the linked queue for the sushi node object
 */
public class EmployeeList implements Serializable {
    // instance variables
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

    public double calculatePayRoll() {
        Employee currentEmployee = head;
        double total = 0.0;

        while (currentEmployee != null) {
            total += currentEmployee.getPayRate();
            currentEmployee = currentEmployee.getNext();
        }

        return total;
    }

    public double calculateEarnings() {
        Employee currentEmployee = head;
        double total = 0;

        while (currentEmployee != null) {
            total += currentEmployee.getEarnings();
            currentEmployee = currentEmployee.getNext();
        }

        return total;
    }

    /*
     * Removes the data at the front of the queue and
     * throws an Exception if the queue is empty.
     * the deQueue method
     */
    public void fire() throws Exception {

        // throw if thereare 0 items
        if (isEmpty()) {
            throw new Exception("queue");
        }

        // erase the head and decerase size
        head = head.getNext();
        size--;

        // if it is now empty, set the rear back to null
        if (isEmpty()) {
            rear = null;
        }

    }

    /*
     * Sorting the list by prices using bubble sort
     * may just be the worst sort list uve ever seen in ur entire life who knows...
     */
    public void sortList() {

        // Sushi current will point to head
        Employee current = head;
        Employee index = null;
        Employee temp;

        if (head == null) {
            return;

        } else {

            while (current != null) {
                // Employee index will point to node next to current
                index = current.getNext();

                while (index != null) {

                    if (current.getEarnings() < index.getEarnings()) {

                        int tempPosition = current.getPosition();
                        String tempName = current.getName();
                        String tempCompany = current.getCompany();
                        String tempJob = current.getJob();
                        int tempPay = current.getPayRate();
                        int tempEarn = current.getEarnings();
                        boolean tempHire = current.getHired();

                        current.changeDetails(index.getPosition(), index.getName(), index.getCompany(), index.getJob(), index.getPayRate(), index.getEarnings(), index.getHired());

                        index.changeDetails(tempPosition, tempName, tempCompany, tempJob, tempPay, tempEarn, tempHire);

                    }

                    index = index.getNext();

                }

                current = current.getNext();
            }
        }
    }

    /*
     * Returns the head of the list in the Employee list
     */
    public Employee getHead() {
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
    public void makeEmpty() {
        head = rear = null;
        size = 0;
    }

    /*
     * Searches through the linked list for a name and returns whether or not its
     * true
     */
    public boolean search(Employee head, String name) {
        // Base case
        if (head == null) {
            return false;
        }

        // If key is present in current Employee node, return true
        if ((head.getName()).equalsIgnoreCase(name)) {
            return true;
        }

        // Recur through the remaining linked list
        return search(head.getNext(), name);
    }

    /*
     * returns a toString reprsentation of the entire queue of sushi
     * without the images and only with the sushi informations
     */
    public String toString() {
        if (isEmpty()) {
            System.out.println("this is awkward...you forgot to hired employees..");
        }

        // Declare and initialize variables
        String info = "";
        Employee current = head;
        int i = 1;

        // go through the linked lists and fills a string with information
        while (current != null) {
            info += "[ Employee " + i + " ]\n " + current.toString() + "\n";
            current = current.getNext();
            i++;
        }

        return info;
    }

}