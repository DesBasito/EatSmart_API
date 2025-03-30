package kg.manurov.eatsmartapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "DISHES")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;


    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "CALORIES", nullable = false)
    private Double calories;

    @Column(name = "PROTEIN")
    private Double protein;

    @Column(name = "FATS")
    private Double fats;

    @Column(name = "CARBOHYDRATES")
    private Double carbohydrates;

    @ManyToMany
    @JoinTable(name = "MEAL_DISHES",
            joinColumns = @JoinColumn(name = "DISHES_ID"),
            inverseJoinColumns = @JoinColumn(name = "MEAL_ID"))
    private Set<Meal> meals;
}