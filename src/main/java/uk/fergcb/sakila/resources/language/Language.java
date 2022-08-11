package uk.fergcb.sakila.resources.language;

import javax.persistence.*;

@Entity
@Table(name="language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="language_id")
    private int languageId;

    @Column(name="name")
    private String name;

    public Language(LanguageDTO languageDTO) {
        this.updateFromDTO(languageDTO);
    }

    public Language() {}

    public void updateFromDTO(LanguageDTO languageDTO) {
        this.name = languageDTO.getName().orElse(name);
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
