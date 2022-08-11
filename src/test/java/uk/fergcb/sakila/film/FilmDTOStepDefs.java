package uk.fergcb.sakila.film;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uk.fergcb.sakila.resources.film.Film;
import uk.fergcb.sakila.resources.film.FilmDTO;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
        assertNotNull("title should not be null", film.getTitle());
        assertNotNull("description should not be null", film.getDescription());
        assertNotNull("releaseYear should not be null", film.getReleaseYear());
        assertNotNull("rentalDuration should not be null", film.getRentalDuration());
        assertNotNull("rentalRate should not be null", film.getRentalRate());
        assertNotNull("length should not be null", film.getLength());
        assertNotNull("replacementCost should not be null", film.getReplacementCost());
        assertNotNull("rating should not be null", film.getRating());
        assertNotNull("specialFeatures should not be null", film.getSpecialFeatures());
    }

    @Then("the Film should not have a description")
    public void unsetFieldsAreNull() {
        assertNull("description should be null", film.getDescription());
    }
}
