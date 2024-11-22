package personal.neuro.neuroAPI.dtos;

import lombok.*;
import personal.neuro.neuroAPI.entities.Certification;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationDTO implements Serializable {
    private String title;

    public static List<CertificationDTO> fromEntities(
            List<Certification> certifications) {
        return certifications.stream().map(CertificationDTO::fromEntity).collect(Collectors.toList());
    }

    public static CertificationDTO fromEntity(Certification certification) {
        CertificationDTO CertificationDTO = new CertificationDTO();
        CertificationDTO.setTitle(certification.getTitle());
        return CertificationDTO;
    }

    public static List<Certification> toEntities(List<CertificationDTO> certificationDTOs) {
        return certificationDTOs.stream().map(CertificationDTO::toEntity).collect(Collectors.toList());
    }

    public Certification toEntity() {
        Certification certification = new Certification();
        certification.setTitle(this.getTitle());
        return certification;
    }
}
