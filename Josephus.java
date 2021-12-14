// name: Phuong Pham    date: 12/9/2021 

import java.util.*;
import java.io.*;

public class Josephus
{
   private static String WINNER = "Josephus";
   public static void main(String[] args) throws FileNotFoundException
   {
   /* run it first with J_numbers.txt  */
      ListNode p = null;
      Scanner sc = new Scanner(System.in);
      System.out.print("How many names (2-20)? ");
      int n = Integer.parseInt(sc.next());
      p = readNLinesOfFile(n, new File("J_numbers.txt"));
      System.out.print("How many names to count off each time?"  );
      int countOff = Integer.parseInt(sc.next());
      p = countingOff(p, countOff, n);
      System.out.println(p.getValue() + " is the winning position."); 
      
   	/* run it next with J_names.txt  */
      System.out.println("\n ****  Now start all over. **** \n");
      p = readNLinesOfFile(n, new File("J_names.txt"));
      System.out.print("Enter the winning position:  ");
      int winPos = Integer.parseInt(sc.next());        
      replaceAt(p, WINNER, winPos);
      p = countingOff(p, countOff, n);
      System.out.println(p.getValue() + " wins!");    
   }
   
   public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      p = countingOff(p, countOff, n);
      return p;
   }
   
   public static ListNode josephusCircle(int n, int countOff, String filename, int winPos) throws FileNotFoundException
   {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      replaceAt(p, WINNER, winPos);
      p = countingOff(p, countOff, n);
      return p;
   }  
   
   /* reads the names, calls insert(), builds the linked list.
	  */
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException
   {
      // instantiate a Scanner object with parameter f
      // set a counter to 0
      // declare a ListNode variable as null
      
      // while loop condition: check that your counter is smaller than n and
      // that the Scanner reference variable has more in the file
         // call your insert method and pass it the ListNode variable and 
         // a call to the Scanner variable's .next() method
         // increment your counter
      // Have your Scanner variable call .close()
      // return your ListNode object
      
      Scanner scan = new Scanner(f);
      int counter = 0;
      ListNode list = null;
      
      while (counter<n && scan.hasNext()) {
         list = insert(list, scan.next());
         counter++;
      }
      
      scan.close();
      return list;
   }
   
    /* helper method to build the list.  Creates the node, then
    inserts it in the circular linked list.
	 */
   private static ListNode insert(ListNode p, Object obj)
   {
      // instantiate a temp ListNode object with a value of obj
      // and a pointer to null
      
      // check if the parameter p is null
         // if it is, setNext for the temp ListNode object to temp
      // otherwise
         // setNext for the temp ListNode object to p.getNext()
         // setNext for p to temp
      // return the temp ListNode object
      
      ListNode temp = new ListNode(obj, null);
      
      if (p==null) {
         temp.setNext(temp);
      } else {
         temp.setNext(p.getNext());
         p.setNext(temp);
      }
      
      return temp;
   }
   
  /* Runs a Josephus game, counting off and removing each name. Prints after each removal.
     Ends with one remaining name, who is the winner. 
	  */
   public static ListNode countingOff(ListNode p, int count, int n)
   {
      // call the print method
      // loop n - 1 times
         // call remove and pass it p and count, then assign the return value to p
         // call print again
      // return p's next reference
      
      print(p);
      for (int i=0; i<n-1; i++) {
         p = remove(p, count);
         print(p);
      }
      
      return p.getNext();
   }
   
   /* removes the node after counting off count-1 nodes.
	  */
   private static ListNode remove(ListNode p, int count)
   {
      // loop n - 1 times
         // call p.getNext() and assign it to p
         
      // update p's next reference to skip over the next node
      // return p
      
      for (int i=0; i<count-1; i++) {
         p = p.getNext();
      }
      p.setNext(p.getNext().getNext());
      return p;
   }
   
   /* prints the circular linked list.
	  */
   public static void print(ListNode p)
   {
      ListNode current = p.getNext();
      
      do {
         System.out.print(current.getValue() + " ");
         current = current.getNext();
      } while (current!=p.getNext());
      System.out.println();
   }
	/* replaces the value (the string) at the winning node.
	   */
   public static void replaceAt(ListNode p, Object obj, int pos)
   {
      ListNode current = p;
      for (int i=0; i<pos; i++) {
         current = current.getNext();
      }
      current.setValue(obj);
   }
}

