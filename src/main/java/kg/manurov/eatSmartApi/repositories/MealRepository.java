package kg.manurov.eatSmartApi.repositories;

import kg.manurov.eatSmartApi.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}