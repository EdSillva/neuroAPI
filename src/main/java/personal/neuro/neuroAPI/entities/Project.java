package personal.neuro.neuroAPI.entities;

import lombok.*;
import java.util.*;

import jakarta.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int numProject;
    private String name;
    private String description;
    private Date initialDate;
    private Date finalDate;
    private String status;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Employee leader;
}
