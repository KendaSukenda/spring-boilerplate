package id.web.sukenda.repository;

import id.web.sukenda.entity.Menu;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends ReactiveMongoRepository<Menu, String> {

}
