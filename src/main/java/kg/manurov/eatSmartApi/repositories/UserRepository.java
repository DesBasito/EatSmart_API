package kg.manurov.eatSmartApi.repositories;

import kg.manurov.eatSmartApi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}