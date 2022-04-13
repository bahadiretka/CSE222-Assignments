
/**
 * Company Class for hold and doing all adding,removing stuff. Implements AdminInterface, Employee Interface and CostumerInterface
 * @author Bahadir Etka Kilinc
 */
public class Company implements AdminInterface,EmployeeInterface,CostumerInterface{
    private String companyName;
    private final Admin administrator;
    private final MyContainer<String> SystemMessages;
    private final MyContainer<Costumer> costumers;
    private final MyContainer<Branch> branches;

    /**
     * Constructor for Company
     * @param companyName Name of Company
     * @param adminName Name of Admin
     * @param adminSurname Surname of Admin
     */
    public Company(String companyName, String adminName, String adminSurname){
        setCompanyName(companyName);
        administrator = new Admin(adminName,adminSurname,this);
        SystemMessages = new MyContainer<>();
        costumers = new MyContainer<>();
        branches = new MyContainer<>();
    }

    /**
     *  Company name setter
     * @param companyName Name of Company
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    /**
     * Getter for company name
     * @return returns Company Name
     */
    public String getCompanyName(){return companyName;}

    /**
     *  Getter for Admin
     * @return returns Admin of Company
     */
    public Admin getAdmin(){
        return administrator;
    }

    /**
     *  Adds Employee to Branch
     * @param name Employee's Name
     * @param Surname Employee's Surname
     * @param employeeID Employee's ID
     * @param branchID Employee's Branch
     */
    public void addEmployee(String name, String Surname, int employeeID, int branchID){
        for(int i=0; i<getNumberOfBranches(); i++){
            if(branches.at(i).getBranchID() == branchID) branches.at(i).getEmployees().insert(new Employee(name,Surname,employeeID,branches.at(i)));
        }
    }

    /**
     * Shows all branches in the Company
     */

    @Override
    public void showBranches() {
        for(int i=0; i<branches.size(); i++){
            System.out.println(branches.at(i));
        }
    }

    /**
     *  Deletes an Employee from a Branch
     * @param branchID Branch's ID
     * @param employeeID Employee's ID
     * @throws Exception If there is no Branch or Employee in Branch
     */
    public void deleteEmployee(int branchID, int employeeID) throws Exception{
        boolean f = false;
        for(int i=0; i < getBranch(branchID).getEmployees().size(); i++){
            if(getBranch(branchID).getEmployees().at(i).getEmployeeID() == employeeID){
                f = true;
                getBranch(branchID).getEmployees().erase(getBranch(branchID).getEmployees().at(i));
            }
        }
        if(!f) {
            throw new Exception("\nThere is no Employee!\n");
        }
    }

    /**
     *  Adds product to required Branch
     * @param name Product's Name
     * @param model Product's Model
     * @param color Product's Color
     * @param stockNumber Product's Stock Number
     * @param productID Product's ID
     * @param branchID ID of the Branch to be added
     */
    public void addProduct(String name,String model,String color,int stockNumber,int productID, int branchID){
        Product product = getProduct(productID,branchID);
        if(product != null && product.getProductID() == productID) product.setNumber(getProduct(productID,branchID).getNumber()+stockNumber);
        else getBranch(branchID).getProducts().insert(new Product(name,model,color,stockNumber,productID));
    }

    /**
     *  Deletes Product from required Branch
     * @param productID Product's ID
     * @param number Number of will be deleted Product
     * @param employeeID Employee's ID which is will delete Product
     * @param branchID Branch ID from will be deleted
     * @throws Exception If there is no Product,throws exception
     */
    public void deleteProduct(int productID,int number,int employeeID,int branchID) throws Exception{
        if(getProduct(branchID,productID).getNumber() >= number){
            getProduct(branchID,productID).setNumber(getProduct(branchID,productID).getNumber()-number);
            getEmployee(employeeID).addMessage(String.format("%d product deleted from product number %d(%s Branch)",number,productID,getBranch(branchID).getBranchName()));
        }
        else{
            throw new Exception("\nThere is no such Product!\n");
        }

    }

    /**
     *  Returns number of Branches
     * @return number of branches in the Company
     */
    public int getNumberOfBranches(){
        return branches.size();
    }

    /**
     *  Adds Branch to Company
     * @param branchName Branch's Name
     * @param branchID Branch's ID
     */
    public void addBranch(String branchName, int branchID){
        branches.insert(new Branch(branchName,branchID,this));
    }

    /**
     *  Deletes branch from Company
     * @param branchID Branch's ID
     * @throws Exception İf There is no Branch to delete, throws Exception
     */
    public void deleteBranch(int branchID) throws Exception{
        boolean f= false;
        for(int i=0; i<getNumberOfBranches(); i++){
            if(branches.at(i).getBranchID() == branchID){
                f=true;
                branches.erase(branches.at(i));
            }
        }
        if(!f) throw new Exception("\nThere is no Branch!\n");
    }

    /**
     *  Adds Costumer to Company
     * @param name Costumer's Name
     * @param Surname Costumer's Surname
     * @param info Costumer Info(Mail,Address,Phone)
     * @param costumerId Costumer's ID
     * @param password Costumer's Password
     */
    public void addCostumer(String name,String Surname, CostumerInfo info, int costumerId, int password){
        addMessage(String.format("%s %s registered to the system with a customer number %d",name,Surname,costumerId));
        costumers.insert(new Costumer(name,Surname,info,costumerId,password,this));
    }

    /**
     *  Returns if there is Costumer with Costumer's ID
     * @param costumerID Costumer's ID
     * @return required Costumer from Company
     */
    public Costumer getCostumer(int costumerID){
        for(int i=0; i<costumers.size(); i++){
            if(costumers.at(i).getCostumerID() == costumerID) return costumers.at(i);
        }
        return null;
    }

