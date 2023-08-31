package it.shoshapartment.Entity.Repository;

import it.shoshapartment.Entity.Entity.Blogs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogsRepository extends JpaRepository<Blogs, Integer> {

}
