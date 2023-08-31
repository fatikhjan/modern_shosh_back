package it.shoshapartment.Entity.Entity.template;

import jakarta.persistence.*;
import lombok.Data;


@Data
@MappedSuperclass
public abstract class AbsNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 1000)
    private String name;

}
