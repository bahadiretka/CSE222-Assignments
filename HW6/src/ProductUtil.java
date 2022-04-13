import java.util.*;
import java.io.*;

/**
 * Product's Utilities class that does the operation about reading file,writing file etc.
 * @author Bahadir Etka Kilinc
 */
public class ProductUtil{
	/**
	 * Reads products from source file
	 */
	public static void readProductsFile(){
		try {
			NameComparator comp = new NameComparator();
			PriorityQueue<Product> products = new PriorityQueue<Product>(comp);
			BufferedReader reader = new BufferedReader(new FileReader("e-commerce-samples.csv"));
			String line;
			boolean flag = false;
			
			while ((line = reader.readLine()) != null) {
				if (flag == false) {
					flag = true;
					continue;
				}
				String[] pStr = line.split(";");
				products.add(new Product(pStr[0],pStr[1], pStr[2], pStr[3], pStr[4], pStr[5], pStr[6]));
			}
			File outputFile = new File("Products.txt");
			FileWriter fileWriter = new FileWriter(outputFile);
			while (!products.isEmpty())
				fileWriter.write(products.poll().toString());
			fileWriter.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Searches product with name
	 * @param searchedProducts hold products
	 * @param str name
	 * @param option sorting option
	 */
	public static void searchOnFile(ArrayList<Product> searchedProducts, String str, int option) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("Products.txt"));
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] pStr = line.split(";");
				
