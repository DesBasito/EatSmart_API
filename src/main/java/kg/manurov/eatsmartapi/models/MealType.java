package kg.manurov.eatsmartapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "MEAL_TYPE")
public class MealType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 65)
    @NotNull
    @Column(name = "NAME", nullable = false, length = 65)
    private String name;

    @OneToMany(mappedBy = "mealTime")
    private Set<Meal> meals = new LinkedHashSet<>();

}