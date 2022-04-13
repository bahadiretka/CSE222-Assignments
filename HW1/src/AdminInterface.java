
/**
 * Generic Administrator Interface
 * @author Bahadir Etka Kilinc
 */
public interface AdminInterface{
    void addBranch(String name,int branchID);
    void deleteBranch(int branchID) throws Exception;
    void addEmployee(String name, String Surname, int id, int branchID);
    void deleteEmployee(int branchID, int id) throws Exception;
    void showBranches();
    void showEmployees();
    Employee getEmployee(int employeeID);
    void showMessages() ;
}
