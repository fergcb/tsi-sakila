package uk.fergcb.sakila.data.resources.film;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class PartialFilmStepDefs {

    PartialFilm partialFilm;

    @Given("a PartialFilm")
    public void aPartialFilm() {
        partialFilm = new PartialFilm();
    }

    @When("I set the PartialFilm's filmId to {int}")
    public void setFilmId(Integer filmId) {
        partialFilm.setFilmId(filmId);
    }

    @When("I set the PartialFilm's title to {string}")
    public void setTitle(String title) {
        partialFilm.setTitle(title);
    }

    @When("I set the PartialFilm's description to {string}")
    public void setDescription(String description) {
        partialFilm.setDescription(description);
    }

    @When("I set the PartialFilm's releaseYear to {int}")
    public void setReleaseYear(Integer releaseYear) {
        partialFilm.setReleaseYear(releaseYear);
    }

    @Then("the PartialFilm's filmId should be {int}")
    public void getFilmId(Integer filmId) {
        assertEquals(filmId, partialFilm.getFilmId());
    }

    @Then("the PartialFilm's title should be {string}")
    public void getTitle(String title) {
        assertEquals(title, partialFilm.getTitle());
    }

    @Then("the PartialFilm's description should be {string}")
    public void getDescription(String description) {
        assertEquals(description, partialFilm.getDescription());
    }

    @Then("the PartialFilm's releaseYear should be {int}")
    public void getReleaseYear(Integer releaseYear) {
        assertEquals(releaseYear, partialFilm.getReleaseYear());
    }

    @Then("the PartialFilm has a {string} link")
    public void hasLink(String name) {
        assertTrue(partialFilm.get_links().containsKey(name));
    }

}
