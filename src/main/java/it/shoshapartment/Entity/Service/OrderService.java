package it.shoshapartment.Entity.Service;

import it.shoshapartment.Entity.Entity.Order;
import it.shoshapartment.Entity.Pyload.ApiResponse;
import it.shoshapartment.Entity.Pyload.OrderDto;
import it.shoshapartment.Entity.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final TelegramService telegramService;

    public ApiResponse ordering(OrderDto orderDto){
        Order order = Order.builder()
                .child(orderDto.child())
                .older(orderDto.older())
                .phoneNumber(orderDto.phoneNumber())
                .sizeOfRooms(orderDto.rooms_size())
                .comeTime(orderDto.come_time())
                .goTime(orderDto.going_time())
                .build();
        orderRepository.save(order);
        int i = order.getComeTime().getYear() + 1900;
        int i1 = order.getGoTime().getYear() + 1900;
        telegramService.sendMessage("Yangi buyurtma 🤑🤑\n" + " Bog'lanish uchun= " + order.getPhoneNumber() + "\nKattalar = " + order.getOlder() + "\nBolalar = " + order.getChild() + "\nkelish sanansi = " + i + "-yil " + order.getComeTime().getMonth() + "-oy " + order.getComeTime().getDate() + "-sana" + "\nketish sanasi = " + i1 + "-yil " + order.getGoTime().getMonth() + "-oy " + order.getGoTime().getDate() + "-sana" + "\nHonalar soni = " + order.getSizeOfRooms());
        return new ApiResponse("Buyurtmangiz muvaffaqiyatli saqlandi", true, 200);
    }

}
