package kg.manurov.eatsmartapi.models;

import jakarta.persistence.*;
import kg.manurov.eatsmartapi.enums.ActivityLevel;
import kg.manurov.eatsmartapi.enums.Gender;
import kg.manurov.eatsmartapi.enums.GoalType;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 65)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "height", nullable = false)
    private Double height;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "gender")
    private String gender;

    @Column(name = "activity_level")
    private String activityLevel;

    @Column(name = "goal_type")
    private String goalType;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Meal> meals = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Report> reports = new LinkedHashSet<>();


}