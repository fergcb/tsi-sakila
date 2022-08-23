package uk.fergcb.sakila.data.resources.language;

import javax.persistence.*;

@Entity
@Table(name="language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="language_id")
    private Integer languageId;

    @Column(name="name")
    private String name;

    public Language(LanguageDTO languageDTO) {
        this.updateFromDTO(languageDTO);
    }

    public Language() {}

    public void updateFromDTO(LanguageDTO languageDTO) {
        this.setName(languageDTO.getName().orElse(name));
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
