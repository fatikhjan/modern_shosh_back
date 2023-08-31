package it.shoshapartment.Entity.Pyload;

import java.util.UUID;

public record BlogDto(
        String uzName,
        String ruName,
        String engName,

        String uzAbout,

        String ruAbout,
        String engAbout,

        UUID photoId
) {
}
