package uk.fergcb.sakila.data.resources.filmreview;

import org.springframework.hateoas.Link;
import uk.fergcb.sakila.data.hateoas.HateoasResource;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Entity
@Table(name = "film_review")
public class FilmReview extends HateoasResource {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "film_review_id", nullable = false)
    private Integer filmReviewId;

    @Column(name = "film_id", nullable = false)
    private Integer filmId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "text", length = 2000)
    private String text;

    public FilmReview(FilmReviewDTO filmReviewDTO) {
        this.updateFromDTO(filmReviewDTO);
    }

    public FilmReview() {}

    public void updateFromDTO(FilmReviewDTO filmReviewDTO) {
        this.setFilmId(filmReviewDTO.getFilmId().orElse(filmId));
        this.setUserId(filmReviewDTO.getUserId().orElse(userId));
        this.setAuthor(filmReviewDTO.getAuthor().orElse(author));
        this.setTitle(filmReviewDTO.getTitle().orElse(title));
        this.setRating(filmReviewDTO.getRating().orElse(rating));
        this.setText(filmReviewDTO.getText().orElse(text));
    }

    public Integer getFilmReviewId() {
        return filmReviewId;
    }

    public void setFilmReviewId(Integer filmReviewId) {
        this.filmReviewId = filmReviewId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    protected Collection<Link> getLinks() {
        return List.of(
                linkTo(methodOn(FilmReviewController.class).getReview(getFilmReviewId())).withSelfRel()
        );
    }
}
