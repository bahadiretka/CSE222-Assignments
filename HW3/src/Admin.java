
/**
 * Administrator Class for Company
 * @author Bahadir Etka Kilinc
 */
public class Admin extends Person implements AdminInterface{
    private final Company company;

    /**
     * Constructor for Administrator
     * @param name Admin's Name
     * @param surname Admin's Surname
     * @param company Admin's Company
     */
    public Admin(String name, String surname, Company company){
        super(name,surname);
        this.company = company;
    }

    /**
     * Shows all information about Branches
     */
    public void showBranches(){
        company.showBranches();
    }

    /**
     * Adds Branch to Company
     * @param name Branch's Name
     * @param branchID Branch's ID
     */
    public void addBranch(String name, int branchID){
        company.addBranch(name,branchID);
    }

    /**
     * Deletes Branch from Company with Branch ID
     * @param branchID Branch's ID
     * @throws Exception if there is no branch to delete in Company,throws Exception
     */
    public void deleteBranch(int branchID) throws Exception{
        company.deleteBranch(branchID);
    }

    /**
     * Adds Employee to Branch
     * @param name Employee's Name
     * @param Surname Employee's Surname
     * @param id Employee's ID
     * @param branchID Branch ID
     */
    public void addEmployee(String name, String Surname, int id, int branchID){
        company.addEmployee(name,Surname,id,branchID);
    }

    /**
     * Shows all employees with information
     */
    public void showEmployees(){
        company.showEmployees();
    }

    /**
     *  Deletes Employee from Branch
     * @param branchID Branch's ID
     * @param id Employee's ID
     * @throws Exception if there is no employee, throws Exception
     */
    public void deleteEmployee(int branchID, int id) throws Exception{
        company.deleteEmployee(branchID,id);
    }

    /**
     *  Returns Employee from Branch with Employee ID
     * @param employeeID Employee's ID
     * @return Employee from Branch
     */
    public Employee getEmployee(int employeeID){
        return company.getEmployee(employeeID);
    }

    /**
     * Shows System Messages
     */
    public void showMessages(){
        company.showMessages();
    }

    /**
     * Shows all products in Company
     */
    public void showProducts(){company.showProducts();}
}
