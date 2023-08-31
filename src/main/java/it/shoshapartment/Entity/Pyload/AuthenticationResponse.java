package it.shoshapartment.Entity.Pyload;

import it.shoshapartment.Entity.Entity.User;

public record AuthenticationResponse(
        String token,
        User user
) {
}
