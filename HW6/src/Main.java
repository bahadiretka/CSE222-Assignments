import java.util.ArrayList;
import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		displayMenu();
	}
	public static void displayMenu() {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		while (true) {
			System.out.print("Welcome to E-Shopping application\n");
			System.out.print("User Id:(Exit-> -1) ");
			String id = scanner.nextLine();

			if (id.equals("-1"))
				break;
			System.out.print("Password: ");
			String password = scanner.nextLine();
			UserMenu(id, password);
		}
		scanner.close();
	}
	public static void UserMenu(String id, String password){
		UserUtil.readUsersFile();
		int user = UserUtil.userLogin(id, password);

		if (user != 0)
			System.out.printf("Welcome %s\n\n", UserUtil.getUserName(id));
		else {
			System.out.print("User could not found!\n\n");
			return;
		}
		ProductUtil.readProductsFile();
		if (user == 2)
			TraderMenu(id);
		else
			CustomerMenu(id);
	}

	public static void TraderMenu(String userID){
		int choice;
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("1) Add a product\n");
			System.out.print("2) Remove a product\n");
			System.out.print("3) Edit a product\n");
			System.out.print("4) Display orders\n");
			System.out.print("0) Exit\n");
			choice = Integer.parseInt(scanner.nextLine());
			if (0 == choice)
				return;
			else if (1 == choice) {
				Product p = new Product();
				System.out.print("Please enter product's ID: ");
				p.setId(scanner.nextLine());
				System.out.print("Please enter product's name: ");
				p.setName(scanner.nextLine());
				System.out.print("Please enter product's category: ");
				p.setProductCategory(scanner.nextLine());
				System.out.print("Please enter product's price: ");
				p.setPrice(scanner.nextLine());
				System.out.print("Please enter product's discounted price: ");
				p.setDiscountedPrice(scanner.nextLine());
				System.out.print("Please enter product's description: ");
				p.setDescription(scanner.nextLine());
				String name = UserUtil.getUserName(userID);
				if (name != null)
					p.setTrader(name);
				ProductUtil.addProduct(p, "Products.txt");
			}
			else if (2 == choice) {
				System.out.print("Enter the ID of product which will be deleted:");
				String id = scanner.nextLine();
				ProductUtil.deleteProduct(id);
			}
			else if (3 == choice) {
				System.out.print("Enter the ID of product which will be edited: ");
				String id = scanner.nextLine();
				ProductUtil.editProduct(id);
			}
			else if (4 == choice) {
				System.out.print("1) See List of Orders\n");
				System.out.print("2) Accept Order\n");
				System.out.print("3) Cancel Order\n");
				int traderChoice = Integer.parseInt(scanner.nextLine());

				if (1 == traderChoice) {
					ProductUtil.displayOrders(userID);
				}
				else if (2 == traderChoice) {
					System.out.print("Which order will be accepted? Enter its product ID: ");
					String productId = scanner.nextLine();
					ProductUtil.acceptOrder(userID, productId);
				}
				else if (3 == traderChoice) {
					System.out.print("Which order will be NOT accepted? Enter its product ID: \n");
					String productId = scanner.nextLine();
					ProductUtil.cancelOrder(productId);
				}
			}
		} while (choice != 0);
	}

	public static void CustomerMenu(String userID) {
		int choice;
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("1) Search and query the product\n");
			System.out.print("2) Display all the products of a trader\n");
			System.out.print("0) Exit\n");
			choice = Integer.parseInt(scanner.nextLine());
			if (0 == choice)
				break;
			if (1 == choice) {
				System.out.print("Please enter a product name: ");
				String str = scanner.nextLine();
				ArrayList<Product> searchedProducts = new ArrayList<Product>();

				ProductUtil.searchOnFile(searchedProducts,str, 1);
				for (;;) {
					System.out.print("1) Sorting\n");
					System.out.print("2) Filtering\n");
					System.out.print("3) Order\n");
					System.out.print("0) Go to previous menu\n");
					int option = Integer.parseInt(scanner.nextLine());
					if (0 == option) {
						break;
					}
					else if (1 == option) {
						System.out.print("According to what would you like to sort?\n");
						System.out.print("1) Name\n");
						System.out.print("2) Price\n");
						System.out.print("3) Discount\n");
						int num = Integer.parseInt(scanner.nextLine());
						ProductUtil.searchSort(searchedProducts, num);
					}
					else if (2 == option) {
						System.out.print("\nWhat would you like to filter by\n");
						System.out.print("1) Price\n");
						System.out.print("2) Category\n");
						int num = Integer.parseInt(scanner.nextLine());

						if (num == 1) {
							System.out.print("1) filter with lower bound\n");
							System.out.print("2) filter with upper bound\n");
							System.out.print("3) filter with upper and lower bound\n");
							int priceChoice = Integer.parseInt(scanner.nextLine());

							if (priceChoice == 1) {
								System.out.print("Enter the lower bound: ");
								int price = Integer.parseInt(scanner.nextLine());
								ProductUtil.priceFilter(searchedProducts,1, price);
							}
							else if (priceChoice == 2) {
								System.out.print("Enter the upper bound: ");
								int price = Integer.parseInt(scanner.nextLine());
								ProductUtil.priceFilter(searchedProducts ,2, price);
							}
							else if (priceChoice == 3) {
								System.out.print("Enter the lower bound: ");
								int priceOne = Integer.parseInt(scanner.nextLine().trim());
								System.out.print("Enter the upper bound: ");
								int priceTwo = Integer.parseInt(scanner.nextLine().trim());
								ProductUtil.priceFilter(searchedProducts, 3, priceOne, priceTwo);
							}
						}
						else if (num == 2) {
							System.out.print("Enter the category\n");
							String category = scanner.nextLine();
							ProductUtil.filterCategory(searchedProducts, category);
						}
					}
					else if (3 == option) {
						System.out.print("Enter the product ID which will be ordered: ");
						String productId = scanner.nextLine();
						ProductUtil.addOrder(searchedProducts, productId, userID);
					}
				}
			}
			else if (2 == choice) {
				System.out.print("Please enter a trader name: ");
				String str = scanner.nextLine();
				ProductUtil.searchFile(str);
			}
		} while (choice != 0);

	}
}
