
/**
 * Generic Product Class
 * @author Bahadir Etka Kilinc
 */
public class Product {
    private int productID;
    private String name;
    private String model;
    private String color;
    private int numberOfProduct;
    /**
     * Constructor for Product
     * @param name Product's Name
     * @param model Product's Model
     * @param color Product's Color
     * @param number Product's Stock Number
     * @param ID Product's ID
     */
    public Product(String name, String model, String color, int number, int ID){
        setProductID(ID);
        setName(name);
        setModel(model);
        setColor(color);
        setNumber(number);
    }
    /**
     * Setter for Product's ID
     * @param ID Product's ID
     */
    public void setProductID(int ID){
        this.productID = ID;
    }

    /**
     * Setter for Product's Name
     * @param name Product's Name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Setter for Product's Model
     * @param model Product's Model
     */
    public void setModel(String model){
        this.model = model;
    }

    /**
     * Setter for Product's Color
     * @param color Product's Color
     */
    public void setColor(String color){
        this.color = color;
    }

    /**
     * Setter for Product's Stock Number
     * @param num Product's Stock Number
     */
    public void setNumber(int num){
        this.numberOfProduct = num;
    }

    /**
     * Returns Product's ID
     * @return Product's ID
     */
    public int getProductID(){
        return productID;
    }

    /**
     * Returns Product's Name
     * @return Product's Name
     */
    public String getName(){
        return name;
    }

    /**
     * Returns Product's Model
     * @return Product's Model
     */
    public String getModel(){
        return model;
    }
    /**
     * Returns Color of Product
     * @return Color of Product
     */
    public String getColor(){
        return color;
    }

    /**
     * Returns Stock Number of Product
     * @return Stock Number of Product
     */
    public int getNumber(){
        return numberOfProduct;
    }

    /**
     * Returns representation of Product
     * @return representation of Product
     */
    public String toString(){
        return String.format("Product Name : %s\tModel : %s\t Color : %s\t Stock No : %d\tProduct ID : %d", getName(),getModel(),getColor(),getNumber(),getProductID());
    }
}
