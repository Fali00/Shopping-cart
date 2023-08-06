import java.util.ArrayList;
import java.awt.*;
import java.util.Scanner;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

/**
 * This is a shop class where customer can do many things such as
 * stores items and make reservations etc.
 * Author Ferhan
 * Date 16/03/2023
 */
public class Shop
{
    //private ArrayList<ShopItem> itemList;
    //private ArrayList<Customer> customerList;
    //private List<ShopItem> itemList;
    //private List<Customer> customerList;
    //private List<ShopItemReservation> itemReservationList;  

    private Map<String, Customer> customerMap;
    private Map<String, ShopItem> itemsMap;
    private Map<String, ShopItemReservation> itemReservationMap;
    private Random randomGenerator;
    private Diary diary;

    /**
     * Constructor for objects of class Shop
     */
    public Shop()
    {
        //itemList = new ArrayList<ShopItem>();
        //customerList = new ArrayList<Customer>();
        //itemReservationList = new ArrayList<ShopItemReservation>();

        customerMap = new HashMap<String, Customer>();
        itemsMap = new HashMap<String, ShopItem>();
        itemReservationMap = new HashMap<String, ShopItemReservation>();
        diary= new Diary();
    }

    //**
    //* returns item lists
    //*/
    //public ArrayList<ShopItem> getItemList()
    //{ 
    //return itemList;
    //}

    //**
    //* returns lists
    //*/
    //public List<ShopItem> getItemList()
    //{ 
    // return itemList;
    //}

    //**
    //* returns customer lists
    //*/
    //public ArrayList<Customer> getCustomerList()
    //{ 
    //return customerList;
    //}

    ///**
    //* returns customer lists
    //*/
    //public List<Customer> getCustomerList()
    //{ 
    //return customerList;
    //}

    /**
     * Returns customer id
     */
    public Customer getCustomer(String customerID)
    { 
        if (customerID != null)
        { 
            return customerMap.get(customerID);
        }

        else 
        { 
            return null;
        } 
    }

    /**
     * Returns item.
     */
    public ShopItem getItem(String itemCode)
    { 
        if (itemCode != null)
        { 
            return itemsMap.get(itemCode);
        }

        else 
        { 
            return null;
        } 
    }

    /**
     * Returns item reserved
     */
    public ShopItemReservation getItemReservation(String reservationNo)
    { 
        if (reservationNo != null)
        { 
            return itemReservationMap.get(reservationNo);
        }
        else
        { 
            return null;
        }
    }

    /**
     * It allows user to make reservation 
     */
    public boolean makeItemReservation(String customerID, String itemID,String startDate, int noOfDays)
    {   
        if (customerID == null || itemID == null ||  startDate == null ||noOfDays <= 0 )
        {
            System.out.println("Your data is unvalid");
            return false;
        }

        Date reservationStartDate = DateUtil.convertStringToDate(startDate);
        Date reservationEndDate = DateUtil.incrementDate(reservationStartDate, noOfDays - 1);
        Date date = reservationStartDate;
        boolean isFree = true;
        for(int day=1; day<=noOfDays; day++)
        { 
            ShopItemReservation[] reservations = diary. getReservations(date);
            if(reservations==null)
            {
                continue;
            }
            else
            {
                
                for (ShopItemReservation reservation: reservations)
                {                           
                    if (reservation.getItemID().equals(itemID))
                    {
                        isFree = false;
                        System.out.println("Shop item "+itemID + ": is not available to reserve on " +DateUtil.convertDateToShortString(date));
                        System.out.println("/////////////////////////////////////////////////////////////////////////");
                    }
                }
            }
            date = DateUtil.nextDate(date);
        }
        if (isFree)
        {
            String reservationNo = generateReservationNo(6);
            ShopItemReservation shopItemReservation = new ShopItemReservation(reservationNo,itemID,customerID,startDate,noOfDays);
            storeItemReservation(shopItemReservation);
            return true;
        }
        return false;
    }

    /**
     * Stores item list
     */
    public void storeItem(ShopItem shopItem)
    {  
        //itemList.add(shopItem);
        itemsMap.put(shopItem.getItemCode(), shopItem);
    }

