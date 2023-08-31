package it.shoshapartment.Entity.Entity;

import it.shoshapartment.Entity.Entity.template.AbsNameEntity;
import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import org.hibernate.query.sqm.produce.function.StandardFunctionReturnTypeResolvers;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Blogs  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uz_name")
    private String name;

    @Column(name = "ru_name")
    private String ruName;

    @Column(name = "eng_name")
    private String engName;

    @Column(length = 1000, name = "about_this_uz")
    private String uzAbout;

    @Column(length = 1000, name = "about_this_ru")
    private String ruAbout;

    @Column(length = 1000, name = "about_this_eng")
    private String engAbout;

    private UUID photoId;
}
