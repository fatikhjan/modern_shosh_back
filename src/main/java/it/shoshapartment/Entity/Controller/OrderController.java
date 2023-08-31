package it.shoshapartment.Entity.Controller;

import it.shoshapartment.Entity.Entity.Order;
import it.shoshapartment.Entity.Pyload.ApiResponse;
import it.shoshapartment.Entity.Pyload.OrderDto;
import it.shoshapartment.Entity.Repository.OrderRepository;
import it.shoshapartment.Entity.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final OrderRepository orderRepository;

    @PostMapping
    public HttpEntity<?> ordering(@RequestBody OrderDto orderDto){
        ApiResponse ordering = orderService.ordering(orderDto);
        return ResponseEntity.status(ordering.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(ordering);
    }

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Order> all = orderRepository.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found order"));
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOrder(@PathVariable UUID id){
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found order"));
        orderRepository.delete(order);
        return ResponseEntity.ok(new ApiResponse("buyurtma olib tashlandi", true, 200));
    }
}
