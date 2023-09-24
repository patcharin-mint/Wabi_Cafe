//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.service;

import ku.cs.Wabi.Cafe.common.Status;
import ku.cs.Wabi.Cafe.entity.Menu;
import ku.cs.Wabi.Cafe.entity.OrderItem;
import ku.cs.Wabi.Cafe.entity.OrderItemKey;
import ku.cs.Wabi.Cafe.entity.PurchaseOrder;
import ku.cs.Wabi.Cafe.model.AddCartRequest;
import ku.cs.Wabi.Cafe.repository.MenuRepository;
import ku.cs.Wabi.Cafe.repository.OrderItemRepository;
import ku.cs.Wabi.Cafe.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Service
public class OrderService {


    @Autowired
    private PurchaseOrderRepository orderRepository;


    @Autowired
    private OrderItemRepository itemRepository;


    @Autowired
    private MenuRepository menuRepository;


    private UUID currentOrderId;

    public PurchaseOrder getCurrentOrder() {
        if (currentOrderId == null)
            createNewOrder();
        return orderRepository.findById(currentOrderId).get();
    }

    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }


    public void submitOrder() {
        PurchaseOrder currentOrder =
                orderRepository.findById(currentOrderId).get();
        currentOrder.setTimestamp(LocalDateTime.now());
        currentOrder.setStatus(Status.CONFIRM);
        orderRepository.save(currentOrder);
        currentOrderId = null;
    }


    public void createNewOrder() {
        PurchaseOrder newOrder = new PurchaseOrder();
        newOrder.setStatus(Status.ORDER);
        PurchaseOrder record = orderRepository.save(newOrder);
        currentOrderId = record.getId();
    }


    public void order(UUID menuId, AddCartRequest request) {
        if (currentOrderId == null)
            createNewOrder();


        PurchaseOrder currentOrder =
                orderRepository.findById(currentOrderId).get();
        Menu menu = menuRepository.findById(menuId).get();


        OrderItem item = new OrderItem();
        item.setId(new OrderItemKey(currentOrderId, menuId));
        item.setPurchaseOrder(currentOrder);
        item.setMenu(menu);
        item.setQuantity(request.getQuantity());
        itemRepository.save(item);
    }

    public PurchaseOrder getById(UUID id) {
        return orderRepository.findById(id).get();
    }


    public void finishOrder(UUID orderId) {
        PurchaseOrder record = orderRepository.findById(orderId).get();
        record.setStatus(Status.FINISH);
        orderRepository.save(record);
    }

}

