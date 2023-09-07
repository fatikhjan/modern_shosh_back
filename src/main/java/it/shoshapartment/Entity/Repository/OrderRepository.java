package it.shoshapartment.Entity.Repository;

import it.shoshapartment.Entity.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.UUID;
@CrossOrigin
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
