package lk.microservices.auth.server.repository;

import lk.microservices.auth.server.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
