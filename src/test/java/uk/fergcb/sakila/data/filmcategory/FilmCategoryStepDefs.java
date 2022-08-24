package uk.fergcb.sakila.data.filmcategory;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import uk.fergcb.sakila.data.resources.filmcategory.FilmCategory;
import uk.fergcb.sakila.data.resources.filmcategory.FilmCategoryKey;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class FilmCategoryStepDefs {

    final Integer expectedFilmId = 42;
    final Integer expectedCategoryId = 123;

    FilmCategory filmCategory;
    FilmCategoryKey filmCategoryKey;

    @When("I create a FilmCategory")
    public void createFilmCategory() {
        filmCategory = new FilmCategory(expectedFilmId, expectedCategoryId);
    }

    @Then("the FilmCategory has a FilmCategoryKey")
    public void getFilmCategoryKey() {
        filmCategoryKey = filmCategory.getFilmCategoryKey();
        assertNotNull(filmCategoryKey);
    }

    @Then("the FilmCategoryKey has the expected filmId")
    public void getFilmId() {
        assertEquals(expectedFilmId, filmCategoryKey.getFilmId());
    }

    @Then("the FilmCategoryKey has the expected categoryId")
    public void getCategoryId() {
        assertEquals(expectedCategoryId, filmCategoryKey.getCategoryId());
    }

}
