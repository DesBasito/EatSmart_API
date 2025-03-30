package kg.manurov.eatsmartapi.repositories;

import kg.manurov.eatsmartapi.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> getMealsByUser_IdAndDate(Long userId, LocalDate date);
}