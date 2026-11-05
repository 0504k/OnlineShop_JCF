import model.*;
import service.*;
import comparator.*;
import java.time.LocalDate; import java.util.*;
public class Main {
    public static void main(String[] args) {

        System.out.println("===== ONLINE SHOP - JCF PROJECT =====");

        // 1. Create products
        Product p1 = new Clothing(
                1, "Dress", 110, "Clothing", "S", "Cotton");

        Product p2 = new Clothing(
                2, "Coat", 400, "Clothing", "M", "Wool");

        Product p3 = new Electronics(
                3, "Electric Toothbrush", 450,
                "Electronics", "Dyson", 2);

        Product p4 = new Electronics(
                4, "Smart Watch", 350,
                "Electronics", "Apple", 2);

        // 2. Product Service: List, Set and Maps
        ProductService productService = new ProductService();

        productService.addProduct(p1);
        productService.addProduct(p2);
        productService.addProduct(p3);
        productService.addProduct(p4);

        System.out.println("\n--- ALL PRODUCTS ---");
        for (Product product : productService.getAllProducts()) {
            System.out.println(product);
        }

        // 3. Map: O(1) lookup by ID
        System.out.println("\n--- SEARCH BY ID ---");
        System.out.println(productService.getProductById(3));

        // 4. Map: products by category
        System.out.println("\n--- PRODUCTS BY CATEGORY ---");
        for (Product product :
                productService.getProductsByCategory("Clothing")) {
            System.out.println(product);
        }

        // 5. Natural ordering by ID
        System.out.println("\n--- NATURAL ORDERING BY ID ---");
        List<Product> sortedProducts =
                productService.getAllProducts();

        Collections.sort(sortedProducts);

        for (Product product : sortedProducts) {
            System.out.println(product.getName() + " - ID: " + product.getId());
        }

        // 6. Custom Comparator 1: Name
        System.out.println("\n--- SORT BY NAME ---");
        sortedProducts.sort(new ProductNameComparator());

        for (Product product : sortedProducts) {
            System.out.println(product.getName());
        }

        // 7. Custom Comparator 2: Price
        System.out.println("\n--- SORT BY PRICE ---");
        sortedProducts.sort(new ProductPriceComparator());

        for (Product product : sortedProducts) {
            System.out.println(product.getName() + " - $" + product.getPrice());
        }

        // 8. Custom Comparator 3: Category
        System.out.println("\n--- SORT BY CATEGORY ---");
        sortedProducts.sort(new ProductCategoryComparator());

        for (Product product : sortedProducts) {
            System.out.println(product.getCategory() + " - " + product.getName());
        }

        // 9. Discount and Warranty interfaces
        System.out.println("\n--- INTERFACES ---");
        System.out.println("Dress after discount: $" +
                ((Discountable) p1).applyDiscount());

        System.out.println(((Warranty) p3).getWarrantyInfo());

        // 10. User Service: Set and Map
        User user = new User(
                1, "Fatima", "fatima@example.com");

        UserService userService = new UserService();
        userService.addUser(user);

        System.out.println("\n--- USER ---");
        System.out.println(userService.getUserById(1));

        // 11. Shopping Cart
        ShoppingCart cart = new ShoppingCart(1, user);
        cart.addItem(p1, 1);
        cart.addItem(p3, 1);

        System.out.println("\n--- SHOPPING CART ---");
        System.out.println(cart);

        for (CartItem item : cart.getItems()) {
            System.out.println(item);
        }

        // 12. Review
        Review review = new Review(
                1, user, p1, 5,
                "Great product!",
                LocalDate.now());

        System.out.println("\n--- REVIEW ---");
        System.out.println(review);

        // 13. Order Service: Map and Queue
        Order order = new Order(1, user);
        order.addItem(new OrderItem(p1, 1));
        order.addItem(new OrderItem(p3, 1));

        OrderService orderService = new OrderService();
        orderService.addOrder(order);

        System.out.println("\n--- ORDER ---");
        System.out.println(order);

        System.out.println("\n--- PROCESS QUEUE ---");
        Order processedOrder = orderService.processNextOrder();

        if (processedOrder != null) {System.out.println("Processed: " + processedOrder);
        }

        // 14. Analytics
        System.out.println("\n--- ANALYTICS ---");
        System.out.println("Total products: " +
                productService.getProductCount());

        System.out.println("Total users: " +
                userService.getUserCount());

        System.out.println("Total orders: " +
                orderService.getOrderCount());
        System.out.println("\n--- SEARCH BY NAME ---");
        for (Product product : productService.searchByName("Watch")) {
            System.out.println(product);
        }

        System.out.println("\n--- FILTER BY PRICE (100-400) ---");
        for (Product product : productService.filterByPrice(100, 400)) {
            System.out.println(product);
        }

        System.out.println("\n--- ADDITIONAL ANALYTICS ---");
        System.out.println("Average product price: $" + productService.getAveragePrice());
        System.out.println("Most expensive product: " + productService.getMostExpensiveProduct());
        System.out.println("Unique products: " + productService.getUniqueProductCount());

        System.out.println("\n===== PROJECT FINISHED =====");
    }}