package uk.fergcb.sakila.data.film;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uk.fergcb.sakila.data.resources.film.Film;
import uk.fergcb.sakila.data.resources.film.FilmDTO;

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
        filmDTO.setLength(120);
        filmDTO.setRating("U");
    }

    @Given("the DTO doesn't include a description")
    public void createPartialDTO() {
        filmDTO = new FilmDTO();
        filmDTO.setTitle("Title");
        filmDTO.setReleaseYear(1999);
        filmDTO.setLanguageId(1);
        filmDTO.setOriginalLanguageId(2);
        filmDTO.setLength(120);
        filmDTO.setRating("U");
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
        assertNotNull("length should not be null", film.getLength());
        assertNotNull("rating should not be null", film.getRating());
    }

    @Then("the Film should not have a description")
    public void unsetFieldsAreNull() {
        assertNull("description should be null", film.getDescription());
    }
}
