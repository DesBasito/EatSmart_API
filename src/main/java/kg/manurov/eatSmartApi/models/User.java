package kg.manurov.eatSmartApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 65)
    @NotNull
    @Column(name = "NAME", nullable = false, length = 65)
    private String name;

    @NotNull
    @Column(name = "AGE", nullable = false)
    private Integer age;

    @NotNull
    @Column(name = "HEIGHT", nullable = false)
    private Double height;

    @NotNull
    @Column(name = "WEIGHT", nullable = false)
    private Double weight;

    @Size(max = 65)
    @NotNull
    @Column(name = "EMAIL", nullable = false, length = 65)
    private String email;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "GOAL_TYPE_ID", nullable = false)
    private GoalType goalType;

}