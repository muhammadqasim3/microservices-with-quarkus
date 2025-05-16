package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.configuration.UserClientService;
import org.acme.dto.UserDTO;
import org.acme.entity.Order;
import org.acme.repository.OrderRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class OrderService {

    private final OrderRepository orderRepository;
    @Inject
    @RestClient
    private UserClientService userClientService;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Create order
    @Transactional
    public Order createOrder(Order order) {
        order.persist();
        return order;
    }

    // Get all orders
    public List<Order> getAllOrders() {
        return Order.listAll();
    }

    // Get order by ID
    public Order getOrder(Long id) {
        return Order.findById(id);
    }

    // Update order
    @Transactional
    public Order updateOrder(Long id, Order order) {
        Order existingOrder = Order.findById(id);
        if (existingOrder != null) {
            existingOrder.productName = order.productName;
            existingOrder.quantity = order.quantity;
            existingOrder.price = order.price;
            existingOrder.persist();
        }
        return existingOrder;
    }

    // Delete order
    @Transactional
    public void deleteOrder(Long id) {
        Order order = Order.findById(id);
        if (order != null) {
            order.delete();
        }
    }


    public UserDTO getUserDetails(Long id) {
        UserDTO userResponse =  userClientService.getUserById(id);

        if (userResponse == null) {
            throw new WebApplicationException("User not found", Response.Status.NOT_FOUND);
        }
        return userResponse;
    }
}
