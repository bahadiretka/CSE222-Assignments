
public class Driver {
    public static void main(String[] args) throws Exception {

        Company company = new Company("abk", "Bahadir", "Kilinc");
        Admin admin = company.getAdmin();

        /* admin creates branches */
        System.out.println("\n\n\t\t\tADMIN CREATES BRANCHES\n\n");
        admin.addBranch("Maltepe",1997);
        admin.addBranch("Kartal",1998);
        admin.addBranch("Pendik",1999);
        admin.addBranch("Tuzla",2000);
        admin.showBranches();
        /* admin creates and adds employees to branches */
        System.out.println("\n\n\t\t\tADMIN CREATES AND ADDS EMPLOYEES TO BRANCHES\n\n");
        admin.addEmployee("Selin","Bakan",100,1997);
        admin.addEmployee("Aslan","Kalin",101,1997);
        admin.addEmployee("Melek","Usta",102,1998);
        admin.addEmployee("Yeliz","Derin",103,1998);
        admin.addEmployee("Ezgi","Salman",104,1999);
        admin.addEmployee("Cem","Canal",105,1999);
        admin.addEmployee("Utku","Tok",106,2000);
        admin.addEmployee("Kaan","Talas",107,2000);
        admin.showEmployees();
        /* Employees adds products to branches */
        System.out.println("\n\n\t\t\tEMPLOYEES ADDS PRODUCTS TO BRANCHES\n");
        addProductToBranches(admin);
        admin.showProducts();
        /* Creating a costumer and employee adds this costumer to company sistem */
        System.out.println("\n\n\t\t\tEMPLOYEES ADDS COSTUMERS TO COMPANY\n");

        CostumerInfo info1 = new CostumerInfo("@gmail","istanbul",789);
        Employee employee = admin.getEmployee(107);
        employee.addCostumer("Nazan","Cam",info1,1234,1234);
        employee.showCostumerInfo(1234);

        /* Creating a costumer and employee adds this costumer to company sistem */
        CostumerInfo info2 = new CostumerInfo("@gmail","ankara",567);
        employee.addCostumer("Hazal","Erguvan",info2,4567,4567);
        employee.showCostumerInfo(4567);
        /* costumers try to login the system and shopping */
        System.out.println("\n\n\t\t\tCOSTUMERS TRY TO LOGIN AND SHOPPING");
        try {
            Costumer costumer1 = company.CostumerLogin(1234,1234);
            System.out.println("\n\n\t\t\tCOSTUMER SEARCHS PRODUCT\n");
            costumer1.searchProduct("Office Desk");
            System.out.println("\n\n\t\t\tCOSTUMER ADDS ORDER\n");

            costumer1.addOrder(1997,304,2,costumer1.getCostumerID());
            costumer1.showOrders(costumer1.getCostumerID());
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        System.out.println("\n\n\t\t\tCOSTUMER TRY TO LOGIN WRONG ID AND PASSWORD");
        try{
            Costumer costumer2 = company.CostumerLogin(333,333);
            costumer2.searchProduct("Office Chair");
        }
        catch (Exception e){
            System.out.println("\n"+e.toString());
        }

        System.out.println("\n\n\t\tBEFORE DELETING AN EMPLOYEE\n");
        /* before deleting employee. */
        admin.showEmployees();
        /* admin deletes an employee. */
        admin.deleteEmployee(1999,105);
        System.out.println("\n\n\t\tAFTER DELETING AN EMPLOYEE\n");
        /* after deleting employee */
        admin.showEmployees();
        /* employee gets costumer information.*/
        System.out.println("\n\n\t\t\tEMPLOYEE GETS COSTUMER INFORMATION\n");
        employee.showCostumerInfo(1234);

        System.out.println("\n\n\t\t\tADMIN TRY TO DELETE AN EMPLOYEE WHICH IS NOT IN THE COMPANY");
        try{
            admin.deleteEmployee(1999,505);
        }
        catch (Exception e){
            System.out.println("\n"+e.toString());
        }
        System.out.println("\n\n\t\t\tEMPLOYEE GETS PRODUCTS WHERE HE/SHE WORKS\n");
        employee.showProducts();
        System.out.println("\n\n\t\t\tEMPLOYEE TRYING TO DELETE PRODUCTS WHICH IS NOT VALID\n");

        try{
            employee.deleteProduct(601,20,employee.getEmployeeID(),employee.getBranch().getBranchID());
        }
        catch (Exception e){
            System.out.println("\n"+e.toString());
        }
        System.out.println("\n\n\t\t\tEMPLOYEE SELLS A PRODUCT TO COSTUMER\n");
        employee.addOrder(employee.getBranch().getBranchID(),511,3,1234);
        employee.showCostumerInfo(1234);
        System.out.println("\n\n\t\t\tBEFORE ADMIN DELETES A BRANCH\n");
        admin.showBranches();
        System.out.println("\n\n\t\t\tAFTER ADMIN DELETES A BRANCH\n");
        admin.deleteBranch(1998);
        admin.showBranches();

        System.out.println("\n\n\t\t\tADMIN GETS MESSAGES FROM COMPANY\n");
        admin.showMessages();

        TestPolymorphism(admin);


    }
    public static void addProductToBranches(Admin admin){
        Employee employee = admin.getEmployee(100);
        /* employees adds product to Branches */
        employee.addProduct("Office Chair","Tunn","Sienna",5,200,employee.getBranch().getBranchID());
        employee.addProduct("Office Chair","Delta","Maroon",4,201,employee.getBranch().getBranchID());
        employee.addProduct("Office Chair","Maxi","Rosewood",3,202,employee.getBranch().getBranchID());
        employee.addProduct("Office Cabinet","Orbit","White",2,605,employee.getBranch().getBranchID());
        employee.addProduct("Office Cabinet","Ridge","Brown",3,606,employee.getBranch().getBranchID());
        employee.addProduct("Office Cabinet","Slime","Black",2,607,employee.getBranch().getBranchID());
        employee.addProduct("Office Chair","Tagix","Azure",2,203,employee.getBranch().getBranchID());
        employee.addProduct("Book Case","Fallacy","Sienna",6,509,employee.getBranch().getBranchID());
        employee.addProduct("Office Desk","Rio","Brown",9,304,employee.getBranch().getBranchID());

        employee = admin.getEmployee(103);

        employee.addProduct("Book Case","Frail","Brown",4,505,employee.getBranch().getBranchID());
        employee.addProduct("Book Case","Trace","Black",4,506,employee.getBranch().getBranchID());
        employee.addProduct("Meeting Table","Chase","Maroon",2,408,employee.getBranch().getBranchID());
        employee.addProduct("Meeting Table","Stem","Brown",4,409,employee.getBranch().getBranchID());
        employee.addProduct("Office Desk","Plan","Brown",5,300,employee.getBranch().getBranchID());
        employee.addProduct("Office Desk","Link","Azure",6,301,employee.getBranch().getBranchID());
        employee.addProduct("Office Cabinet","Bold","Azure",3,602,employee.getBranch().getBranchID());
        employee.addProduct("Office Cabinet","Hurl","White",4,603,employee.getBranch().getBranchID());
        employee.addProduct("Office Desk","Forma","Maroon",7,302,employee.getBranch().getBranchID());
        employee.addProduct("Office Chair","Carot","Magenta",6,204,employee.getBranch().getBranchID());
        employee.addProduct("Meeting Table","Goal","Brown",3,401,employee.getBranch().getBranchID());
        employee.addProduct("Office Cabinet","Dire","Azure",5,609,employee.getBranch().getBranchID());

        employee = admin.getEmployee(104);

        employee.addProduct("Office Cabinet","Wind","White",5,610,employee.getBranch().getBranchID());
        employee.addProduct("Meeting Table","Union","White",2,402,employee.getBranch().getBranchID());
        employee.addProduct("Meeting Table","Depth","Azure",4,403,employee.getBranch().getBranchID());
        employee.addProduct("Book Case","Anger","Brown",4,500,employee.getBranch().getBranchID());
        employee.addProduct("Book Case","Copper","Black",3,501,employee.getBranch().getBranchID());
        employee.addProduct("Meeting Table","Guest","Black",2,400,employee.getBranch().getBranchID());
        employee.addProduct("Meeting Table","Art","Maroon",3,404,employee.getBranch().getBranchID());
        employee.addProduct("Office Cabinet","Rise","Brown",4,608,employee.getBranch().getBranchID());
        employee.addProduct("Office Cabinet","Bake","Black",4,611,employee.getBranch().getBranchID());
        employee.addProduct("Book Case","Force","Blue",5,510,employee.getBranch().getBranchID());
        employee.addProduct("Book Case","Brick","Blue",3,504,employee.getBranch().getBranchID());
        employee.addProduct("Book Case","Prose","Maroon",3,502,employee.getBranch().getBranchID());


        employee = admin.getEmployee(107);

        employee.addProduct("Office Cabinet","Bleed","Black",4,600,employee.getBranch().getBranchID());
        employee.addProduct("Office Cabinet","Which","Brown",3,601,employee.getBranch().getBranchID());
        employee.addProduct("Office Desk","Mapa","Black",8,303,employee.getBranch().getBranchID());
        employee.addProduct("Office Desk","Rio","Brown",9,304,employee.getBranch().getBranchID());
        employee.addProduct("Office Cabinet","Graze","Brown",4,604,employee.getBranch().getBranchID());
        employee.addProduct("Office Chair","Fera","Sienna",4,205,employee.getBranch().getBranchID());
        employee.addProduct("Office Chair","Mira","Maroon",3,206,employee.getBranch().getBranchID());
        employee.addProduct("Meeting Table","Blame","Black",3,405,employee.getBranch().getBranchID());
        employee.addProduct("Meeting Table","Sand","White",2,406,employee.getBranch().getBranchID());
        employee.addProduct("Meeting Table","Touch","Azure",3,407,employee.getBranch().getBranchID());
        employee.addProduct("Book Case","Tacky","Maroon",4,508,employee.getBranch().getBranchID());
        employee.addProduct("Office Chair","Carot","Magenta",6,204,employee.getBranch().getBranchID());
        employee.addProduct("Book Case","Tree","Azure",6,511,employee.getBranch().getBranchID());
        employee.addProduct("Book Case","Comb","Azure",4,503,employee.getBranch().getBranchID());
        employee.addProduct("Book Case","Vest","Azure",4,507,employee.getBranch().getBranchID());

    }
    public static void TestPolymorphism(Person person){
        person.showProducts();
    }
}
