package service;
import model.Product;
import java.util.*;
public class ProductService {
    private final List<Product> products = new ArrayList<>();
    private final Set<Product> uniqueProducts = new HashSet<>();
    private final Map<Integer, Product> productById = new HashMap<>();
    private final Map<String, List<Product>> productsByCategory = new HashMap<>();

    // CREATE
    public synchronized void addProduct(Product product) {
        if (product == null || productById.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product already exists or is invalid.");
        }

        products.add(product);
        uniqueProducts.add(product);
        productById.put(product.getId(), product);

        productsByCategory
                .computeIfAbsent(product.getCategory(), k -> new ArrayList<>())
                .add(product);
    }

    // READ
    public synchronized Product getProductById(int id) {
        return productById.get(id);
    }

    public synchronized List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public synchronized List<Product> getProductsByCategory(String category) {
        return new ArrayList<>(
                productsByCategory.getOrDefault(category, new ArrayList<>())
        );
    }

    // FILTER BY NAME
    public synchronized List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();

        if (name == null || name.isBlank()) {
            return result;
        }

        for (Product product : products) {
            if (product.getName().toLowerCase()
                    .contains(name.toLowerCase())) {
                result.add(product);
            }
        }

        return result;
    }

    // FILTER BY PRICE RANGE
    public synchronized List<Product> filterByPrice(double min, double max) {
        List<Product> result = new ArrayList<>();

        if (min < 0 || max < min) {
            throw new IllegalArgumentException("Invalid price range.");
        }

        for (Product product : products) {
            if (product.getPrice() >= min && product.getPrice() <= max) {
                result.add(product);
            }
        }

        return result;
    }

    // UPDATE
    public synchronized void updateProduct(Product product) {
        if (product == null || !productById.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product not found.");
        }

        Product oldProduct = productById.get(product.getId());

        List<Product> oldCategory =
                productsByCategory.get(oldProduct.getCategory());

        if (oldCategory != null) {
            oldCategory.remove(oldProduct);

            if (oldCategory.isEmpty()) {
                productsByCategory.remove(oldProduct.getCategory());
            }
        }

        int index = products.indexOf(oldProduct);
        products.set(index, product);

        uniqueProducts.remove(oldProduct);
        uniqueProducts.add(product);

        productById.put(product.getId(), product);

        productsByCategory
                .computeIfAbsent(product.getCategory(), k -> new ArrayList<>())
                .add(product);
    }

    // DELETE
    public synchronized boolean removeProduct(int id) {
        Product product = productById.remove(id);

        if (product == null) {
            return false;
        }

        products.remove(product);
        uniqueProducts.remove(product);

        List<Product> categoryProducts =
                productsByCategory.get(product.getCategory());

        if (categoryProducts != null) {
            categoryProducts.remove(product);

            if (categoryProducts.isEmpty()) {
                productsByCategory.remove(product.getCategory());
            }
        }

        return true;
    }

    // ANALYTICS
    public synchronized int getProductCount() {
        return products.size();
    }

    public synchronized double getAveragePrice() {
        if (products.isEmpty()) return 0;

        double total = 0;

        for (Product product : products) {
            total += product.getPrice();
        }

        return total / products.size();
    }

    public synchronized Product getMostExpensiveProduct() {
        return Collections.max(
                products,
                Comparator.comparingDouble(Product::getPrice)
        );
    }

    public synchronized int getUniqueProductCount() {
        return uniqueProducts.size();
    }}