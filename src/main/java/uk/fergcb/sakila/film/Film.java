package uk.fergcb.sakila.film;

import org.springframework.hateoas.Link;
import uk.fergcb.sakila.actor.PartialActor;
import uk.fergcb.sakila.category.Category;
import uk.fergcb.sakila.hateoas.HateoasResource;
import uk.fergcb.sakila.language.Language;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
@Table(name="film")
public class Film extends HateoasResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="film_id")
    private int filmId;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="release_year")
    private Integer releaseYear;

    @ManyToOne
    @JoinColumn(name="language_id", nullable=false, insertable = false, updatable = false)
    private Language language;

    @Column(name="language_id", nullable=false)
    private Integer languageId;

    @ManyToOne
    @JoinColumn(name="original_language_id", insertable = false, updatable = false)
    private Language originalLanguage;

    @Column(name="original_language_id")
    private Integer originalLanguageId;

    @Column(name="rental_duration")
    private Integer rentalDuration;

    @Column(name="rental_rate")
    private BigDecimal rentalRate;

    @Column(name="length")
    private Integer length;

    @Column(name="replacement_cost")
    private BigDecimal replacementCost;

    @Column(name="rating")
    private String rating;

    @Column(name="special_features")
    private String specialFeatures;

    @ManyToMany
    @JoinTable(name="film_actor",
            joinColumns = @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name="actor_id")
    )
    private List<PartialActor> cast;

    @ManyToMany
    @JoinTable(name="film_category",
            joinColumns = @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name="category_id")
    )
    private List<Category> categories;

    public Film(FilmDTO filmDTO) {
        this.updateFromDTO(filmDTO);
    }

    public Film() {}

    public void updateFromDTO(FilmDTO filmDTO) {
        this.title = filmDTO.getTitle().orElse(title);
        this.description = filmDTO.getDescription().orElse(description);
        this.releaseYear = filmDTO.getReleaseYear().orElse(releaseYear);
        this.languageId = filmDTO.getLanguageId().orElse(languageId);
        this.originalLanguageId = filmDTO.getOriginalLanguageId().orElse(originalLanguageId);
        this.rentalDuration = filmDTO.getRentalDuration().orElse(rentalDuration);
        this.rentalRate = filmDTO.getRentalRate().orElse(rentalRate);
        this.length = filmDTO.getLength().orElse(length);
        this.replacementCost = filmDTO.getReplacementCost().orElse(replacementCost);
        this.rating = filmDTO.getRating().orElse(rating);
        this.specialFeatures = filmDTO.getSpecialFeatures().orElse(specialFeatures);
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Language getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguageId(Integer originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public Integer getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public List<PartialActor> getCast() {
        return cast;
    }

    public void setCast(List<PartialActor> cast) {
        this.cast = cast;
    }

    public List<Category> getCategories() {
        return categories;
    }

    @Override
    protected Collection<Link> getLinks() {
        return List.of(
                linkTo(methodOn(FilmController.class).getFilmById(getFilmId())).withSelfRel()
        );
    }
}
