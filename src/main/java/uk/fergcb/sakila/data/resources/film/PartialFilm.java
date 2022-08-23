package uk.fergcb.sakila.data.resources.film;

import org.springframework.hateoas.Link;
import uk.fergcb.sakila.data.hateoas.HateoasResource;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
@Table(name="film")
public class PartialFilm extends HateoasResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="film_id")
    private Integer filmId;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="release_year")
    private Integer releaseYear;

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
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

    @Override
    protected Collection<Link> getLinks() {
        return List.of(
                linkTo(methodOn(FilmController.class).getFilmById(getFilmId())).withSelfRel()
        );
    }
}
