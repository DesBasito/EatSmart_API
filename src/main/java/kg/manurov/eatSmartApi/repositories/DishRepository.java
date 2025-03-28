package kg.manurov.eatSmartApi.repositories;

import kg.manurov.eatSmartApi.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}