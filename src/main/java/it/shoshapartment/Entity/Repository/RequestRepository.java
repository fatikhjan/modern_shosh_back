package it.shoshapartment.Entity.Repository;

import it.shoshapartment.Entity.Entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface RequestRepository extends JpaRepository<Request, Integer> {
}
