package model;
import java.util.Objects;
public class CartItem { private Product product; private int quantity;
    public CartItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CartItem)) return false;
        CartItem item = (CartItem) obj;
        return product.getId() == item.product.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(product.getId());
    }

    @Override
    public String toString() {
        return product.getName()
                + " | Quantity: " + quantity
                + " | Subtotal: $" + getSubtotal();
    }}