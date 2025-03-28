package kg.manurov.eatSmartApi.repositories;

import kg.manurov.eatSmartApi.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
}