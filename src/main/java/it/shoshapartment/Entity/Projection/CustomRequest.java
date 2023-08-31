package it.shoshapartment.Entity.Projection;

import it.shoshapartment.Entity.Entity.Request;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "CustomRequest", types = Request.class)
public interface CustomRequest {

    Integer getId();

    String getName();

    String getPhoneNumber();

}
