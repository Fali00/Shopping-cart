import java.util.Scanner;

/**
 * HandTool class that has one field which is sharpenable.
 *
 * Author Ferhan Ali
 * Date 16/03/2024
 */
public class HandTool extends Tool
{
    private boolean sharpenable;
    /**
     * Constructor for objects of class HandTool
     */
    public HandTool(String itemName, String itemCode, int cost,int timesBorrowed,boolean onLoan,int weight,boolean sharpenable)
    {
        super(itemName,itemCode,cost,timesBorrowed,onLoan,weight);
        this.sharpenable = sharpenable;
    }
    
    /**
     * Default constructor for objects of class HandTool
     */
    public HandTool()
    {
        super();
        sharpenable = false;
    }
    




    /**
     * Returns sharpenable
     */
    public boolean getSharpenable()
    { 
        return sharpenable;
    }

     /**
     * Returns sharpenable
     */
    public boolean getSharpenables()
    { 
        return sharpenable;
    }

    /**
     * Prints hand tool details 
     */
    public void printDetails()
    { 
        super.printDetails();
        System.out.println("sharpenable: " +sharpenable);
    }

    /**
     * Reads hand tool details
     */
    public void readData(Scanner scan1)
    { 
        super.readData(scan1);
        sharpenable = scan1.nextBoolean();
    }
}
