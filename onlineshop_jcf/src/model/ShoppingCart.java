package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class ShoppingCart { private int id; private User user; private List<CartItem> items;
    public ShoppingCart(int id, User user) {
        if (id <= 0 || user == null) {
            throw new IllegalArgumentException("Invalid cart data.");
        }

        this.id = id;
        this.user = user;
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public void addItem(Product product, int quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Invalid item data.");
        }

        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public boolean removeItem(int productId) {
        return items.removeIf(
                item -> item.getProduct().getId() == productId
        );
    }

    public double getTotal() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void clearCart() {
        items.clear();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ShoppingCart)) return false;
        ShoppingCart cart = (ShoppingCart) obj;
        return id == cart.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Shopping Cart ID: " + id
                + " | Customer: " + user.getName()
                + " | Items: " + items.size()
                + " | Total: $" + getTotal();
    }}