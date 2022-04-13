import java.util.ArrayList;

/**
 * Branch Class for Company
 * @author Bahadir Etka Kilinc
 */
public class Branch{
    private final Company company;
    private int branchID;
    private String branchName;
    private final HybridList<Product> products;
    private final KWArrayList<Employee> employees;

    /**
     * Constructor for Branch
     * @param name Branch's Name
     * @param id Branch's ID
     * @param company Company to which the Branch is affiliated
     */
    public Branch(String name,int id, Company company){
        setBranchName(name);
        setBranchID(id);
        this.company = company;
        products = new HybridList<>();
        employees = new KWArrayList<>();
    }

    /**
     * Setter for Branch's ID
     * @param branchID Branch's ID
     */
    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    /**
     * Setter for Branch's Name
     * @param name Branch's Name
     */
    public void setBranchName(String name){
        this.branchName = name;
    }

    /**
     * Returns Branch's ID
     * @return Branch's ID
     */
    public int getBranchID(){
        return branchID;
    }

    /**
     * Returns Branch's Name
     * @return Branch's Name
     */
    public String getBranchName(){return branchName;}

    /**
     * Returns this Branch
     * @return Branch
     */
    public Branch getBranch(){
        return this;
    }

    /**
     *  Returns Company to which the Branch is affiliated
     * @return Company to which the Branch is affiliated
     */
    public Company getCompany(){return company;}

    /**
     * Returns Employees of Branch
     * @return Employees of Branch
     */
    public KWArrayList<Employee> getEmployees(){return employees;}

    /**
     * Return Products of Branch
     * @return Products of Branch
     */
    public HybridList<Product> getProducts(){return products;}

    /**
     *  Returns representation of Branch' Name and ID
     * @return Representation of Branch' Name and ID
     */
    public String toString(){
       return String.format("\n\t\tBranch Name : %s Branch ID : %d\n",getBranchName(),getBranchID());
    }
}
