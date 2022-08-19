package uk.fergcb.sakila.data.resources.film;

import org.springframework.hateoas.Link;
import uk.fergcb.sakila.data.hateoas.HateoasResource;
import uk.fergcb.sakila.data.resources.actor.PartialActor;
import uk.fergcb.sakila.data.resources.category.Category;
import uk.fergcb.sakila.data.resources.language.Language;
import uk.fergcb.sakila.data.resources.filmreview.FilmReview;
import uk.fergcb.sakila.data.resources.filmreview.FilmReviewController;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    @Column(name="length")
    private Integer length;

    @Column(name="rating")
    private String rating;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name="film_id")
    private Set<FilmReview> reviews;

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
        this.length = filmDTO.getLength().orElse(length);
        this.rating = filmDTO.getRating().orElse(rating);
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

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
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
                linkTo(methodOn(FilmController.class).getFilmById(getFilmId())).withSelfRel(),
                linkTo(methodOn(FilmReviewController.class).getFilmReviews(getFilmId())).withRel("reviews")
        );
    }
}
