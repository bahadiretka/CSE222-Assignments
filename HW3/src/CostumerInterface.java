
/**
 * Generic Costumer Interface
 * @author Bahadir Etka Kilinc
 */
public interface CostumerInterface{
    void addOrder(int branchID,int productID, int number,int costumerID);
    void showProducts();
    void searchProduct(String productName);
    void showOrders(int costumerId);
    void showCostumerInfo(int costumerId);
}
