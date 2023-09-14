package it.shoshapartment.Entity.Pyload;

import java.util.Date;

public record OrderDto(
        String phoneNumber,

        Date going_time,

        Date come_time,

        Integer older,

        Integer child,

        Integer rooms_size,

        String lang
) {
}
