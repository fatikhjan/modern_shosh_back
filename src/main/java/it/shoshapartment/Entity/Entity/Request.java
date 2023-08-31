package it.shoshapartment.Entity.Entity;

import it.shoshapartment.Entity.Entity.template.AbsNameEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Request extends AbsNameEntity {

    @Column(name = "phone_number")
    private String phoneNumber;
}
