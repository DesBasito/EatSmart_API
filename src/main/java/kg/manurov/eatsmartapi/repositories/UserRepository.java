package kg.manurov.eatsmartapi.repositories;

import kg.manurov.eatsmartapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String value);
}