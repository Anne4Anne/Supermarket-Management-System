package com.mycompany.courseworkdatastructures;

/**
 *
 * @author owenb
 */
// class to handle the 4 most recent activities
public class StackActivity {

    private final Activity[] stack = new Activity[4];//fixed array size of 4, preventing more than 4 being stored
    private int size = 0;//initialise the size to 0

    // Add new activity and remover the oldest if greater than 4
    public void push(Activity activity) {
        if (size < 4) {
            stack[size++] = activity;// adds activity to next free position
        } else {
            // Shift left to remove oldest
            System.arraycopy(stack, 1, stack, 0, 3);
            stack[3] = activity;
        } // adds the newest activity at end of the list
    }

    // Return activities as an array
    public Activity[] toArray() {
        Activity[] arr = new Activity[size];// Array creation 
        System.arraycopy(stack, 0, arr, 0, size); // copy stored activities
        return arr;
    }

    // Getter for the number of stored activities
    public int getSize() {
        return size;
    }
}
