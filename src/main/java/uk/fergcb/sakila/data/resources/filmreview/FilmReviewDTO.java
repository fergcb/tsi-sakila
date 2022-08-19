package uk.fergcb.sakila.data.resources.filmreview;

import java.util.Optional;

public class FilmReviewDTO {
    private Integer filmId;
    private String userId;
    private String author;
    private String title;
    private Integer rating;
    private String text;

    public Optional<Integer> getFilmId() {
        return Optional.ofNullable(filmId);
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Optional<String> getUserId() {
        return Optional.ofNullable(userId);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Optional<String> getAuthor() {
        return Optional.ofNullable(author);
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Optional<String> getTitle() {
        return Optional.ofNullable(title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Optional<Integer> getRating() {
        return Optional.ofNullable(rating);
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Optional<String> getText() {
        return Optional.ofNullable(text);
    }

    public void setText(String text) {
        this.text = text;
    }
}
