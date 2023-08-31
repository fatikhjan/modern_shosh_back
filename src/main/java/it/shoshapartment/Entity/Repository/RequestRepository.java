package it.shoshapartment.Entity.Repository;

import it.shoshapartment.Entity.Entity.Request;
import it.shoshapartment.Entity.Projection.CustomRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "request", collectionResourceRel = "list", excerptProjection = CustomRequest.class)
public interface RequestRepository extends JpaRepository<Request, Integer> {
}
