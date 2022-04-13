import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * User utilizes class
 * @author Bahadir Etka Kilinc
 */
public class UserUtil {
	private static Hashtable<String, String> users = new Hashtable<>();

	/**
	 * Checks if the user is contains or not
	 * @param id id of user
	 * @param password password of user
	 * @return returns role of user
	 */
	public static int userLogin(String id, String password) {
		try {
			String s = users.get(id);
			if (s != null && s.equals(password)) {
				Scanner in = new Scanner(new FileReader("Users.txt"));
				
				while (in.hasNextLine()) {
					String line = in.nextLine();
					String[] temp = line.split(" "); 
					if (id.equals(temp[0])) {
						if (temp[2].equals("Customer"))
							return 1;
						else if (temp[2].equals("Trader"))
							return 2;
					}
				}
			} 
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * reads user from Users.txt
	 */
	public static void readUsersFile() {
		try {
			Scanner in = new Scanner(new FileReader("Users.txt"));
			
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] temp = line.split(" "); 
				users.put(temp[0], temp[1]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * gets user id with name
	 * @param name name of user
	 * @return returns id of user
	 */
	public static String getUserID(String name) {
		try {
			Scanner in = new Scanner(new FileReader("Users.txt"));
			
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] temp = line.split(" "); 
				if (temp[3].equals(name))
					return temp[0];
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * gets name of user with its id
	 * @param id id of user
	 * @return returns name of user
	 */
	public static String getUserName(String id) {
		try {
			Scanner in = new Scanner(new FileReader("Users.txt"));
			
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String[] temp = line.split(" "); 
				if (temp[0].equals(id))
					return temp[3];
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}