				if (pStr[1].contains(str) || pStr[5].contains(str)) 
					searchedProducts.add(new Product(pStr[0],pStr[1], pStr[2], pStr[3], pStr[4], pStr[5], pStr[6]));
				
			}
			sorter(searchedProducts, option);
			reader.close();
			for (int i = 0; i < searchedProducts.size(); ++i)
				System.out.println(searchedProducts.get(i));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sorts searched products with three different sorting algorithms
	 * @param searchedProducts searched products
	 * @param option sorting option
	 */
	private static void sorter(ArrayList<Product> searchedProducts, int option) {
		if (option == 1)
			MergeSort.sort(searchedProducts);
		else if (option == 2)
			QuickSort.sort(searchedProducts);
		else if (option == 3)
			HeapSort.sort(searchedProducts);
	}

	/**
	 * Sorts and prints sorted products
	 * @param searchedProducts searched products
	 * @param option sorting option
	 */
	public static void searchSort(ArrayList<Product> searchedProducts, int option) {
		sorter(searchedProducts, option);
		for (int i = 0; i < searchedProducts.size(); ++i)
			System.out.println(searchedProducts.get(i));
	}

	/**
	 * searches products with a trader name
	 * @param str trader name
	 */
	public static void searchFile(String str) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("Products.txt"));
			String line;
			TreeSet<Product> myTree = new TreeSet<>();
			while ((line = bufferedReader.readLine()) != null) {
				String[] pStr = line.split(";");
				if (pStr[6].contains(str)) 
					myTree.add(new Product(pStr[0],pStr[1], pStr[2], pStr[3], pStr[4], pStr[5], pStr[6]));
			}
			bufferedReader.close();
			while (!myTree.isEmpty())
				System.out.println(myTree.pollFirst().toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * filters price with a bound
	 * @param searchedProducts searched products
	 * @param choice lower or upper option
	 * @param bound upper or lower bound
	 */
	public static void priceFilter(ArrayList<Product> searchedProducts, int choice, int bound){
		sorter(searchedProducts, 2);
		for (int i = 0; i < searchedProducts.size(); ++i) {
			int price = Integer.parseInt(searchedProducts.get(i).getPrice().trim());
			if ((choice == 1 && price > bound) || (choice == 2 && price < bound))
				System.out.println(searchedProducts.get(i));
		}	
	}

	/**
	 * filters price with lower and upper bound
	 * @param searchedProducts searched products
	 * @param choice filtering option
	 * @param boundLower lower bound
	 * @param boundUpper upper bound
	 */
	public static void priceFilter(ArrayList<Product> searchedProducts, int choice, int boundLower, int boundUpper){
		sorter(searchedProducts, 2);
		for (Product searchedProduct : searchedProducts) {
			int price = Integer.parseInt(searchedProduct.getPrice().trim());
			if (choice == 3 && price > boundLower && price < boundUpper)
				System.out.println(searchedProduct);
		}
	}

	/**
	 * Creates and writes the products to file
	 * @param products products list that will be wrote
	 * @param file file object
	 */
	private static void createFile(List<Product> products, File file) {
		try {
			FileWriter fileWriter = new FileWriter(file);
			for (Product product : products) fileWriter.write(product.toString());
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * adds a product to products.txt
	 * @param product the product will be added
	 * @param file file object
	 */
	public static void addProduct(Product product, String file) {
		try {
			FileWriter fileWriter = new FileWriter(file, true);
			fileWriter.write(product.toString());
			fileWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * Deletes a product from products.txt
	 * @param productId id of product which will be deleted
	 */
	public static void deleteProduct(String productId){
		try {
			File inputFile = new File("Products.txt");
			File tempFile = new File("Temp.txt");
			LinkedList<Product> searchedProducts = new LinkedList<>();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				String[] pStr = line.split(";");
				if (!pStr[0].equals(productId))
					searchedProducts.add(new Product(pStr[0],pStr[1], pStr[2], pStr[3], pStr[4], pStr[5], pStr[6]));
			}
			bufferedReader.close();
			createFile(searchedProducts, tempFile);
			inputFile.delete();
			tempFile.renameTo(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * edits a product with its id
	 * @param id if of product
	 */
	public static void editProduct(String id) {
		try {
			File inputFile = new File("Products.txt");
			File tempFile = new File("Temp.txt");
			ArrayList<Product> searchedProducts = new ArrayList<>();
			BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile));
			String line;
			Scanner scanner = new Scanner(System.in);
			boolean flag = false;
			
			while ((line = bufferedReader.readLine()) != null) {
				
				String[] pStr = line.split(";");
				
				if (pStr[0].equals(id)) {
					flag = true;
					System.out.printf("Which feature of the product you want to change?\n");
					System.out.println("1) Name");
					System.out.println("2) Price");
					System.out.println("3) Discounted price");
					System.out.println("4) Description");
					int choice = Integer.parseInt(scanner.nextLine());

					if (1 == choice) {
						System.out.println("Enter the new name: ");
						String s = scanner.nextLine();
						pStr[1] = s;
					}
					else if (2 == choice) {
						System.out.println("Enter the new price: ");
						String price = scanner.nextLine();
						pStr[3] = price;
					}
					else if (3 == choice) {
						System.out.println("Enter the new discounted price: ");
						String price = scanner.nextLine();
						pStr[4] = price;
					}
					else if (4 == choice) {
						System.out.println("Enter the new description: ");
						String description = scanner.nextLine();
						pStr[5] = description;
					}					
				}
				searchedProducts.add(new Product(pStr[0],pStr[1], pStr[2], pStr[3], pStr[4], pStr[5], pStr[6]));
			}
			bufferedReader.close();
			if (!flag) {
				System.out.println("There is no such product with this ID");
				return;
			}
			sorter(searchedProducts, 1);
			createFile(searchedProducts, tempFile);
			inputFile.delete();
			tempFile.renameTo(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Filters searched products with a category
	 * @param searchedProducts searched products
	 * @param category category which will be searched
	 */
	public static void filterCategory(ArrayList<Product> searchedProducts, String category) {
		for (int i = 0; i < searchedProducts.size(); ++i) 
			if (searchedProducts.get(i).getCategory().contains(category))
				System.out.println(searchedProducts.get(i));
		
	}

	/**
	 * Adds an order to costumer to Orders.txt
	 * @param searchedProducts searched product
	 * @param productId product id which will be ordered
	 * @param userId id of costumer
	 */
	public static void addOrder(ArrayList<Product> searchedProducts, String productId, String userId) {
		try {
			FileWriter fw = new FileWriter("Orders.txt", true);
			
			for (int i = 0; i < searchedProducts.size(); ++i) {
				Product p = searchedProducts.get(i); 
				if (p.getId().equals(productId)) {
					System.out.println("The order was successful");
					fw.write(String.format("%s %s %s\n", productId, userId, p.getTrader()));
					fw.close();
					return;
				}
			}
			fw.close();
			System.out.println("There is no product with inputted ID.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *	displays orders (if there is) from Orders.txt
	 * @param userId id of trader
	 */
	public static void displayOrders(String userId) {
		try {
			File file = new File("Orders.txt");
			if (!file.exists()) {
				System.out.println("Orders.txt doesn't exist.");
				return;
			}
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			
			System.out.println("Product ID\tUser ID\tTrader Name");
			
			while ((line = br.readLine()) != null) {
				String[] oStr = line.split(" ");
				String id = UserUtil.getUserID(oStr[2]);
				if (id != null && id.equals(userId))
					System.out.println(line);
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * trader accepts the order from costumer
	 * @param userId id of trader
	 * @param productId id of product
	 */
	public static void acceptOrder(String userId, String productId){
		try {
			File file = new File("Orders.txt");
			if (!file.exists()) {
				System.out.println("Orders.txt doesn't exist.");
				return;
			}
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {
				String[] oStr = line.split(" ");
				String id = UserUtil.getUserID(oStr[2]);
				if (id != null && id.equals(userId) && oStr[0].equals(productId)) {
					deleteProduct(productId);
					System.out.println("Product sold successfully");
					break;
				}
			}
			
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * trader cancels the order from costumer
	 * @param productId id of product
	 */
	public static void cancelOrder(String productId){
		try {
			File file = new File("Orders.txt");
			File tempFile = new File("Temp.txt");
			if (!file.exists()) {
				System.out.println("Orders.txt doesn't exist.");
				return;
			}
			ArrayList<String> orderProducts = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;

			while ((line = br.readLine()) != null) {
				String[] oStr = line.split(" ");
				if (!oStr[0].equals(productId))
					orderProducts.add(line);
			}
			FileWriter setObj = new FileWriter(tempFile);
			
			for (int i = 0; i < orderProducts.size(); ++i) 
				setObj.write(orderProducts.get(i));

			setObj.close();
			br.close();
			file.delete();
			tempFile.renameTo(file);
			System.out.println("Ordered product cancelled");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}