    /**
     * Stores iterm reservation to the list.
     */
    public void storeItemReservation(ShopItemReservation shopItemReservation)
    { 
        //itemReservationList.add(shopItemReservation);
        itemReservationMap.put(shopItemReservation.getReservationNo(), shopItemReservation);
        diary.addReservation(shopItemReservation);
    }

    /**
     * Generates customer id.
     */
    public String generateCustomerID(String name, int number)
    { 
        Random randomGenerator = new Random();
        int id = randomGenerator.nextInt(6);
        String formattedId = String.format("%0"+number+"d", id);
        return name+formattedId;
    }

    /**
     * Print dairy entries with start and end date.
     */
    public void printDiaryEntries(String startDateString, String endDateString)
    { 
        DateUtil date = new DateUtil();
        Date startDate = date.convertStringToDate(startDateString);
        Date endDate = date.convertStringToDate(endDateString);
        Diary diarys = new Diary();
        diarys.printEntries(startDate, endDate);
    }

    /**
     * Delete item from reservation list.
     */
    public void  deleteItemReservation(String reservationNo)
    {
        if (reservationNo != null)
        {
            for (ShopItemReservation shopItemReservation: itemReservationMap.values()) 
            {
                if(shopItemReservation.getReservationNo().equals(reservationNo))
                {
                  itemReservationMap.remove(shopItemReservation.getReservationNo(),shopItemReservation);
                }
            }
        }
    }

    /**
     * Generates reservation number
     */
    public String generateReservationNo(int number)
    { 
        Random randomGenerator = new Random();
        int id = randomGenerator.nextInt(6);
        String formattedId = String.format("%0"+number+"d", id);
        return formattedId;
    }

    /**
     * Stores customer list
     */
    public void storeCustomer(Customer customer)
    {  
        //customerMap.add(customer);
        if (customer.getCustomerID().equals ("unknown"))
        { 
            String id = generateCustomerID("AB-", 6); 
            customer.setCustomerID(id);
        }
        customerMap.put(customer.getCustomerID(), customer);
    }

    /**
     * Print all details of shop item
     */
    public void printAllItems()
    {
        //for (ShopItem shopItem: itemList)
        //{  
        //shopItem.printDetails();
        //}

        for(ShopItem shopItems: itemsMap.values())
        {
            shopItems.printDetails();
        }
    }

    /**
     * Print items that has been reserved
     */
    public void printItemReservations()
    { 
        //for (ShopItemReservation shopItemReservation: itemReservationList)
        //{ 
        // shopItemReservation.printDetails();
        //}
        for (ShopItemReservation shopItemReservation: itemReservationMap.values())
        { 
            shopItemReservation.printDetails();
        }
    }

    /**
     * Print all details of customer
     */
    public void printAllCustomers()
    {
        //for (Customer customer: customerList)
        //{ 
        // customer.printDetails();
        //}

        for(Customer customer: customerMap.values())
        {
            customer.printDetails();
        }
    }

