package uk.fergcb.sakila.film;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FilmDTOStepDefs {

    Film film;
    FilmDTO filmDTO;
    Exception exception;

    @Given("the DTO has values for all fields")
    public void createFullDTO() {
        filmDTO = new FilmDTO();
        filmDTO.setTitle("Title");
        filmDTO.setDescription("Desc");
        filmDTO.setReleaseYear(1999);
        filmDTO.setLanguageId(1);
        filmDTO.setOriginalLanguageId(2);
        filmDTO.setRentalDuration(3);
        filmDTO.setRentalRate(BigDecimal.valueOf(5.99));
        filmDTO.setLength(120);
        filmDTO.setReplacementCost(BigDecimal.valueOf(20.52));
        filmDTO.setRating("U");
        filmDTO.setSpecialFeatures("Trailers,Deleted Scenes");
    }

    @Given("the DTO doesn't include a description")
    public void createPartialDTO() {
        filmDTO = new FilmDTO();
        filmDTO.setTitle("Title");
        filmDTO.setReleaseYear(1999);
        filmDTO.setLanguageId(1);
        filmDTO.setOriginalLanguageId(2);
        filmDTO.setRentalDuration(3);
        filmDTO.setRentalRate(BigDecimal.valueOf(5.99));
        filmDTO.setLength(120);
        filmDTO.setReplacementCost(BigDecimal.valueOf(20.52));
        filmDTO.setRating("U");
        filmDTO.setSpecialFeatures("Trailers,Deleted Scenes");
    }

    @When("I create a new Film")
    public void createFilm() {
        try {
            film = new Film(filmDTO);
        } catch (Exception ex) {
            exception = ex;
        }
    }

    @Then("the Film should have all fields")
    public void FilmHasAllNotNullFields() {
        assertNotNull(film.getTitle(), "title should not be null");
        assertNotNull(film.getDescription(), "description should not be null");
        assertNotNull(film.getReleaseYear(), "releaseYear should not be null");
        assertNotNull(film.getRentalDuration(), "rentalDuration should not be null");
        assertNotNull(film.getRentalRate(), "rentalRate should not be null");
        assertNotNull(film.getLength(), "length should not be null");
        assertNotNull(film.getReplacementCost(), "replacementCost should not be null");
        assertNotNull(film.getRating(), "rating should not be null");
        assertNotNull(film.getSpecialFeatures(), "specialFeatures should not be null");
    }

    @Then("the Film should not have a description")
    public void unsetFieldsAreNull() {
        assertNull(film.getDescription(), "description should be null");
    }
}
