package it.shoshapartment.Entity.Repository;

import it.shoshapartment.Entity.Entity.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;
@CrossOrigin
public interface BlogsRepository extends JpaRepository<Blogs, Integer> {

}
