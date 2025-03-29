package kg.manurov.eatsmartapi.repositories;

import kg.manurov.eatsmartapi.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}