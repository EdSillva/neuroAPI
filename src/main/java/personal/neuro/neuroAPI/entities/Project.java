package personal.neuro.neuroAPI.entities;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int numProjet;
    private String name;
    private String description;
    private String status;

    @OneToOne(fetch = FetchType.LAZY)
    private Employee leader;
}
