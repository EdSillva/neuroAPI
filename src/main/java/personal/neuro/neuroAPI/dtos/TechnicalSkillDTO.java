package personal.neuro.neuroAPI.dtos;

import lombok.*;
import personal.neuro.neuroAPI.entities.TechnicalSkill;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TechnicalSkillDTO implements Serializable {
    private String title;
    private String description;

    public static List<TechnicalSkillDTO> fromEntities(List<TechnicalSkill> technicalSkills) {
        return technicalSkills.stream()
                .map(TechnicalSkillDTO::fromEntity).collect(Collectors.toList());
    }

    public static TechnicalSkillDTO fromEntity(TechnicalSkill technicalSkill) {
        TechnicalSkillDTO technicalSkillDTO = new TechnicalSkillDTO();
        technicalSkillDTO.setTitle(technicalSkill.getTitle());
        technicalSkillDTO.setDescription(technicalSkill.getDescription());
        return technicalSkillDTO;
    }

    public static List<TechnicalSkill> toEntities(List<TechnicalSkillDTO> technicalSkills) {
        return technicalSkills.stream()
                .map(TechnicalSkillDTO::toEntity)
                .collect(Collectors.toList());
    }

    public TechnicalSkill toEntity() {
        TechnicalSkill technicalSkill = new TechnicalSkill();
        technicalSkill.setTitle(this.getTitle());
        technicalSkill.setDescription(this.getDescription());
        return technicalSkill;
    }

}
