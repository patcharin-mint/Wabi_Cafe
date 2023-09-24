//Patcharin Khangwicha 6410406797
package ku.cs.Wabi.Cafe.repository;

import ku.cs.Wabi.Cafe.entity.OrderItem;
import ku.cs.Wabi.Cafe.entity.OrderItemKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository
        extends JpaRepository<OrderItem, OrderItemKey> {
}

