
/**
 * Employee Class for Branch's Employees. Extends Person implements EmployeeInterface
 *
 * @author Bahadir Etka Kilinc
 */
public class Employee extends Person implements EmployeeInterface{
    private int employeeID;
    private final Branch branch;

    /**
     * Constructor for Employee
     *
     * @param name       Employee's Name
     * @param surname    Employee's Surname
     * @param employeeID Employee's ID
     * @param branch     Branch where the employee is affiliated
     */
    public Employee(String name, String surname,int employeeID, Branch branch){
        super(name,surname);
        setEmployeeID(employeeID);
        this.branch = branch;
    }

    /**
     * Returns Branch where the employee is affiliated
     *
     * @return Branch where the employee is affiliated
     */
    public Branch getBranch(){return branch;}

    /**
     * Setter for Employee's ID
     *
     * @param id Employee's ID
     */
    public void setEmployeeID(int id){
        this.employeeID = id;
    }

    /**
     * Returns Employee's ID
     *
     * @return Employee 's ID
     */
    public int getEmployeeID(){return employeeID;}

    /**
     *  Adds Product to Branch
     * @param name name of product
     * @param model model of product
     * @param color color of product
     * @param number stock number of product
     * @param productID ID of product
     * @param branchID ID of the Branch to which product will be added
     */
    public void addProduct(String name, String model, String color, int number, int productID,int branchID){
        getBranch().getBranch().getCompany().addProduct(name, model, color, number,productID,branchID);
    }

    /**
     *  Deletes product from Branch
     * @param productID ID of product
     * @param number number of product to will be deleted
     * @param employeeID employee ID for inform company administrator
     * @param branchID branch ID from which product will be deleted
     * @throws Exception if there is no product, throws Exception
     */
    public void deleteProduct(int productID, int number,int employeeID,int branchID) throws Exception{
        getBranch().getCompany().deleteProduct(productID,number,employeeID,branchID);
    }

    /**
     * Show all products in this Branch
     */
    public void showProducts(){
        getBranch().getCompany().showProducts(branch.getBranchID());
    }

    /**
     * Adds Costumer to Company
     * @param name name of costumer
     * @param Surname surname of costumer
     * @param info information of costumer
     * @param costumerId ID of costumer
     * @param password password of costumer
     */
    public void addCostumer(String name,String Surname, CostumerInfo info, int costumerId, int password){
        getBranch().getCompany().addCostumer(name,Surname,info,costumerId,password);
    }

    /**
     * Shows all information about Costumer with his/her ID number
     * @param costumerID Costumer's ID
     */
    public void showCostumerInfo(int costumerID){
        branch.getCompany().showCostumerInfo(costumerID);
    }

    /**
     * An employee sells a product to Costumer
     * @param branchID branch's ID
     * @param productID product's ID
     * @param number number of product which will be sold
     * @param costumerID ID of the customer to whom the product will be sold
     */
    public void addOrder(int branchID,int productID,int number,int costumerID){
        getBranch().getCompany().addOrder(branch.getBranchID(),productID,number,costumerID);
    }

    /**
     * Adds String Message to System Messages
     * @param message Message
     */
    public void addMessage(String message){
        branch.getCompany().addMessage(message);
    }

    /**
     * Returns String representation of Employee
     * @return String representation of Employee
     */
    public String toString(){
        return String.format("%s EmployeeID : %d",super.toString(),getEmployeeID());
    }
}
