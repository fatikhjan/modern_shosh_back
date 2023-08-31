package it.shoshapartment.Entity.Pyload;

public record ApiResponse(
        String message,

        boolean success,

        Integer status
) {
}
