package model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Order {
    private int id;
    private User user;
    private List<OrderItem> items;
    private String status;
    private LocalDate orderDate;
    public Order(int id, User user) {
        if (id <= 0 || user == null) {
            throw new IllegalArgumentException("Invalid order data.");
        }
        this.id = id;
        this.user = user;
        this.items = new ArrayList<>();
        this.status = "NEW";
        this.orderDate = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }
        items.add(item);
    }

    public void setStatus(String status) {
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Status cannot be empty.");
        }
        this.status = status;
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Order)) return false;
        Order order = (Order) obj;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order ID: " + id
                + " | Customer: " + user.getName()
                + " | Status: " + status
                + " | Date: " + orderDate
                + " | Total: $" + getTotal();
    }}