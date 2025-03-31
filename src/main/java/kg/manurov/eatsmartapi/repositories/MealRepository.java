package kg.manurov.eatsmartapi.repositories;

import kg.manurov.eatsmartapi.models.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {
    @Query("SELECT m FROM Meal m LEFT JOIN FETCH m.dishes WHERE m.user.id = :userId AND m.date = :date")
    List<Meal> getMealsByUser_IdAndDate(Long userId, LocalDate date);
}