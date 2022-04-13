

/**
 * Costumer class for Company extends Person implements Costumer Interface
 * @author Bahadir Etka Kilinc
 */
public class Costumer extends Person implements CostumerInterface{
    private final Company company;
    private int costumerId; // can be random
    private int password;
    private final CostumerInfo info;
    private final MyContainer<Product> orders;

    /**
     * Constructor for Costumer
     * @param name Costumer's Name
     * @param Surname Costumer's Surname
     * @param info Costumer's Information(Mail,Address,PhoneNum)
     * @param costumerId Costumer's ID
     * @param password Costumer's Password
     * @param company Company
     */
    public Costumer(String name ,String Surname, CostumerInfo info, int costumerId, int password, Company company){
        super(name,Surname);
        this.info = info;
        setPassword(password);
        setCostumerID(costumerId);
        this.company = company;
        orders = new MyContainer<>();
    }

    /**
     *  Setter for Costumer's ID
     * @param id Costumer's ID
     */
    public void setCostumerID(int id){
        costumerId = id;
    }

    /**
     * Setter for Costumer's password.
     * @param password Costumer's Password
     */
    public void setPassword(int password){
        this.password = password;
    }

    /**
     * Returns Costumer's ID
     * @return Costumer's ID
     */
    public int getCostumerID(){
        return costumerId;
    }

    /**
     * Returns Costumer's Password
     * @return Costumer's Password
     */
    public int getPassword(){
        return password;
    }

    /**
     * Returns Costumer's Information
     * @return Costumer's Information
     */
    public CostumerInfo getInfo(){return info;}

    /**
     * Returns Orders of Costumer
     * @return Orders of Costumer
     */
    public MyContainer<Product> getOrders(){
        return orders;
    }

    /**
     * Returns to the customer's registered company
     * @return Company
     */
    public Company getCompany(){return company;}

    /**
     * Adds order to Costumer(Shopping)
     * @param branchID  Branch's ID
     * @param productID  Product's ID
     * @param number     Number of Order
     * @param costumerID Costumer's ID
     */
    public void addOrder(int branchID,int productID, int number,int costumerID){
        company.addOrder(branchID,productID,number,getCostumerID());
    }

    /**
     * Shows all products in all Branches
     */
    public void showProducts(){
        company.showProducts();
    }

    /**
     * Searches and if there is/are product shows
     * @param productName Product Name
     */
    public void searchProduct(String productName){
        company.searchProduct(productName);
    }

    /**
     *Shows Costumer's previous orders
     * @param costumerId Costumer's ID
     */
    public void showOrders(int costumerId){
        getCompany().showOrders(costumerId);
    }

    /**
     * Shows all information about Costumer
     * @param costumerId Costumer's ID
     */
    public void showCostumerInfo(int costumerId){
        company.showCostumerInfo(costumerId);
    }

    /**
     * Returns String representation of Costumer
     * @return String representation of Costumer
     */
    public String toString(){
        return String.format("%s\tID : %d\tPassword :%d\n%s",super.toString(),getCostumerID(),getPassword(),getInfo());
    }
}
