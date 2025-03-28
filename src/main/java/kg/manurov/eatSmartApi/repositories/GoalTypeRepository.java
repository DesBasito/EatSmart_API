package kg.manurov.eatSmartApi.repositories;


import kg.manurov.eatSmartApi.models.GoalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoalTypeRepository extends JpaRepository<GoalType, Long> {
    @Query("select g from GoalType g where g.name = :name")
    Optional<GoalType> findByName(String name);
}