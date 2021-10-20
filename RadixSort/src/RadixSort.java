import java.util.LinkedList;
import java.util.Random;
 
public class RadixSort {
 
  private static LinkedList[] q = {
      new LinkedList(), // 0
      new LinkedList(), // 1
      new LinkedList(), // 2
      new LinkedList(), // 3
      new LinkedList(), // 4
      new LinkedList(), // 5
      new LinkedList(), // 6
      new LinkedList(), // 7
      new LinkedList(), // 8
      new LinkedList() // 9
  };
 

  public static void main(String[] args)
  {
    Object[] list = new Object[11];
 
    for(int i=0; i < 11; i++){
      list[i] = new Random().nextInt(1000 * 1000);
    }
    System.out.println("List is: ");
    for(int i=0; i < list.length; i++){
      System.out.print(list[i] + " ");
    }

    Object[] sortedList = sort(list);
    System.out.println(" ");
    for(int i=0; i < list.length; i++){
      System.out.print(list[i] + " ");
    }
 
  }
 
  
  public static Object[] sort(Object[] list)
  {
    // Get the maximum number of digits.
    int maxDigits = getMaxDigits(list);
 
    // Iterate through the radix depending on max digits.
    for(int r=1; r <= maxDigits; r++){
 
      // Iterate through every number.
      int radix;
      for(int n=0; n < list.length; n++){
        // Figure out what queue to put it into.
        radix = getDigitAt(Integer.parseInt(list[n].toString()), r);
        // Put it into it's queue accordinmaxDigits = getMaxDigits(list);g to the radix.
        q[radix].offer(list[n]);
      }
 
      // Go through the queues and put the numbers back into the list.
      int a=0;
      for(int k=0; k < q.length; k++){
        // Go through every element in the queue.
        while(q[k].peek() != null){
          list[a++] = q[k].poll();
        }
      }
 
    }
 
    // Return the list, it is now sorted.
    return list;
 
  }
 
  /**
   * Gets the maximum digits of a list of integers.
   * @param list
   * @return
   */
  public static int getMaxDigits(Object list[])
  {
    // Define the max digits.
    int maxDigits = 0;
 
    // Iterate through the list.
    int digits;
    for(int i=0; i < list.length; i++){
 
      // Cast the number to a string.
      digits = getDigits(Integer.parseInt(list[i].toString()));
 
      // Compare the lengths.
      if(digits > maxDigits){
        maxDigits = digits;
      }
 
    }
 
    // Return the max digits.
    return maxDigits;
  }
 
  /**
   * Gets the number of digits the specified number has.
   * @param i
   * @return
   */
  public static int getDigits(int i)
  {
    if(i < 10){
      return 1;
    }
    return 1 + getDigits(i / 10);
  }
 
  /**
   * Gets the digit at the specified radix of the specified number.
   * @param number
   * @param radix
   * @return
   */
  public static int getDigitAt(int number, int radix)
  {
    return (int)(number / Math.pow(10,radix-1)) % 10;
  }
 
}