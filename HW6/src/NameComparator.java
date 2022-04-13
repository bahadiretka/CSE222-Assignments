import java.util.Comparator;

/**
 * Comparator for comparing products with name
 * @author Bahadir Etka Kilinc
 */
class NameComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Product pr1,pr2;
        pr1 = (Product) o1;
        pr2 = (Product) o2;
        return pr1.getName().compareTo(pr2.getName());
    }
}
