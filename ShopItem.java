import java.util.Scanner;

/**
 * ShopItem class which has shopitem details with accessor methods.
 *
 * Author Ferhan Ali
 * Date 15/03/2023
 */
public abstract class ShopItem 
{
    private String itemName;
    private String itemCode;
    private int cost;
    /**
     * Constructor for objects of class ShopItem
     */
    public ShopItem(String itemName, String itemCode, int cost)
    {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.cost = cost;
    }

    /**
    /* default constructor for objects of class ShopItem
     */
    public ShopItem()
    { 
       itemName = "";
       itemCode = "";
       cost = 0;
    }

    /**
     * Returns toolname
     */
    public String getItemName()
    { 
        return itemName;
    }

    /**
     * Returns itemcode
     */
    public String getItemCode()
    {
        return itemCode;
    }

    /**
     * Returns costs
     */
    public int getCost()
    {
        return cost;
    }

    /**
     * Print details of data
     */
    public void printDetails()
    { 
        System.out.println("item name: " +itemName+ " item code: " +itemCode+" cost: " +cost);
    }

    /**
     * Reads shop items data.
     */
    public void readData(Scanner scan1)
    {    
        itemName = scan1.next();
        itemCode = scan1.next();
        cost = scan1.nextInt();
    }
}
