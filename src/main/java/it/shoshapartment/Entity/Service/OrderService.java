package it.shoshapartment.Entity.Service;

import it.shoshapartment.Entity.Entity.Order;
import it.shoshapartment.Entity.Pyload.ApiResponse;
import it.shoshapartment.Entity.Pyload.OrderDto;
import it.shoshapartment.Entity.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        int i2 = order.getComeTime().getMonth() + 1;
        int i3 = order.getGoTime().getMonth() + 1;
        telegramService.sendMessage(
                orderDto.lang().equals("uzb") ? "Yangi buyurtma 🤑🤑\n" + " Bog'lanish uchun= " + order.getPhoneNumber() + "\nKattalar = " + order.getOlder() + "\nBolalar = " + order.getChild() + "\nKelish sanansi = " + i + "-yil " + i2 + "-oy " + order.getComeTime().getDate() + "-sana" + "\nKetish sanasi = " + i1 + "-yil " + i3 + "-oy " + order.getGoTime().getDate() + "-sana" + "\nXonalar soni = " + order.getSizeOfRooms()
                        : orderDto.lang().equals("rus") ? "Новый заказ 🤑🤑\n" + "Контакт= " + order.getPhoneNumber() + "\nВзрослые = " + order.getOlder() + "\nДети = " + order.getChild() + "\nДата прибытия = " + i + "-год " + i2 + "-месяц " + order.getComeTime().getDate() + "-дата" + "\nДата выезда = " + i1 + "-год " + i3 + "-месяц " + order.getGoTime().getDate() + "-дата" + "\nКоличество комнат = " + order.getSizeOfRooms()
                : "New order 🤑🤑\n" + "Contact= " + order.getPhoneNumber() + "\nAdults = " + order.getOlder() + "\nChildren = " + order.getChild() + "\nArrival date = " + i + "-year " + i2 + "-month " + order.getComeTime().getDate() + "-date" + "\nDate of departure = " + i1 + "-year " + i3 + "-month " + order.getGoTime().getDate() + "-date" + "\nNumber of rooms = " + order.getSizeOfRooms());
        return new ApiResponse("Buyurtmangiz muvaffaqiyatli saqlandi", true, 200);
    }

}
