package service;
import model.Order;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
public class OrderService {
    private Map<Integer, Order> ordersById = new HashMap<>();
    private Queue<Order> orderQueue = new ArrayDeque<>();

    public synchronized void addOrder(Order order) {
        if (order == null || ordersById.containsKey(order.getId())) {
            throw new IllegalArgumentException("Order already exists or is invalid.");
        }

        ordersById.put(order.getId(), order);
        orderQueue.offer(order);
    }

    public synchronized Order getOrderById(int id) {
        return ordersById.get(id);
    }

    public synchronized boolean removeOrder(int id) {
        Order order = ordersById.remove(id);

        if (order == null) {
            return false;
        }

        orderQueue.remove(order);
        return true;
    }

    public synchronized Order processNextOrder() {
        Order order = orderQueue.poll();

        if (order != null) {
            order.setStatus("PROCESSING");
        }

        return order;
    }

    public synchronized List<Order> getAllOrders() {
        return new ArrayList<>(ordersById.values());
    }

    public synchronized int getOrderCount() {
        return ordersById.size();
    }

    public synchronized int getPendingOrderCount() {
        return orderQueue.size();
    }}