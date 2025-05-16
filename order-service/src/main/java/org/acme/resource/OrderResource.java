package org.acme.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.UserDTO;
import org.acme.entity.Order;
import org.acme.service.OrderService;

import java.util.List;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create a new order
    @POST
    public Response createOrder(Order order) {
        Order createdOrder = orderService.createOrder(order);
        return Response.status(Response.Status.CREATED).entity(createdOrder).build();
    }

    // Get all orders
    @GET
    public Response getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return Response.ok(orders).build();
    }

    // Get order by ID
    @GET
    @Path("/{id}")
    public Response getOrder(@PathParam("id") Long id) {
        Order order = orderService.getOrder(id);
        if (order == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(order).build();
    }

    // Update an order
    @PUT
    @Path("/{id}")
    public Response updateOrder(@PathParam("id") Long id, Order order) {
        Order updatedOrder = orderService.updateOrder(id, order);
        if (updatedOrder == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updatedOrder).build();
    }

    // Delete an order
    @DELETE
    @Path("/{id}")
    public Response deleteOrder(@PathParam("id") Long id) {
        orderService.deleteOrder(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/users/{id}")
    public UserDTO getUserDetails(@PathParam("id") Long id) {
        return orderService.getUserDetails(id);
    }

}
