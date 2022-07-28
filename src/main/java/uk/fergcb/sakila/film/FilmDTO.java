package uk.fergcb.sakila.film;

import uk.fergcb.sakila.language.Language;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Optional;

public class FilmDTO {
    private Integer filmId;
    private String title;
    private String description;
    private Date releaseYear;
    private Language language;
    private Language originalLanguage;
    private Integer rentalDuration;
    private BigDecimal rentalRate;
    private Integer length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;

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

    public Optional<Date> getReleaseYear() {
        return Optional.ofNullable(releaseYear);
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Optional<Language> getLanguage() {
        return Optional.ofNullable(language);
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Optional<Language> getOriginalLanguage() {
        return Optional.ofNullable(originalLanguage);
    }

    public void setOriginalLanguage(Language originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Optional<Integer> getRentalDuration() {
        return Optional.ofNullable(rentalDuration);
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public Optional<BigDecimal> getRentalRate() {
        return Optional.ofNullable(rentalRate);
    }

    public void setRentalRate(BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Optional<Integer> getLength() {
        return Optional.ofNullable(length);
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Optional<BigDecimal> getReplacementCost() {
        return Optional.ofNullable(replacementCost);
    }

    public void setReplacementCost(BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    public Optional<String> getRating() {
        return Optional.ofNullable(rating);
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Optional<String> getSpecialFeatures() {
        return Optional.ofNullable(specialFeatures);
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }
}
