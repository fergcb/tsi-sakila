package uk.fergcb.sakila.resources.film;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public class FilmDTO {
    private Integer filmId;
    private String title;
    private String description;
    private Integer releaseYear;
    private Integer languageId;
    private Integer originalLanguageId;
    private Integer length;
    private String rating;
    private Set<Integer> categoryIds;
    private Set<Integer> actorIds;

    public Optional<Integer> getFilmId() {
        return Optional.ofNullable(filmId);
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Optional<Integer> getReleaseYear() {
        return Optional.ofNullable(releaseYear);
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Optional<Integer> getLanguageId() {
        return Optional.ofNullable(languageId);
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Optional<Integer> getOriginalLanguageId() {
        return Optional.ofNullable(originalLanguageId);
    }

    public void setOriginalLanguageId(Integer originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public Optional<Integer> getLength() {
        return Optional.ofNullable(length);
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Optional<String> getRating() {
        return Optional.ofNullable(rating);
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Set<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Set<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Set<Integer> getActorIds() {
        return actorIds;
    }

    public void setActorIds(Set<Integer> actorIds) {
        this.actorIds = actorIds;
    }
}
