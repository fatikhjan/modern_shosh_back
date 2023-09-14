package it.shoshapartment.Entity.Entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "uz_name", length = 10000)
    private String name;

    @Column(name = "ru_name", length = 10000)
    private String ruName;

    @Column(name = "eng_name", length = 10000)
    private String engName;

    @Column(length = 30000, name = "about_this_uz")
    private String uzAbout;

    @Column(length = 30000, name = "about_this_ru")
    private String ruAbout;

    @Column(length = 30000, name = "about_this_eng")
    private String engAbout;

    private UUID photoId;
}
