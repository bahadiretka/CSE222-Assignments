
/**
 * Simple Product Class that holds single product data with necessary setters and getters.
 * @author Bahadir Etka Kilinc
 */
public class Product implements Comparable<Product> {
	private String id;
	private String name;
	private String productCategory;
	private String price;
	private String discountedPrice;
	private String description;
	private String trader;

	public Product() {
		this("", "", "", "", "", "", "");
	}
	
	public Product(String id, String name, String productCategory, String price, String discountedPrice, String description, String trader) {
		this.setId(id);
		this.setName(name);
		this.setProductCategory(productCategory);
		this.setPrice(price);
		this.setDiscountedPrice(discountedPrice);
		this.setDescription(description);
		this.setTrader(trader);
	}

	public String getId() { return id; }

	public void setId(String id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getPrice() { return price; }

	public void setPrice(String price) { this.price = price; }

	public String getDiscountedPrice() { return discountedPrice; }

	public void setDiscountedPrice(String discountedPrice) { this.discountedPrice = discountedPrice; }

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }

	public String getTrader() { return trader; }
	
	public void setTrader(String trader) { this.trader = trader; }
	
	public String getCategory() { return productCategory; }
	
	public void setProductCategory(String productCategory) { this.productCategory = productCategory; }
	@Override
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return String.format("%s;%s;%s;%s;%s;%s;%s\n", getId(), getName(), getCategory(), getPrice(), getDiscountedPrice(), getDescription(), getTrader());
	}
	@Override
	/**
	 * {@inheritDoc}
	 */
	public int compareTo(Product o) 
	{
		return getId().compareTo(o.getId());
	}
}


