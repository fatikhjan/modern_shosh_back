package it.shoshapartment.Entity.Repository;

import it.shoshapartment.Entity.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
