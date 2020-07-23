package id.web.sukenda.repository;

import id.web.sukenda.entity.Role;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RoleRepository extends ReactiveMongoRepository<Role, String> {

    Mono<Role> findFirstByCode(String code);

}
