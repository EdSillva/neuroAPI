package personal.neuro.neuroAPI.entities;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class TechnicalSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
