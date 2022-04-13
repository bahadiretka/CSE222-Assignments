
/**
 * CostumerInfo Class for hold Costumer Information
 * @author Bahadir Etka Kilinc
 */
public class CostumerInfo {
    private String email;
    private String address;
    private int phoneNumber;

    /**
     * Constructor for CostumerInfo
     * @param email Mail
     * @param address Address
     * @param phoneNumber Phone Number
     */
    public CostumerInfo(String email,String address, int phoneNumber){
        setMail(email);
        setAddress(address);
        setPhoneNum(phoneNumber);
    }

    /**
     * Setter for Mail
     * @param mail Mail
     */
    public void setMail(String mail){
        this.email = mail;
    }

    /**
     * Setter for Address
     * @param address Address
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * Setter for Phone Number
     * @param num Phone Number
     */
    public void setPhoneNum(int num){
        phoneNumber = num;
    }

    /**
     * Returns Mail
     * @return Mail
     */
    public String getMail(){
        return email;
    }

    /**
     * Returns Address
     * @return Address
     */
    public String getAddress(){return address;}

    /**
     * Returns Phone Number
     * @return Phone Number
     */
    public int getPhoneNumber(){
        return phoneNumber;
    }

    /**
     * Returns representation of Costumer Information
     * @return representation of Costumer Information
     */
    public String toString(){
        return String.format("Mail : %s\t Address : %s\tPhone Number : %d\n",getMail(),getAddress(),getPhoneNumber());
    }
}

