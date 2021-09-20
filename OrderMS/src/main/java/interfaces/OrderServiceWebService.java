package interfaces;

import java.lang.String;
import java.util.List;
import org.mybatis.jpetstore.domain.Order;
import org.mybatis.jpetstore.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class OrderServiceWebService {

    @Autowired
    public OrderService orderService;

    @GetMapping("/OrderService/getNextId")
    public int getNextId(@RequestParam("name") String name){
        return orderService.getNextId(name);
    }

    @GetMapping("/OrderService/getOrder")
    public Order getOrder(@RequestParam("orderId") int orderId){
    	System.out.println("order number: " + orderId);
    	System.out.println(orderService.getOrder(orderId).toString());
        return orderService.getOrder(orderId);
    }

    @PostMapping("/OrderService/insertOrder")
    public void insertOrder(@RequestBody Order order){
    	System.out.println("done");
    	System.out.println("isNull: " + (order != null));
    	System.out.println("getOrderId: " + order.getOrderId());
    	System.out.println("getUsername: " + order.getUsername());
    	System.out.println("getBillAddress1: " + order.getBillAddress1());
    	System.out.println("getOrderDate: " + order.getOrderDate());
        orderService.insertOrder(order);
    }

    @GetMapping("/OrderService/getOrdersByUsername")
    public List<Order> getOrdersByUsername(@RequestParam("username") String username){
        return orderService.getOrdersByUsername(username);
    }

}