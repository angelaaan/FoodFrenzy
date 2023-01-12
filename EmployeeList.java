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

    // Adds the specified data to the rear of the queue.
    public void enQueue(Employee node) {

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
     */
    public String deQueue() throws Exception {

        //throw if thereare 0 items
        if (isEmpty()){
            throw new Exception("queue");
        }
            
        //holds onto head data to return later
        String data = head.set();
        System.out.println(head.getImage());

        //erase the head and decerase size
        head = head.getNext();
        size--;

        //if it is now empty, set the rear back to null
        if (isEmpty()){
            rear = null;
        }

        //return data
        return data;
    }

    /*
     * Sorting the list by prices using bubble sort
     */
    public void sortList()
    {
        // Sushi current will point to head
        Employee current = head;
        Employee index = null;
 
        String tempName;
        String tempImage;
        int tempPrice;
 
        if (head == null) {
            return;
        }
        else {
            while (current != null) {
                // Sushi index will point to node next to current
                index = current.getNext();
 
                while (index != null) {
                    // If current sushi's price is greater
                    // than index's sushi's price, swap the data
                    // between them
                    if (current.getPrice() > index.getPrice()) {
                        tempName = current.getName();
                        tempImage = current.getImage();
                        tempPrice = current.getPrice();

                        current.setName(index.getName());
                        current.setImage(index.getImage());
                        current.setPrice(index.getPrice());

                        index.setName(tempName);
                        index.setImage(tempImage);
                        index.setPrice(tempPrice);
                    }
                    
                    index = index.getNext();
                }
                current = current.getNext();
            }

            System.out.println(this.toString());
        }
    }

    /*
     * Returns the head of the list in the Sushi type
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
            info += "[" + i +"] - "+current.getStats() + "\n";
            current = current.getNext();
            i++;
        }

        return info;
    }

}