    /**
     * Reads item data
     */
    public void readItemData() 
    {
        Frame frame = null;
        FileDialog file = new FileDialog (frame, "Open", FileDialog.LOAD);
        file.setVisible(true);
        String filename = file.getFile();
        String directorypath = file.getDirectory();
        File myFile = new File(directorypath +filename);
        String typeOfData = "";
        ShopItem item = null;
        Scanner scan=null;
        try 
        { 
            scan = new Scanner(myFile);
        }
        catch (FileNotFoundException e){ 
            System.out.println("File not found" +myFile);
        }

        while (scan.hasNextLine())
        {
            String lineOfText = scan.nextLine().trim();
            if (!lineOfText.startsWith("//") && (!lineOfText.isEmpty()))
            { 
                Scanner scan1 = new Scanner(lineOfText).useDelimiter("[ ]*,[ ]*");
                //String toolName = scan1.next().; 
                //String itemCode = scan1.next();
                //int timesBorrowed = scan1.nextInt();
                //boolean onLoan = scan1.nextBoolean();
                //int cost = scan1.nextInt();
                //int weight = scan1.nextInt();
                //tool.readData(scan1);
                if (lineOfText.startsWith("["))
                {
                    if (lineOfText.toUpperCase().startsWith("[ELECTRICTOOL DATA]"))
                    { 
                        typeOfData = "electric";
                    }
                    else if (lineOfText.toUpperCase().startsWith("[HANDTOOL DATA]"))
                    { 
                        typeOfData = "handtool";
                    }
                    else if (lineOfText.toUpperCase().startsWith("[PERISHABLE DATA]"))
                    { 
                        typeOfData = "perishable";
                    }
                    else if (lineOfText.toUpperCase().startsWith("[WORKWEAR DATA]"))
                    { 
                        typeOfData = "workwear";
                    }
                    else 
                    { 
                        System.out.println("item is not found");
                    }
                }

                else{
                    if(typeOfData.equals("electric"))
                    {
                        item = new ElectricTool();
                    }
                    else if(typeOfData.equals("handtool"))
                    {
                        item = new HandTool();
                    }
                    else if(typeOfData.equals("perishable"))
                    {
                        item = new Perishable(); 
                    }
                    else if(typeOfData.equals("workwear"))
                    {
                        item = new Workwear(); 
                    }
                    else 
                    { 
                        System.out.println("item is not found");
                    }
                    if (item != null)
                    {
                        item.readData(scan1);
                        storeItem(item);
                    }
                }
                scan1.close();
            }
        }
        scan.close();
    }

    /**
     * reads customer data
     */ 
    public void readCustomerData()
    { 
        Frame frame = null;
        FileDialog file = new FileDialog (frame, "Open", FileDialog.LOAD);
        file.setVisible(true);
        String filename = file.getFile();
        String directorypath = file.getDirectory();
        File myFile = new File(directorypath +filename);
        Scanner scan=null;
        try 
        { 
            scan = new Scanner(myFile);
        }
        catch (FileNotFoundException e){ 
            System.out.println("File not found" +myFile);
        }

        while (scan.hasNextLine())
        {
            String lineOfText = scan.nextLine().trim();
            if (!lineOfText.startsWith("//") && (!lineOfText.isEmpty()))
            { 
                Scanner scan1 = new Scanner(lineOfText).useDelimiter(",");
                Customer customer = new Customer();
                customer.readData(scan1);
                storeCustomer(customer);
                scan1.close();
            }
        }
        scan.close();
    }

    /**
     * reads ItemReservation data
     */ 
    public void readItemReservationData()
    { 
        Frame frame = null;
        FileDialog file = new FileDialog (frame, "Open", FileDialog.LOAD);
        file.setVisible(true);
        String filename = file.getFile();
        String directorypath = file.getDirectory();
        File myFile = new File(directorypath +filename);
        Scanner scan=null;
        try 
        { 
            scan = new Scanner(myFile);
        }
        catch (FileNotFoundException e){ 
            System.out.println("File not found" +myFile);
        }
        while (scan.hasNextLine())
        {
            String lineOfText = scan.nextLine().trim();
            if (!lineOfText.startsWith("//") && !lineOfText.isEmpty())
            { 
                Scanner scan1 = new Scanner(lineOfText).useDelimiter(",");
                Customer customer = new Customer();
                customer.readData(scan1);
                storeCustomer(customer);
                scan1.close();
            }
        }
        scan.close();
    }

    /**
     * Write data into the file.
     */
    public void writeItemReservationData(String fileName)
    {  
        try 
        {
            PrintWriter pWriter = new PrintWriter(fileName);
            for (ShopItemReservation shopItemReservation: itemReservationMap.values())
            { 
                shopItemReservation.writeData(pWriter);
            }
            pWriter.close();
        }

        catch (FileNotFoundException e){ 
            System.out.println("File not found" +fileName);
        }
    }

    /**
     * Write data into the file.
     */
    public void writeCustomerData(String fileName)
    {  
        try
        {
            PrintWriter pWriter = new PrintWriter(fileName);  
            for (Customer customer: customerMap.values())
            { 
                customer.writeData(pWriter);
            }
            pWriter.close();
        }
        catch (FileNotFoundException e){ 
            System.out.println("File not found" +fileName);
        }
    }
}

