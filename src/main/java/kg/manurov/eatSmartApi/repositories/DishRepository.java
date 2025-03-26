package kg.manurov.eatSmartApi.repositories;

import kg.manurov.eatSmartApi.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}