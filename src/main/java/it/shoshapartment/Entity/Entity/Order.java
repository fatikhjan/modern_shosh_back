package it.shoshapartment.Entity.Entity;

import com.fasterxml.jackson.databind.DatabindException;
import it.shoshapartment.Entity.Entity.template.AbsEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "orders")
public class Order extends AbsEntity {

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "going_time")
    private Date goTime;

    @Column(name = "come_time")
    private Date comeTime;

    @Column(name = "older")
    private Integer older;

    @Column(name = "child")
    private Integer child;

    @Column(name = "size_of_rooms")
    private Integer sizeOfRooms;


}
