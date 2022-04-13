
/**
 * Generic Abstract Class for Person
 * @author Bahadir Etka Kilinc
 */
public abstract class Person{
    private String Name;
    private String Surname;

    /**
     * Constructor for Person
     * @param Name Person's Name
     * @param Surname Person's Surname
     */
    public Person(String Name,String Surname){
        setName(Name);
        setSurname(Surname);
    }

    /**
     * Setter for Person's Name
     * @param Name Person's Name
     */
    public void setName(String Name){
        this.Name = Name; 
    }

    /**
     * Setter for Person's Surname
     * @param Surname Person's Surname
     */
    public void setSurname(String Surname){
        this.Surname = Surname; 
    }

    /**
     * Returns Person's Name
     * @return Person's Name
     */
    public String getName(){
        return Name;
    }

    /**
     * Returns Person's Surname
     * @return Person's Surname
     */
    public String getSurname(){
        return Surname;
    }

    /**
     * Shows all products in all company
     * Abstract method for all persons
     */
    public abstract void showProducts();

    /**
     * Returns representation of Person
     * @return representation of Person
     */
    public String toString(){
        return String.format("Name : %s\tSurname : %s", getName(),getSurname());
    }
}

