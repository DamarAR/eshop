package id.ac.ui.cs.advprog.eshop.service;

import enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;

import java.util.List;

public interface OrderService {
    public Order createOrder(Order order);

    public Order UpdateStatus(String orderId, String status);

    public Order findById(String orderId);

    public List<Order> findAllByAuthor(String author);
}
