
/**
 * Generic Employee Interface
 * @author Bahadir Etka Kilinc
 */
interface EmployeeInterface{
    void addProduct(String name, String model, String color, int number, int productID,int branchID);
    void deleteProduct(int productID, int number,int employeeID,int branchID) throws Exception;
    void addCostumer(String name,String Surname, CostumerInfo info, int costumerId, int password);
    void showCostumerInfo(int costumerID);
    void addOrder(int branchID,int productID,int number,int costumerID);
    void addMessage(String message);
}