    /**
     *  Costumer gets own account with his/her ID and password
     * @param costumerID Costumer's ID
     * @param password Costumer's Password
     * @return Costumer from Company
     */
    public Costumer CostumerLogin(int costumerID, int password){
        for(int i=0; i<costumers.size(); i++) {
            if (costumers.at(i).getCostumerID() == costumerID && costumers.at(i).getPassword() == password)
                return costumers.at(i);
        }
        return null;
    }

    /**
     * Gets Employee with ID
     * @param employeeID Employee's ID
     * @return Employee from Branch
     */
    public Employee getEmployee(int employeeID){
        for(int i=0; i<getNumberOfBranches(); i++){
            for(int j=0; j<branches.at(i).getEmployees().size();j++){
                if(branches.at(i).getEmployees().at(j).getEmployeeID() == employeeID) return branches.at(i).getEmployees().at(j);
            }
        }
        return null;
    }

    /**
     * Adds order to Costumer from Branch
     * @param branchID   Branch's ID
     * @param productID  Product's ID
     * @param number     Number will be sold
     * @param costumerID Costumer's ID
     */
    public void addOrder(int branchID, int productID, int number,int costumerID){
        Product product = getProduct(branchID,productID);

        if(getBranch(branchID)!=null) {
            if (product.getNumber() >= number) {
                getCostumer(costumerID).getOrders().insert(new Product(product.getName(),product.getModel(),product.getColor(),number,product.getProductID()));
                product.setNumber(product.getNumber()-number);
                getBranch(branchID).getEmployees().at(0).addMessage(String.format("%d of the product number %d ID sold from %d Branch",number,productID,branchID));

            }
            else{
                product.setNumber(product.getNumber()+5);
                getBranch(branchID).getEmployees().at(0).addMessage(String.format(
                        "Since the product number %d ID is out of stock, 5 products have been added to %s Branch",productID,getBranch(branchID).getBranchName()));
            }
        }
    }

    /**
     *  Returns product from Branch
     * @param branchID Branch's ID
     * @param productID Product's ID
     * @return Product from Branch
     */
    public Product getProduct(int branchID,int productID){
        for(int i=0; i<getNumberOfBranches(); i++){
            for(int j=0; j<branches.at(i).getProducts().size(); j++){
                if(branches.at(i).getBranchID()== branchID && branches.at(i).getProducts().at(j).getProductID() == productID){
                    return branches.at(i).getProducts().at(j);
                }
            }
        }
        return null;
    }

    /**
     *  Returns Branch from Company with Branch's ID
     * @param branchID Branch's ID
     * @return Branch from Company
     */
    public Branch getBranch(int branchID){
        for(int i=0; i<getNumberOfBranches(); i++){
            if(branches.at(i).getBranchID() == branchID) return branches.at(i);
        }
        return null;
    }

    /**
     * Shows all Products in all Branches
     */
    public void showProducts(){
        for(int i=0; i<getNumberOfBranches(); i++){
            System.out.println(branches.at(i));
            for(int j=0; j<branches.at(i).getProducts().size(); j++){
                System.out.println(branches.at(i).getProducts().at(j));
            }
        }
    }

    /**
     *  Shows Products in required Branch
     * @param branchID Branch's ID
     */
    public void showProducts(int branchID){
        System.out.println(getBranch(branchID).getBranchName());
        for(int i=0; i<getBranch(branchID).getProducts().size();i++){
            System.out.println(getBranch(branchID).getProducts().at(i));
        }
    }

    /**
     *  Adds message to System Messages
     * @param message Message to add System Messages
     */
    public void addMessage(String message){
        SystemMessages.insert(message);
    }

    /**
     * Shows all System Messages
     */
    public void showMessages(){
        System.out.println("\n\t\tMessages to Administrator\n");

        for(int i=0; i<SystemMessages.size(); i++){
            System.out.println("-> "+SystemMessages.at(i));
        }
    }

    /**
     *  Search Product in All Branches with product name
     * @param productName Product's Name
     */
    public void searchProduct(String productName){
        for(int i=0; i<getNumberOfBranches(); i++){
            for(int j=0; j<branches.at(i).getProducts().size(); j++){
                if(branches.at(i).getProducts().at(j).getName().equals(productName)){
                    System.out.println(branches.at(i));
                    System.out.println(branches.at(i).getProducts().at(j));
                }
            }
        }
    }

    /**
     * Shows Costumer's orders with his/her ID
     * @param costumerId Costumer's ID
     */
    @Override
    public void showOrders(int costumerId) {
        MyContainer<Product> orders = getCostumer(costumerId).getOrders();
        if(orders.size() == 0){
            System.out.println("There is no order!");
        }
        else{
            for(int i=0; i<orders.size();i++){
                System.out.println(orders.at(i));
            }
        }
    }

    /**
     * Shows all employees in all Branches
     */
    public void showEmployees(){
        for(int i=0; i<getNumberOfBranches(); i++){
            System.out.println(branches.at(i).getBranchName());
            for(int j=0; j<branches.at(i).getEmployees().size(); j++)
                System.out.println(branches.at(i).getEmployees().at(j));
        }
    }

    /**
     *  Shows all information of Costumer with his/her ID
     * @param costumerID Costumer's ID
     */
    public void showCostumerInfo(int costumerID){
        System.out.println("\n\t\t\tCostumer Information\n");
        System.out.println(getCostumer(costumerID));
        int numOfOrders = getCostumer(costumerID).getOrders().size();
        if(numOfOrders == 0){
            System.out.println("There is no order!");
        }
        else {
            System.out.println("\t\t\tORDERS\n");
            for (int i = 0; i < numOfOrders; i++) {
                System.out.println(getCostumer(costumerID).getOrders().at(i));
            }
        }
    }
}
